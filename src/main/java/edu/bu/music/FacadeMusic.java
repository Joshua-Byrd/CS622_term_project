package edu.bu.music;

/**
 * Facade for the music package, providing a singleton access point to the music functionalities.
 */
public class FacadeMusic {
    private static FacadeMusic instance;

    // Private constructor to prevent instantiation
    private FacadeMusic() {
        // Initialize the music manager
        MusicManager.init();
    }

    /**
     * INTENT: To provide a global point of access to the FacadeMusic singleton instance.
     * PRECONDITION: None.
     * POSTCONDITION: Returns the singleton instance of FacadeMusic.
     *
     * @return The singleton instance of FacadeMusic.
     */
    public static synchronized FacadeMusic getTheInstance() {
        if (instance == null) {
            instance = new FacadeMusic();
        }
        return instance;
    }

    /**
     * INTENT: To play the ambient music.
     * PRECONDITION: None.
     * POSTCONDITION: The ambient music is played.
     */
    public void playAmbientMusic() {
        MusicManager.playAmbientMusic();
    }

    /**
     * INTENT: To play the battle music.
     * PRECONDITION: None.
     * POSTCONDITION: The battle music is played.
     */
    public void playBattleMusic() {
        MusicManager.playBattleMusic();
    }

    /**
     * INTENT: To play the logo music.
     * PRECONDITION: None.
     * POSTCONDITION: The logo music is played.
     */
    public void playLogoMusic() {
        MusicManager.playLogoMusic();
    }

    /**
     * INTENT: To stop all music.
     * PRECONDITION: None.
     * POSTCONDITION: All playing music is stopped.
     */
    public void stopMusic() {
        MusicManager.stopMusic();
    }
}
