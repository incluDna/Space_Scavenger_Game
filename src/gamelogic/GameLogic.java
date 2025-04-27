package gamelogic;

import entity.Collectible;
import entity.Enemy;
import entity.PowerUp;
import java.util.ArrayList;
import java.util.List;

// Manages the game's core logic, including spawning and updating objects
public class GameLogic {
	private static List<Collectible> collectibles = new ArrayList<>(); // List of collectible objects
	private static List<Enemy> enemies = new ArrayList<>(); // List of enemy objects
	private static List<PowerUp> powerUps = new ArrayList<>(); // List of power-up objects

	// Spawns collectibles, enemies, and power-ups at the beginning of the game
	public static void spawnObjects() {
		collectibles = GameObjectSpawner.spawnCollectibles(5);
		enemies = GameObjectSpawner.spawnEnemies(2);
		powerUps = GameObjectSpawner.spawnPowerUps(2);
		System.out.println("Game objects spawned.");
	}

	// Returns the list of collectibles
	public static List<Collectible> getCollectibles() {
		return collectibles;
	}

	// Returns the list of enemies
	public static List<Enemy> getEnemies() {
		return enemies;
	}

	// Returns the list of power-ups
	public static List<PowerUp> getPowerUps() {
		return powerUps;
	}

	// Updates all game objects each frame
	public static void updateObjects() {
		for (Collectible collectible : collectibles) {
			collectible.update();
		}
		for (Enemy enemy : enemies) {
			enemy.update();
		}
		for (PowerUp powerUp : powerUps) {
			powerUp.update();
		}
	}
}
