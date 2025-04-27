package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import input.InputHandler;
import main.GameConfig;
import gamelogic.ScoreManager;
import java.io.InputStream;

// Represents the player's spacecraft, controlled by the player
public class Spacecraft extends Entity {

	// Image representing the spacecraft
	private Image sprite;

	// Movement speed multiplier for power-ups
	private double speedMultiplier = 1.0;

	// Time when the speed multiplier effect ends
	private long speedMultiplierEndTime = 0;

	// Constructor: Initializes the spacecraft at the given position
	public Spacecraft(double x, double y) {
		super(x, y, 50, 50);
		try {
			InputStream is = getClass().getResourceAsStream("/image_object_spacecraft.png");
			if (is != null) {
				this.sprite = new Image(is);
			} else {
				throw new Exception("Spacecraft image not found!");
			}
		} catch (Exception e) {
			System.err.println("ERROR: Could not load spacecraft image. " + e.getMessage());
		}
	}

	// Updates the spacecraft's position and size based on player input
	@Override
	public void update() {
		long currentTime = System.currentTimeMillis();

		// Reset speed multiplier if the effect has expired
		if (speedMultiplier > 1.0 && currentTime > speedMultiplierEndTime) {
			speedMultiplier = 1.0;
			System.out.println("Speed x2 effect ended.");
		}

		updateSize();

		// Handle movement controls
		if (InputHandler.isKeyPressed(KeyCode.A) || InputHandler.isKeyPressed(KeyCode.LEFT)) {
			moveLeft();
		}
		if (InputHandler.isKeyPressed(KeyCode.D) || InputHandler.isKeyPressed(KeyCode.RIGHT)) {
			moveRight();
		}
		if (InputHandler.isKeyPressed(KeyCode.W) || InputHandler.isKeyPressed(KeyCode.UP)) {
			moveUp();
		}
		if (InputHandler.isKeyPressed(KeyCode.S) || InputHandler.isKeyPressed(KeyCode.DOWN)) {
			moveDown();
		}
	}

	// Renders the spacecraft on the screen
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);
	}

	// Updates the spacecraft's size based on the player's score
	private void updateSize() {
		int score = ScoreManager.getScore();

		double newSize = GameConfig.SPACECRAFT_BASE_SIZE + (score / 5.0);
		if (newSize > GameConfig.SPACECRAFT_MAX_SIZE) {
			newSize = GameConfig.SPACECRAFT_MAX_SIZE;
		}
		if (newSize < GameConfig.SPACECRAFT_MIN_SIZE) {
			newSize = GameConfig.SPACECRAFT_MIN_SIZE;
		}

		this.width = newSize;
		this.height = newSize;
	}

	// Sets the speed multiplier effect for power-ups
	public void setSpeedMultiplier(double multiplier) {
		this.speedMultiplier = multiplier;
		this.speedMultiplierEndTime = System.currentTimeMillis() + 5000;
	}

	// Moves the spacecraft left
	private void moveLeft() {
		if (x > 0) {
			x -= GameConfig.SPACECRAFT_SPEED * speedMultiplier;
		}
	}

	// Moves the spacecraft right
	private void moveRight() {
		if (x + width < GameConfig.SCREEN_WIDTH) {
			x += GameConfig.SPACECRAFT_SPEED * speedMultiplier;
		}
	}

	// Moves the spacecraft up
	private void moveUp() {
		if (y > 0) {
			y -= GameConfig.SPACECRAFT_SPEED * speedMultiplier;
		}
	}

	// Moves the spacecraft down
	private void moveDown() {
		if (y + height < GameConfig.SCREEN_HEIGHT) {
			y += GameConfig.SPACECRAFT_SPEED * speedMultiplier;
		}
	}
}
