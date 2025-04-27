package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the second How to Play screen
public class HowToPlayScreen2Controller {

	@FXML
	private ImageView bgImage; // Background image for the How to Play screen

	@FXML
	private Button btnHome; // Button to return to the home screen

	@FXML
	private Button btnBack; // Button to go back to the previous How to Play page

	@FXML
	private ImageView homeIcon; // Icon for the home button

	@FXML
	private ImageView backIcon; // Icon for the back button

	// Initializes the How to Play screen (Page 2)
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home_howtoplay_page_2.png")); // Loads background image

		homeIcon.setImage(new Image("file:res/image_button_home.png")); // Loads home button image
		backIcon.setImage(new Image("file:res/image_button_back.png")); // Loads back button image

		// Sets action for the Home button
		btnHome.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			SceneManager.switchTo("HomeScreen.fxml"); // Switches to home screen
		});

		// Sets action for the Back button
		btnBack.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("HowToPlayScreen1.fxml"); // Switches to the previous How to Play screen
		});
	}
}
