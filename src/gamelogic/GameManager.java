package gamelogic;

import entity.Collectible;
import entity.Enemy;
import entity.PowerUp;
import entity.Spacecraft;
import input.InputHandler;
import gui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import main.GameConfig;
import main.GameLoop;

import java.io.IOException;
import java.util.List;

// Manages the overall game state, including level transitions, updates, and rendering
public class GameManager {
	private static int currentLevel = 1; // Tracks the current level
	private static Spacecraft spacecraft; // The player's spacecraft
	private static GraphicsContext gc; // Graphics context for rendering
	private static Stage mainStage; // The main game window
	private static Scene mainScene; // The active game scene

	// Initializes and starts a new game at the specified level
	public static void startGame(Stage stage, int level) {
		try {
			currentLevel = level;
			ScoreManager.resetScore();

			// Spawns game objects (collectibles, enemies, power-ups)
			GameLogic.spawnObjects();

			// Creates a new spacecraft for the player
			spacecraft = new Spacecraft(GameConfig.SCREEN_WIDTH / 2 - 25, GameConfig.SCREEN_HEIGHT - 100);

			String levelFXML = switch (level) {
			case 1 -> "Level1Screen.fxml";
			case 2 -> "Level2Screen.fxml";
			case 3 -> "Level3Screen.fxml";
			default -> "";
			};

			FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(levelFXML));
			Parent root = loader.load();

			StackPane pane = new StackPane(root);
			Scene newScene = new Scene(pane, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
			stage.setScene(newScene);
			mainScene = newScene;
			mainStage = stage;

			InputHandler.initialize(mainScene);
			GameLoop.getInstance().start();

			// Stops home screen BGM before playing level music
			SoundManager.stopBGM();

			// Plays the appropriate BGM for the level
			switch (level) {
			case 1, 2 -> SoundManager.playBGM("sound_bg_level_1_2.mp3");
			case 3 -> SoundManager.playBGM("sound_bg_level_3.mp3");
			}
		} catch (IOException e) {
			System.err.println("ERROR loading level: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Updates the game state every frame
	public static void updateGame() {
		spacecraft.update();
		ScoreManager.update();

		// Updates all game objects
		GameLogic.updateObjects();

		// Checks for collisions
		CollisionHandler.checkCollisions(spacecraft, GameLogic.getCollectibles(), GameLogic.getEnemies(),
				GameLogic.getPowerUps());

		// Checks if the game is won or lost
		checkGameStatus();
	}

	// Checks whether the player has won or lost the game
	private static void checkGameStatus() {
		if (ScoreManager.getScore() < 0) {
			if (ScoreManager.isShieldActive()) {
				System.out.println("Shield Active! Alien penalty ignored.");
				ScoreManager.deactivateShield();
			} else {
				System.out.println("Game Over! Score below 0.");
				ScoreManager.resetScore();
				GameLoop.getInstance().stop();
				Platform.runLater(GameUIManager::showGameOverUI);
			}
		}

		if (ScoreManager.getScore() >= 300) {
			System.out.println("Player wins! Score reached 300.");
			GameLoop.getInstance().stop();
			Platform.runLater(GameUIManager::showWinUI);
		}
	}

	// Renders the game objects and UI on the screen
	public static void renderGame() {
		if (gc == null) {
			System.out.println("ERROR: GraphicsContext is null! Cannot render.");
			return;
		}

		Platform.runLater(() -> {
			gc.clearRect(0, 0, GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);

			// Render spacecraft
			if (spacecraft != null) {
				spacecraft.render(gc);
			}

			// Render all game objects
			for (Collectible collectible : GameLogic.getCollectibles()) {
				collectible.render(gc);
			}

			for (Enemy enemy : GameLogic.getEnemies()) {
				enemy.render(gc);
			}

			for (PowerUp powerUp : GameLogic.getPowerUps()) {
				powerUp.render(gc);
			}

			// Render UI elements
			UIManager.renderUI();
		});
	}

	// Sets the graphics context for rendering
	public static void setGraphicsContext(GraphicsContext newGc) {
		gc = newGc;
		UIManager.setGraphicsContext(newGc);
	}

	// Returns the current level
	public static int getCurrentLevel() {
		return currentLevel;
	}

	// Returns the player's spacecraft instance
	public static Spacecraft getSpacecraft() {
		return spacecraft;
	}
}
