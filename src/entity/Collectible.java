package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameConfig;
import java.util.Random;

public abstract class Collectible extends Entity {

	// Image representing the collectible
	protected Image sprite;

	// Points awarded when collected
	protected int points;

	// Minimum score required to collect
	private int minScoreRequired;

	// Random instance for positioning
	protected static final Random random = new Random();

	// Constructor initializes collectible properties
	public Collectible(String imagePath, int points, int minScoreRequired) {
		super(random.nextDouble() * (GameConfig.SCREEN_WIDTH - 50), -60, 50, 50);
		this.points = points;
		this.minScoreRequired = minScoreRequired;
		this.sprite = new Image(imagePath);
	}

	// Updates collectible movement
	@Override
	public void update() {
		y += 3;
		if (y > GameConfig.SCREEN_HEIGHT) {
			resetPosition(true);
		}
	}

	// Renders collectible on the canvas
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);
	}

	// Resets collectible position
	public void resetPosition(boolean keepType) {
		x = random.nextDouble() * (GameConfig.SCREEN_WIDTH - width);
		y = -50;
	}

	// Returns collectible points
	public int getPoints() {
		return points;
	}

	// Checks if collectible is valid for current score
	public boolean isCollectible(int score) {
		return score >= minScoreRequired;
	}
}
