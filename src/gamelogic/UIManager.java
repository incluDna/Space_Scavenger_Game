package gamelogic;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import main.GameConfig;
import java.util.HashMap;
import java.util.Map;

import entity.PowerUp;

// Manages UI rendering for score, power-ups, and range score bar
public class UIManager {
	private static GraphicsContext gc; // Graphics context for rendering UI
	private static Map<PowerUp.PowerUpType, Image> powerUpIcons = new HashMap<>(); // Stores power-up icons
	private static Map<PowerUp.PowerUpType, Long> activePowerUps = new HashMap<>(); // Tracks active power-ups and
																					// expiration time

	// Sets the graphics context for UI rendering
	public static void setGraphicsContext(GraphicsContext newGc) {
		gc = newGc;
	}

	// Renders UI elements
	public static void renderUI() {
		if (gc == null)
			return;
		Platform.runLater(() -> {
			drawScore();
			drawRangeScoreBar();
			drawActivePowerUps();
		});
	}

	// Draws the current score on screen
	private static void drawScore() {
		double scoreX = GameConfig.SCREEN_WIDTH - 200;
		double scoreY = 50;

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Arial Bold", 28));
		gc.setTextAlign(TextAlignment.RIGHT);
		gc.fillText(String.valueOf(ScoreManager.getScore()), scoreX, scoreY);
	}

	// Draws the range score bar
	private static void drawRangeScoreBar() {
		double barX = 37;
		double barY = 92;
		double barWidth = 360;
		double barHeight = 15;
		double currentWidth = (ScoreManager.getScore() / 300.0) * barWidth;

		gc.setFill(Color.DARKGRAY);
		gc.fillRect(barX, barY, barWidth, barHeight);
		gc.setFill(Color.LIMEGREEN);
		gc.fillRect(barX, barY, currentWidth, barHeight);
	}

	// Draws active power-ups and their remaining time
	private static void drawActivePowerUps() {
		int startX = (int) (GameConfig.SCREEN_WIDTH - 250);
		int startY = 70;
		int spacing = 60;
		long currentTime = System.currentTimeMillis();

		synchronized (activePowerUps) {
			int index = 0;
			for (var entry : activePowerUps.entrySet()) {
				PowerUp.PowerUpType type = entry.getKey();
				long remainingTime = Math.max(0, (entry.getValue() - currentTime) / 1000);

				if (remainingTime > 0 && powerUpIcons.containsKey(type)) {
					Image icon = powerUpIcons.get(type);
					gc.drawImage(icon, startX + (index * spacing), startY, 50, 50);
					gc.setFill(Color.WHITE);
					gc.setFont(new Font("Arial", 16));
					gc.fillText(remainingTime + "s", startX + (index * spacing) + 15, startY + 65);
					index++;
				}
			}
		}
	}

	// Activates a power-up in the UI
	public static void activatePowerUpUI(PowerUp.PowerUpType type) {
		long endTime = System.currentTimeMillis() + 5000;

		synchronized (activePowerUps) {
			activePowerUps.put(type, endTime);
		}

		Image icon = switch (type) {
		case SPEED_X2 -> new Image("file:res/image_object_powerup_speedx2.png");
		case SCORE_X2 -> new Image("file:res/image_object_powerup_scorex2.png");
		case SHIELD -> new Image("file:res/image_object_powerup_shield.png");
		case STAR -> new Image("file:res/image_object_powerup_star.png");
		default -> null;
		};

		if (icon != null) {
			Platform.runLater(() -> {
				powerUpIcons.put(type, icon);
				System.out.println("Power-Up Icon Added: " + type);
			});
		}

		// Removes the power-up from UI after 5 seconds
		new Thread(() -> {
			try {
				Thread.sleep(5000);
				synchronized (activePowerUps) {
					activePowerUps.remove(type);
				}
				Platform.runLater(() -> powerUpIcons.remove(type));
				System.out.println("Power-Up Expired: " + type);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	// Removes expired power-ups from the UI
	public static void removeExpiredPowerUps() {
		long currentTime = System.currentTimeMillis();
		synchronized (activePowerUps) {
			activePowerUps.entrySet().removeIf(entry -> {
				boolean expired = currentTime > entry.getValue();
				if (expired) {
					Platform.runLater(() -> powerUpIcons.remove(entry.getKey()));
				}
				return expired;
			});
		}
	}
}
