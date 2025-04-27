package gamelogic;

// Manages level unlocking and progression
public class LevelManager {
	private static int unlockedLevel = 1; // Stores the highest unlocked level
	private static int currentLevel = 1; // Stores the currently selected level

	// Checks if a specific level is unlocked
	public static boolean isLevelUnlocked(int level) {
		return level <= unlockedLevel;
	}

	// Unlocks the next level when the current level is completed
	public static void unlockNextLevel(int currentLevel) {
		if (currentLevel >= 1 && currentLevel < 3) {
			unlockedLevel = Math.max(unlockedLevel, currentLevel + 1);
			System.out.println("Level " + (currentLevel + 1) + " unlocked!");
		} else {
			System.out.println("All levels are already unlocked.");
		}
	}

	// Unlocks all levels (used for cheat mode)
	public static void unlockAllLevels() {
		unlockedLevel = 3;
		System.out.println("Cheat Mode: All levels unlocked!");
	}

	// Resets level unlocking back to level 1
	public static void resetLevels() {
		unlockedLevel = 1;
	}

	// Sets the current level
	public static void setCurrentLevel(int level) {
		currentLevel = level;
	}

	// Gets the current level
	public static int getCurrentLevel() {
		return currentLevel;
	}

	// Gets the highest unlocked level
	public static int getUnlockedLevel() {
		return unlockedLevel;
	}
}
