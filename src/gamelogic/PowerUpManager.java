package gamelogic;

import entity.PowerUp;
import java.util.HashMap;
import java.util.Map;

// Manages active power-ups and their expiration times
public class PowerUpManager {
	private static final Map<PowerUp.PowerUpType, Long> activePowerUps = new HashMap<>(); // Stores active power-ups with expiration times

	// Activates a power-up and sets its expiration time
	public static void activatePowerUp(PowerUp.PowerUpType type) {
		activePowerUps.put(type, System.currentTimeMillis() + 5000);
		System.out.println("Power-Up Activated: " + type);
	}

	// Checks if a power-up is still active
	public static boolean isPowerUpActive(PowerUp.PowerUpType type) {
		return activePowerUps.containsKey(type) && System.currentTimeMillis() < activePowerUps.get(type);
	}

	// Removes expired power-ups from the active list
	public static void removeExpiredPowerUps() {
		long currentTime = System.currentTimeMillis();
		activePowerUps.entrySet().removeIf(entry -> currentTime > entry.getValue());
	}
}
