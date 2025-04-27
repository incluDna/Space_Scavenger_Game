package gamelogic;

import entity.Collectible;
import entity.Enemy;
import entity.PowerUp;
import entity.Spacecraft;
import javafx.application.Platform;
import main.GameLoop;
import java.util.List;

// Handles collision detection and resolution
public class CollisionHandler {

	// Checks collisions between the spacecraft and all game objects
	public static void checkCollisions(Spacecraft spacecraft, List<Collectible> collectibles, List<Enemy> enemies,
			List<PowerUp> powerUps) {
		for (Collectible collectible : collectibles) {
			if (spacecraft.getBounds().intersects(collectible.getBounds())) {
				handleCollectibleCollision(spacecraft, collectible);
			}
		}

		for (Enemy enemy : enemies) {
			if (spacecraft.getBounds().intersects(enemy.getBounds())) {
				if (ScoreManager.isStarActive()) {
					System.out.println("Star Mode Active! Enemy ignored.");
					enemy.resetPosition();
				} else {
					System.out.println("Collision with Tornado! Game Over.");
					GameLoop.getInstance().stop();
					Platform.runLater(GameUIManager::showGameOverUI);
					return;
				}
			}
		}

		for (PowerUp powerUp : powerUps) {
			if (spacecraft.getBounds().intersects(powerUp.getBounds())) {
				handlePowerUpCollision(spacecraft, powerUp);
			}
		}
	}

	// Handles collision between the spacecraft and a collectible
	public static void handleCollectibleCollision(Spacecraft spacecraft, Collectible collectible) {
		int points = collectible.getPoints();

		if (ScoreManager.isStarActive()) {
			if (ScoreManager.isScoreMultiplierActive()) {
				points *= 2;
			}
			ScoreManager.addScore(points);
			System.out.println(
					"Star Mode: Collected " + collectible.getClass().getSimpleName() + " | +" + points + " points");
		} else {
			int currentScore = ScoreManager.getScore();
			if (collectible.isCollectible(currentScore)) {
				if (ScoreManager.isScoreMultiplierActive()) {
					points *= 2;
				}
				SoundManager.playSFX("sound_fx_eating_trash.mp3");
				ScoreManager.addScore(points);
				System.out
						.println("Collected: " + collectible.getClass().getSimpleName() + " | +" + points + " points");
			} else {
				if (ScoreManager.isShieldActive()) {
					System.out.println("Shield Active! No penalty.");
					ScoreManager.deactivateShield();
				} else {
					System.out.println("Picked wrong collectible! Game Over.");
					GameLoop.getInstance().stop();
					Platform.runLater(GameUIManager::showGameOverUI);
				}
			}
		}

		Platform.runLater(GameManager::renderGame);
		collectible.resetPosition(true);
	}

	// Handles collision between the spacecraft and a power-up
	public static void handlePowerUpCollision(Spacecraft spacecraft, PowerUp powerUp) {
		PowerUp.PowerUpType collectedType = powerUp.getType();

		switch (collectedType) {
		case SPEED_X2 -> {
			spacecraft.setSpeedMultiplier(2);
			SoundManager.playSFX("sound_fx_x2.mp3");
		}
		case SCORE_X2 -> {
			ScoreManager.activateScoreMultiplier();
			SoundManager.playSFX("sound_fx_x2.mp3");
		}
		case SHIELD -> {
			ScoreManager.activateShield();
			SoundManager.playSFX("sound_fx_storm.mp3");
		}
		case ALIEN -> {
			if (!ScoreManager.isStarActive()) {
				ScoreManager.addScore(-50);
				SoundManager.playSFX("sound_fx_alien.mp3");
			}
		}
		case STAR -> {
			ScoreManager.activateStarMode();
			SoundManager.playSFX("sound_fx_x2.mp3");
		}
		}

		UIManager.activatePowerUpUI(collectedType);
		Platform.runLater(GameManager::renderGame);
		powerUp.resetPosition(true);
	}
}
