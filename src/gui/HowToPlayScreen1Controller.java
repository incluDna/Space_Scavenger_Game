package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the first How to Play screen
public class HowToPlayScreen1Controller {

	@FXML
	private ImageView bgImage; // Background image for the How to Play screen

	@FXML
	private Button btnHome; // Button to return to the home screen

	@FXML
	private Button btnNext; // Button to go to the next How to Play page

	@FXML
	private ImageView homeIcon; // Icon for the home button

	@FXML
	private ImageView nextIcon; // Icon for the next button

	// Initializes the How to Play screen (Page 1)
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home_howtoplay_page_1.png")); // Loads background image

		homeIcon.setImage(new Image("file:res/image_button_home.png")); // Loads home button image
		nextIcon.setImage(new Image("file:res/image_button_next.png")); // Loads next button image

		// Sets action for the Home button
		btnHome.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			SceneManager.switchTo("HomeScreen.fxml"); // Switches to home screen
		});

		// Sets action for the Next button
		btnNext.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("HowToPlayScreen2.fxml"); // Switches to the next How to Play screen
		});
	}
}
