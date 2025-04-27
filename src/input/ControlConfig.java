package input;

import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class ControlConfig {

	// Stores currently pressed keys
	private static final Set<KeyCode> activeKeys = new HashSet<>();

	// Registers a key press event
	public static void registerKeyPress(KeyCode key) {
		activeKeys.add(key);
	}

	// Registers a key release event
	public static void registerKeyRelease(KeyCode key) {
		activeKeys.remove(key);
	}

	// Checks if a specific key is currently pressed
	public static boolean isKeyPressed(KeyCode key) {
		return activeKeys.contains(key);
	}
}
