package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import main.GameConfig;
import java.util.Random;

// Represents an enemy (Tornado) that players must avoid
public class Enemy extends Entity {
	private static final Random random = new Random(); // Random instance for spawning position
	private Image sprite; // Image representing the enemy

	// Constructor initializes the enemy with an image and random position
	public Enemy() {
		super(random.nextDouble() * (GameConfig.SCREEN_WIDTH - 50), -60, 50, 50);
		this.sprite = new Image("file:res/image_object_enemy.png");
	}

	// Updates the enemy's position, making it move downward
	@Override
	public void update() {
		y += 4;
		if (y > GameConfig.SCREEN_HEIGHT) {
			resetPosition();
		}
	}

	// Renders the enemy on the screen
	@Override
	public void render(GraphicsContext gc) {
		gc.drawImage(sprite, x, y, width, height);
	}

	// Resets the enemy position to the top when it moves off-screen
	public void resetPosition() {
		x = random.nextDouble() * (GameConfig.SCREEN_WIDTH - width);
		y = -60;
	}
}
