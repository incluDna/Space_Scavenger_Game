package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the Credit Screen UI
public class CreditScreenController {

	@FXML
	private ImageView bgImage; // Background image for the credit screen

	@FXML
	private Button btnHome; // Button to return to the home screen

	@FXML
	private ImageView homeIcon; // Home button icon

	// Initializes the Credit Screen
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home_credit.png")); // Loads background image
		homeIcon.setImage(new Image("file:res/image_button_home.png")); // Loads home button icon

		// Sets action for the Home button
		btnHome.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			SceneManager.switchTo("HomeScreen.fxml"); // Switches back to the home screen
		});
	}
}
