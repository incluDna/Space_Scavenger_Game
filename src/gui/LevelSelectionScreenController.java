package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import gamelogic.GameManager;
import gamelogic.LevelManager;
import gamelogic.SoundManager;

// Controls the UI for the level selection screen
public class LevelSelectionScreenController {
	@FXML
	private Button btnLevel1; // Button to select Level 1
	@FXML
	private Button btnLevel2; // Button to select Level 2
	@FXML
	private Button btnLevel3; // Button to select Level 3
	@FXML
	private Button btnCheatUnlock; // Button to unlock all levels (cheat mode)
	@FXML
	private Button btnBack; // Button to return to the home screen

	@FXML
	private ImageView iconLevel1; // Icon for Level 1
	@FXML
	private ImageView iconLevel2; // Icon for Level 2
	@FXML
	private ImageView iconLevel3; // Icon for Level 3
	@FXML
	private ImageView bgImage; // Background image for the level selection screen
	@FXML
	private ImageView homeIcon; // Home button icon

	private int selectedLevel = -1; // Tracks the currently selected level

	// Initializes the level selection screen
	@FXML
	public void initialize() {
		bgImage.setImage(new Image("file:res/image_bg_home_level_selection.png")); // Sets the background image
		homeIcon.setImage(new Image("file:res/image_button_home.png")); // Sets the home button icon

		// Sets level icons size
		iconLevel1.setFitWidth(250);
		iconLevel1.setFitHeight(250);
		iconLevel2.setFitWidth(250);
		iconLevel2.setFitHeight(250);
		iconLevel3.setFitWidth(250);
		iconLevel3.setFitHeight(250);

		// Sets actions for level selection buttons
		btnLevel1.setOnAction(event -> handleLevelSelection(1));
		btnLevel2.setOnAction(event -> handleLevelSelection(2));
		btnLevel3.setOnAction(event -> handleLevelSelection(3));

		// Sets action for the cheat unlock button
		btnCheatUnlock.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3"); // Plays click sound
			LevelManager.unlockAllLevels(); // Unlocks all levels
			updateLevelButtons(); // Updates UI
		});

		// Sets action for the back button
		btnBack.setOnAction(event -> {
			SoundManager.playSFX("sound_fx_on_click.mp3");
			SceneManager.switchTo("HomeScreen.fxml"); // Switches to home screen
		});

		updateLevelButtons(); // Updates level buttons based on unlocked levels
	}

	// Handles level selection logic
	private void handleLevelSelection(int level) {
		if (!LevelManager.isLevelUnlocked(level)) {
			System.out.println("Level " + level + " is locked.");
			return;
		}

		if (selectedLevel == level) {
			// If the selected level is clicked again, start the game
			SoundManager.playSFX("sound_fx_on_click.mp3");
			LevelManager.setCurrentLevel(level);
			GameManager.startGame(SceneManager.getPrimaryStage(), level);
		} else {
			// Selects a new level
			selectedLevel = level;
			updateLevelButtons();
		}
	}

	// Updates the level selection buttons based on unlock status
	private void updateLevelButtons() {
		int unlockedLevel = LevelManager.getUnlockedLevel();

		updateLevelIcon(iconLevel1, 1, unlockedLevel);
		updateLevelIcon(iconLevel2, 2, unlockedLevel);
		updateLevelIcon(iconLevel3, 3, unlockedLevel);

		System.out.println("Level selection updated. Unlocked up to Level " + unlockedLevel);
	}

	// Updates level icons based on their unlock state
	private void updateLevelIcon(ImageView icon, int level, int unlockedLevel) {
		String imagePath;
		if (level > unlockedLevel) {
			imagePath = "file:res/image_button_level_locked.png"; // Locked level
		} else if (selectedLevel == level) {
			imagePath = "file:res/image_button_level_check.png"; // Selected level
		} else {
			imagePath = "file:res/image_button_level_unlocked.png"; // Unlocked level
		}
		icon.setImage(new Image(imagePath));
	}
}
