package edu.bu.music;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages the playback of background music in the game. It provides methods for the playing of music tracks in
 * their own threads, as well as stopping and switching music tracks
 */
public class MusicManager {
    //single-thread executor because we're only running a single music file at a time
    //so it's essentially just a single background task
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();
    public static MusicPlayer ambientPlayer;
    public static MusicPlayer battlePlayer;
    public static MusicPlayer logoPlayer;

    /**
     * INTENT: To initialize the music players with the respective music files.
     * PRECONDITION: The specified music files must exist at the provided paths.
     * POSTCONDITION: The music players are initialized and ready to play the respective music tracks.
     */
    public static void init() {
        ambientPlayer = new MusicPlayer("music/main_theme.mid");
        battlePlayer = new MusicPlayer("music/battle_theme.mid");
        logoPlayer = new MusicPlayer("music/logo_theme.mid");
    }

    /**
     * INTENT: To play the ambient music track, stopping any other playing tracks.
     * PRECONDITION: The ambientPlayer must be properly initialized.
     * POSTCONDITION: The ambient music track is played in a loop in its own thread
     */
    public static void playAmbientMusic() {
        if (battlePlayer.isPlaying()) {
            battlePlayer.stop();
        } else if (logoPlayer.isPlaying()) {
            logoPlayer.stop();
        }
        if (!ambientPlayer.isPlaying()) {
            executor.execute(ambientPlayer::play);
        }
    }

    /**
     * INTENT: To play the battle music track, stopping any other playing tracks.
     * PRECONDITION: The battlePlayer must be properly initialized.
     * POSTCONDITION: The battle music track is played in a loop in its own thread.
     */
    public static void playBattleMusic() {
        if (ambientPlayer.isPlaying()) {
            ambientPlayer.stop();
        } else if (logoPlayer.isPlaying()) {
            logoPlayer.stop();
        }

        if (!battlePlayer.isPlaying()) {
            executor.execute(battlePlayer::play);
        }
    }

    /**
     * INTENT: To play the logo music track, stopping any other playing tracks.
     * PRECONDITION: The logoPlayer must be properly initialized.
     * POSTCONDITION: The logo music track is played in a loop in its own thread.
     */
    public static void playLogoMusic() {
        if (ambientPlayer.isPlaying()) {
            ambientPlayer.stop();
        } else if (battlePlayer.isPlaying()) {
            battlePlayer.stop();
        }

        if (!logoPlayer.isPlaying()) {
            executor.execute(logoPlayer::play);
        }
    }

    /**
     * INTENT: To stop any currently playing music tracks.
     * PRECONDITION: None.
     * POSTCONDITION: All currently playing music tracks are stopped.
     */
    public static void stopMusic() {
        if (ambientPlayer.isPlaying()) {
            ambientPlayer.stop();
        }
        if (battlePlayer.isPlaying()) {
            battlePlayer.stop();
        }
        if (logoPlayer.isPlaying()) {
            logoPlayer.stop();
        }
    }
}
