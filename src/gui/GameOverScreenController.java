package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import gamelogic.GameManager;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the Game Over Screen UI
public class GameOverScreenController {

	@FXML
	private ImageView bgGameOver; // Background image for the game over screen

	@FXML
	private Button btnPlayAgain; // Button to restart the current level

	@FXML
	private Button btnMenu; // Button to return to the main menu

	// Initializes the Game Over Screen
	@FXML
	public void initialize() {
		bgGameOver.setImage(new Image("file:res/image_other_gameover.png")); // Loads background image

		// Sets action for the Play Again button
		btnPlayAgain.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			GameManager.startGame(SceneManager.getPrimaryStage(), GameManager.getCurrentLevel()); // Restarts the level
		});

		// Sets action for the Menu button
		btnMenu.setOnAction(event -> {
			SoundManager.stopBGM(); // Stops current background music
			SoundManager.playBGM("sound_bg_opening.mp3"); // Plays home screen background music
			SceneManager.switchTo("HomeScreen.fxml"); // Switches to the home screen
		});
	}
}
