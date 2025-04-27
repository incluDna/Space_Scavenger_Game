package gui;

import java.io.File;

import gamelogic.GameManager;
import gamelogic.LevelManager;
import gamelogic.SoundManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gui.SceneManager;

// Controls the win screen UI
public class WinScreenController {

	@FXML
	private ImageView winImage; // Displays the "YOU WIN!!" image

	@FXML
	private Button btnPlayAgain; // Button to restart the level

	@FXML
	private Button btnHome; // Button to return to the home screen

	// Initializes the win screen
	@FXML
	public void initialize() {
		loadWinImage(); // Loads the "YOU WIN!!" image

		// Button to restart the level
		btnPlayAgain.setOnAction(event -> {
			LevelManager.unlockNextLevel(GameManager.getCurrentLevel());
			SoundManager.stopBGM();
			GameManager.startGame(SceneManager.getPrimaryStage(), GameManager.getCurrentLevel());
		});

		// Button to return to the home screen
		btnHome.setOnAction(event -> {
			SoundManager.stopBGM();
			SoundManager.playBGM("sound_bg_opening.mp3");
			SceneManager.switchTo("HomeScreen.fxml");
		});
	}

	// Loads the "YOU WIN!!" image
	private void loadWinImage() {
		File imageFile = new File("res/image_other_youwin.png");
		if (imageFile.exists()) {
			winImage.setImage(new Image(imageFile.toURI().toString()));
		} else {
			System.err.println("ERROR: image_other_youwin.png NOT FOUND in res/");
		}
	}
}
