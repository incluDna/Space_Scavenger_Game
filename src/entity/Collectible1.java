package entity;

// Represents the first type of collectible
public class Collectible1 extends Collectible {

	// Constructor initializes with specific image, points, and score requirement
	public Collectible1() {
		super("file:res/image_object_collect_1.png", 10, 0); // Can be collected at score 0+
	}
}
