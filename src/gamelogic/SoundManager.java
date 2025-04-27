package gamelogic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// Manages background music (BGM) and sound effects (SFX)
public class SoundManager {
	private static double bgmVolume = 1.0; // Volume level for background music
	private static double sfxVolume = 1.0; // Volume level for sound effects
	private static boolean isBGMEnabled = true; // Indicates if BGM is enabled
	private static boolean isSFXEnabled = true; // Indicates if SFX is enabled
	private static final Map<String, MediaPlayer> bgmPlayers = new HashMap<>(); // Stores BGM players
	private static final Map<String, Media> sfxMedia = new HashMap<>(); // Stores SFX media files
	private static MediaPlayer currentBGM; // Stores the currently playing BGM

	static {
		loadBGM("sound_bg_level_1_2.mp3");
		loadBGM("sound_bg_level_3.mp3");
		loadBGM("sound_bg_lost.mp3");
		loadBGM("sound_bg_win.mp3");
		loadBGM("sound_bg_opening.mp3");

		loadSFX("sound_fx_alien.mp3");
		loadSFX("sound_fx_x2.mp3");
		loadSFX("sound_fx_storm.mp3");
		loadSFX("sound_fx_eating_trash.mp3");
		loadSFX("sound_fx_on_click.mp3");
	}

	// Loads BGM into memory
	private static void loadBGM(String filename) {
		Media media = new Media(new File("res/" + filename).toURI().toString());
		MediaPlayer player = new MediaPlayer(media);
		player.setCycleCount(MediaPlayer.INDEFINITE);
		bgmPlayers.put(filename, player);
	}

	// Loads SFX into memory
	private static void loadSFX(String filename) {
		try {
			Media media = new Media(new File("res/" + filename).toURI().toString());
			sfxMedia.put(filename, media);
		} catch (Exception e) {
			System.err.println("ERROR: Cannot load SFX - " + filename);
		}
	}

	// Plays the specified BGM
	public static void playBGM(String filename) {
		if (!isBGMEnabled)
			return;

		if (currentBGM != null) {
			currentBGM.stop();
		}
		currentBGM = bgmPlayers.get(filename);
		if (currentBGM != null) {
			currentBGM.setMute(!isBGMEnabled);
			currentBGM.play();
		}
	}

	// Stops the currently playing BGM
	public static void stopBGM() {
		if (currentBGM != null) {
			currentBGM.stop();
		}
	}

	// Plays a sound effect
	public static void playSFX(String filename) {
		if (!isSFXEnabled)
			return;

		Media media = sfxMedia.get(filename);
		if (media != null) {
			MediaPlayer sfxPlayer = new MediaPlayer(media);
			sfxPlayer.setVolume(isSFXEnabled ? 1.0 : 0.0);
			sfxPlayer.setOnEndOfMedia(() -> sfxPlayer.dispose());
			sfxPlayer.play();
		}
	}

	// Checks if a specific BGM is currently playing
	public static boolean isBGMPlaying(String filename) {
		MediaPlayer player = bgmPlayers.get(filename);
		return player != null && player.getStatus() == MediaPlayer.Status.PLAYING;
	}

	// Sets the volume for BGM
	public static void setBGMVolume(double volume) {
		bgmVolume = volume;
		System.out.println("BGM Volume set to: " + bgmVolume);
	}

	// Gets the current BGM volume
	public static double getBGMVolume() {
		return bgmVolume;
	}

	// Sets the volume for SFX
	public static void setSFXVolume(double volume) {
		sfxVolume = volume;
		System.out.println("SFX Volume set to: " + sfxVolume);
	}

	// Gets the current SFX volume
	public static double getSFXVolume() {
		return sfxVolume;
	}

	// Enables or disables BGM
	public static void setBGMEnabled(boolean enabled) {
		isBGMEnabled = enabled;
		System.out.println("BGM " + (enabled ? "Enabled" : "Disabled"));

		if (currentBGM != null) {
			currentBGM.setMute(!isBGMEnabled);
		}
	}

	// Returns whether BGM is enabled
	public static boolean isBGMEnabled() {
		return isBGMEnabled;
	}

	// Enables or disables SFX
	public static void setSFXEnabled(boolean enabled) {
		isSFXEnabled = enabled;
		System.out.println("SFX " + (enabled ? "Enabled" : "Disabled"));
	}

	// Returns whether SFX is enabled
	public static boolean isSFXEnabled() {
		return isSFXEnabled;
	}
}
