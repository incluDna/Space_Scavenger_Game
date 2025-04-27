package entity;

// Represents the third type of collectible
public class Collectible3 extends Collectible {
    
    // Constructor initializes with specific image, points, and score requirement
    public Collectible3() {
        super("file:res/image_object_collect_3.png", 20, 200); // Can be collected at score 200+
    }
}
