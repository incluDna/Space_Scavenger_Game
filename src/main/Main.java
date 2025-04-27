package main;

import gui.SceneManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	// Initializes and starts the game application
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Space Scavenger"); // Set the window title
			primaryStage.setResizable(false); // Disable window resizing
			primaryStage.getIcons().add(new Image("file:res/image_icon.png")); // Set window icon

			SceneManager.setPrimaryStage(primaryStage);
			SceneManager.switchTo("HomeScreen.fxml"); // Load home screen
			primaryStage.show(); // Show the window
		} catch (Exception e) {
			System.err.println("ERROR in start(): " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Entry point of the application
	public static void main(String[] args) {
		launch(args); // Start JavaFX application
	}
}
