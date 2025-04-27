package gui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import gamelogic.SoundManager;
import gui.SceneManager;

// Controls the Home Screen UI
public class HomeScreenController {

	@FXML
	private ImageView bgImage; // Background image for the home screen

	@FXML
	private VBox buttonContainer; // Container for menu buttons

	@FXML
	private Button btnPlayGame; // Button to start the level selection screen

	@FXML
	private Button btnSettings; // Button to open settings

	@FXML
	private Button btnHowToPlay; // Button to view how-to-play instructions

	@FXML
	private Button btnCredit; // Button to view game credits

	@FXML
	private Button btnExit; // Button to exit the game

	// Initializes the Home Screen
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home.png")); // Loads background image

		SoundManager.playBGM("sound_bg_opening.mp3"); // Plays background music

		// Sets images for buttons
		setButtonImage(btnPlayGame, "file:res/image_button_playgame.png");
		setButtonImage(btnSettings, "file:res/image_button_setting.png");
		setButtonImage(btnHowToPlay, "file:res/image_button_howtoplay.png");
		setButtonImage(btnCredit, "file:res/image_button_credit.png");
		setButtonImage(btnExit, "file:res/image_button_exit.png");

		// Sets action for the Play Game button
		btnPlayGame.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			SceneManager.switchTo("LevelSelectionScreen.fxml"); // Switches to level selection
		});

		// Sets action for the Settings button
		btnSettings.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("SettingsScreen.fxml");
		});

		// Sets action for the How to Play button
		btnHowToPlay.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("HowToPlayScreen1.fxml");
		});

		// Sets action for the Credit button
		btnCredit.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("CreditScreen.fxml");
		});

		// Sets action for the Exit button
		btnExit.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			System.exit(0); // Closes the game
		});
	}

	// Sets an image for a button
	private void setButtonImage(Button button, String imagePath) {
		ImageView imageView = new ImageView(new Image(imagePath));
		imageView.setFitWidth(250); // Adjust button image width
		imageView.setPreserveRatio(true);
		button.setGraphic(imageView);
	}
}
