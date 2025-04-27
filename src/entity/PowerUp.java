package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameConfig;
import gamelogic.ScoreManager;

import java.util.Random;

// Represents power-up objects in the game
public class PowerUp extends Entity {

	// Enum for different types of power-ups
	public enum PowerUpType {
		SPEED_X2, SCORE_X2, SHIELD, ALIEN, STAR
	}

	private static final Random random = new Random(); // Random object for generating positions and types
	private PowerUpType type; // The type of the power-up
	private Image sprite; // The image representing the power-up

	// Constructor initializes the power-up with a random type
	public PowerUp() {
		super(random.nextDouble() * (GameConfig.SCREEN_WIDTH - 40), -50, 40, 40);
		assignRandomType();
	}

	// Assigns a random type and corresponding image to the power-up
	private void assignRandomType() {
		switch (random.nextInt(5)) {
		case 0 -> {
			type = PowerUpType.SPEED_X2;
			sprite = new Image("file:res/image_object_powerup_speedx2.png");
		}
		case 1 -> {
			type = PowerUpType.SCORE_X2;
			sprite = new Image("file:res/image_object_powerup_scorex2.png");
		}
		case 2 -> {
			type = PowerUpType.SHIELD;
			sprite = new Image("file:res/image_object_powerup_shield.png");
		}
		case 3 -> {
			type = PowerUpType.ALIEN;
			sprite = new Image("file:res/image_object_powerup_alien.png");
		}
		case 4 -> {
			type = PowerUpType.STAR;
			sprite = new Image("file:res/image_object_powerup_star.png");
		}
		default -> {
			throw new IllegalStateException("Unexpected PowerUp type!");
		}
		}
	}

	// Updates the power-up's position
	@Override
	public void update() {
		y += 3;
		if (y > GameConfig.SCREEN_HEIGHT) {
			resetPosition(false);
		}
	}

	// Renders the power-up on the canvas
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);
	}

	// Resets the power-up's position and optionally keeps its type
	public void resetPosition(boolean keepType) {
		x = random.nextDouble() * (GameConfig.SCREEN_WIDTH - width);
		y = -50;
		if (!keepType) {
			assignRandomType();
		}
	}

	// Applies the power-up's effect to the player
	public void applyEffect(Spacecraft player) {
		switch (type) {
		case SPEED_X2 -> player.setSpeedMultiplier(2);
		case SCORE_X2 -> ScoreManager.activateScoreMultiplier();
		case SHIELD -> ScoreManager.activateShield();
		case ALIEN -> ScoreManager.addScore(-50);
		case STAR -> ScoreManager.activateStarMode();
		}
	}

	// Returns the type of the power-up
	public PowerUpType getType() {
		return type;
	}
}
