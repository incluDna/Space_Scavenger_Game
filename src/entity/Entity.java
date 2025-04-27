package entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

// Base class for all game objects
public abstract class Entity implements Renderable, Updatable {
	protected double x, y; // Position of the entity
	protected double width, height; // Size of the entity

	// Constructor initializes entity position and size
	public Entity(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Abstract method to update entity behavior
	@Override
	public abstract void update();

	// Abstract method to render entity
	@Override
	public abstract void render(GraphicsContext gc);

	// Gets the bounding box for collision detection
	public Rectangle2D getBounds() {
		return new Rectangle2D(x, y, width, height);
	}

	// Gets the x position of the entity
	public double getX() {
		return x;
	}

	// Gets the y position of the entity
	public double getY() {
		return y;
	}
}
