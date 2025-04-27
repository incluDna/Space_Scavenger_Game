package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the settings screen UI
public class SettingsScreenController {

	@FXML
	private ImageView bgImage; // Background image of the settings screen

	@FXML
	private Button btnToggleMusic; // Button to toggle background music

	@FXML
	private Button btnToggleSound; // Button to toggle sound effects

	@FXML
	private Button btnBack; // Button to return to the home screen

	private boolean isMusicOn = true; // Tracks background music state
	private boolean isSoundOn = true; // Tracks sound effect state

	// Initializes the settings screen
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home_setting.png")); // Loads background image

		updateMusicButton(); // Updates the music button state
		updateSoundButton(); // Updates the sound button state

		// Toggles background music on/off
		btnToggleMusic.setOnAction(event -> {
			isMusicOn = !isMusicOn;
			SoundManager.setBGMEnabled(isMusicOn);
			updateMusicButton();
		});

		// Toggles sound effects on/off
		btnToggleSound.setOnAction(event -> {
			isSoundOn = !isSoundOn;
			SoundManager.setSFXEnabled(isSoundOn);
			updateSoundButton();
		});

		// Returns to the home screen
		btnBack.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("HomeScreen.fxml");
		});
	}

	// Updates the music toggle button UI
	private void updateMusicButton() {
		String imagePath = isMusicOn ? "file:res/image_button_checkbox_in.png"
				: "file:res/image_button_checkbox_out.png";
		btnToggleMusic.setGraphic(new ImageView(new Image(imagePath)));
	}

	// Updates the sound toggle button UI
	private void updateSoundButton() {
		String imagePath = isSoundOn ? "file:res/image_button_checkbox_in.png"
				: "file:res/image_button_checkbox_out.png";
		btnToggleSound.setGraphic(new ImageView(new Image(imagePath)));
	}
}
