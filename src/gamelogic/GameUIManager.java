package gamelogic;

import java.io.File;
import java.io.IOException;
import gui.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Handles the UI for game-over and win screens
public class GameUIManager {

	// Displays the win screen and unlocks the next level
	public static void showWinUI() {
		Platform.runLater(() -> {
			try {
				LevelManager.unlockNextLevel(GameManager.getCurrentLevel());

				// Stops background music and plays the win sound effect
				SoundManager.stopBGM();
				playWinSound();

				FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("WinScreen.fxml"));
				Parent root = loader.load();
				StackPane pane = (StackPane) SceneManager.getPrimaryStage().getScene().getRoot();
				pane.getChildren().add(root);

			} catch (IOException e) {
				System.err.println("ERROR loading WinScreen.fxml: " + e.getMessage());
			}
		});
	}

	// Plays the win sound effect
	private static void playWinSound() {
		try {
			Media media = new Media(new File("res/sound_bg_win.mp3").toURI().toString());
			MediaPlayer winPlayer = new MediaPlayer(media);
			winPlayer.setVolume(SoundManager.getSFXVolume());
			winPlayer.setOnEndOfMedia(winPlayer::dispose);
			winPlayer.play();
			System.out.println("Playing win sound...");
		} catch (Exception e) {
			System.err.println("ERROR: Cannot play win sound - " + e.getMessage());
		}
	}

	// Displays the game-over screen
	public static void showGameOverUI() {
		Platform.runLater(() -> {
			try {
				FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("GameOverScreen.fxml"));
				Parent root = loader.load();
				StackPane pane = (StackPane) SceneManager.getPrimaryStage().getScene().getRoot();
				pane.getChildren().add(root);

				// Stops background music and plays the game-over sound effect
				SoundManager.stopBGM();
				Media media = new Media(new File("res/sound_bg_lost.mp3").toURI().toString());
				MediaPlayer lostPlayer = new MediaPlayer(media);
				lostPlayer.setVolume(SoundManager.getSFXVolume());
				lostPlayer.play();

			} catch (IOException e) {
				System.err.println("ERROR loading GameOverScreen.fxml: " + e.getMessage());
			}
		});
	}
}
