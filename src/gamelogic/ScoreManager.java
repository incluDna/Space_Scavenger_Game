package gamelogic;

import javafx.application.Platform;
import main.GameConfig;
import main.GameLoop;

// Manages the player's score, multipliers, and special effects
public class ScoreManager {
	private static int score = 0; // Current player score
	private static double scoreMultiplier = 1.0; // Multiplier for score calculation
	private static boolean shieldActive = false; // Indicates if shield is active
	private static boolean scoreMultiplierActive = false; // Indicates if score multiplier is active
	private static boolean starActive = false; // Indicates if star mode is active
	private static long starEndTime = 0; // Stores star mode expiration time
	private static long scoreMultiplierEndTime = 0; // Stores score multiplier expiration time

	// Adds points to the score
	public static void addScore(int points) {
		score += points;
		if (score > GameConfig.MAX_SCORE) {
			score = GameConfig.MAX_SCORE;
		}

		System.out.println("Score: " + score);

		if (score == GameConfig.MAX_SCORE) {
			GameLoop.getInstance().stop();
			Platform.runLater(GameUIManager::showWinUI);
		}
	}

	// Returns the current score
	public static int getScore() {
		return score;
	}

	// Resets the score to zero
	public static void resetScore() {
		score = 0;
	}

	// Activates the score multiplier for a limited time
	public static void activateScoreMultiplier() {
		scoreMultiplierActive = true;
		scoreMultiplierEndTime = System.currentTimeMillis() + 5000;
		System.out.println("Score x2 Activated!");
	}

	// Sets the score multiplier
	public static void setScoreMultiplier(double multiplier) {
		scoreMultiplier = multiplier;
		System.out.println("Score multiplier set to: " + multiplier);
	}

	// Resets the score multiplier to default
	public static void resetScoreMultiplier() {
		scoreMultiplier = 1.0;
	}

	// Updates active effects such as multipliers and star mode
	public static void update() {
		long currentTime = System.currentTimeMillis();

		if (scoreMultiplierActive && currentTime > scoreMultiplierEndTime) {
			scoreMultiplierActive = false;
			System.out.println("Score x2 Ended!");
		}

		if (starActive && currentTime > starEndTime) {
			starActive = false;
			System.out.println("Star Mode Deactivated!");
		}
	}

	// Returns true if the shield is active
	public static boolean isShieldActive() {
		return shieldActive;
	}

	// Activates the shield
	public static void activateShield() {
		shieldActive = true;
	}

	// Deactivates the shield
	public static void deactivateShield() {
		shieldActive = false;
	}

	// Returns true if the score multiplier is active
	public static boolean isScoreMultiplierActive() {
		return scoreMultiplierActive;
	}

	// Deactivates the score multiplier
	public static void deactivateScoreMultiplier() {
		scoreMultiplierActive = false;
	}

	// Returns true if the star mode is active
	public static boolean isStarActive() {
		return starActive;
	}

	// Activates the star mode for a limited time
	public static void activateStarMode() {
		starActive = true;
		starEndTime = System.currentTimeMillis() + 5000;
		System.out.println("Star Mode Activated!");
	}

	// Deactivates the star mode
	public static void deactivateStarMode() {
		starActive = false;
		System.out.println("Star Mode Deactivated!");
	}

	// Returns the end time of the star mode
	public static long getStarEndTime() {
		return starEndTime;
	}
}
