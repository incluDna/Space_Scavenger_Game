package gamelogic;

import entity.Collectible;
import entity.Collectible1;
import entity.Collectible2;
import entity.Collectible3;
import entity.Enemy;
import entity.PowerUp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Handles the spawning of all game objects (collectibles, enemies, and power-ups)
public class GameObjectSpawner {
	private static final Random random = new Random(); // Random generator for object spawning

	// Spawns a list of collectibles with random types
	public static List<Collectible> spawnCollectibles(int count) {
		List<Collectible> collectibles = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			int type = random.nextInt(3);
			Collectible collectible = switch (type) {
			case 0 -> new Collectible1();
			case 1 -> new Collectible2();
			case 2 -> new Collectible3();
			default -> null;
			};
			collectibles.add(collectible);
		}
		return collectibles;
	}

	// Spawns a list of enemies
	public static List<Enemy> spawnEnemies(int count) {
		List<Enemy> enemies = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			enemies.add(new Enemy());
		}
		return enemies;
	}

	// Spawns a list of power-ups with random types
	public static List<PowerUp> spawnPowerUps(int count) {
		List<PowerUp> powerUps = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			PowerUp powerUp = new PowerUp();
			System.out.println("Spawned PowerUp: " + powerUp.getType()); // Debug log
			powerUps.add(powerUp);
		}
		return powerUps;
	}
}
