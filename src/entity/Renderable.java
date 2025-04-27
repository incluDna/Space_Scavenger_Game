package entity;

import javafx.scene.canvas.GraphicsContext;

// Interface for objects that can be rendered on the screen
public interface Renderable {

	// Renders the object on the given GraphicsContext
	void render(GraphicsContext gc);
}
