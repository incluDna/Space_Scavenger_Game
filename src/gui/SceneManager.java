package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.stage.Stage;
import java.io.IOException;

import gamelogic.SoundManager;

// Manages scene transitions in the game
public class SceneManager {
	private static Stage primaryStage; // The primary application stage

	// Sets the primary stage for scene management
	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}

	// Gets the primary stage
	public static Stage getPrimaryStage() {
		return primaryStage;
	}

	// Switches the current scene to a new FXML file
	public static void switchTo(String fxmlFile) {
		try {
			Parent root = FXMLLoader.load(SceneManager.class.getResource(fxmlFile)); // Loads the FXML file
			Scene scene = new Scene(root, 1280, 720); // Creates a new scene

			// Adds click sound effect to all buttons in the scene
			for (Node node : root.lookupAll("*")) { // Loops through all elements in the scene
				if (node instanceof Button button) { // Checks if the node is a button
					button.setOnMouseClicked(event -> SoundManager.playSFX("sound_fx_on_click.mp3"));
				}
			}

			primaryStage.setScene(scene); // Sets the new scene
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
