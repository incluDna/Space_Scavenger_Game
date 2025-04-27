package gui;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import gamelogic.GameManager;
import gamelogic.SoundManager;

// Controls the UI for Level 1 screen
public class Level1ScreenController {
	@FXML
	private Canvas gameCanvas; // The canvas where the game is rendered

	@FXML
	private Button btnPlayAgain; // Button to restart the level

	@FXML
	private Button btnHome; // Button to return to the home screen

	// Initializes the Level 1 screen
	@FXML
	public void initialize() {
		System.out.println("Level 1 Started"); // Debug log

		GraphicsContext gc = gameCanvas.getGraphicsContext2D(); // Gets the graphics context
		GameManager.setGraphicsContext(gc); // Assigns it to GameManager

		// Sets action for the Play Again button
		btnPlayAgain.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			GameManager.startGame(gui.SceneManager.getPrimaryStage(), 1); // Restarts Level 1
		});

		// Sets action for the Home button
		btnHome.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			gui.SceneManager.switchTo("HomeScreen.fxml"); // Switches to home screen
		});
	}

	// Returns the game canvas
	public Canvas getCanvas() {
		return gameCanvas;
	}
}
