package entity;

// Represents the second type of collectible
public class Collectible2 extends Collectible {

	// Constructor initializes with specific image, points, and score requirement
	public Collectible2() {
		super("file:res/image_object_collect_2.png", 15, 100); // Can be collected at score 100+
	}
}
