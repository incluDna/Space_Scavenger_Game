package input;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import java.util.HashSet;
import java.util.Set;

public class InputHandler {

	// Stores currently pressed keys
	private static final Set<KeyCode> activeKeys = new HashSet<>();

	// Initializes key event listeners for the given scene
	public static void initialize(Scene scene) {
		scene.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
		scene.setOnKeyReleased(event -> activeKeys.remove(event.getCode()));
	}

	// Checks if a specific key is currently pressed
	public static boolean isKeyPressed(KeyCode key) {
		return activeKeys.contains(key);
	}

	// Clears all active keys (used when restarting)
	public static void clearKeys() {
		activeKeys.clear();
		System.out.println("InputHandler: Cleared all active keys.");
	}
}
