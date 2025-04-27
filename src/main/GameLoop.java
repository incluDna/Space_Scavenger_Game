package main;

import gamelogic.GameManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;

public class GameLoop {

	// Singleton instance of GameLoop
	private static GameLoop instance;

	// Timer for handling the game update loop
	private AnimationTimer timer;

	// Indicates whether the game loop is running
	private boolean running = false;

	// Private constructor to prevent external instantiation
	private GameLoop() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (!running) {
					return;
				}
				GameManager.updateGame();
				Platform.runLater(GameManager::renderGame);
			}
		};
	}

	// Returns the singleton instance of GameLoop
	public static GameLoop getInstance() {
		if (instance == null) {
			instance = new GameLoop();
		}
		return instance;
	}

	// Starts the game loop if not already running
	public void start() {
		if (!running) {
			running = true;
			timer.start();
			System.out.println("Game loop started.");
		}
	}

	// Stops the game loop if it is running
	public void stop() {
		if (running) {
			running = false;
			timer.stop();
			System.out.println("Game loop stopped.");
		}
	}

	// Returns whether the game loop is currently running
	public boolean isRunning() {
		return running;
	}
}
