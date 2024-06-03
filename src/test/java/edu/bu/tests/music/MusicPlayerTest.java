package edu.bu.tests.music;

import edu.bu.music.MusicPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {

    private MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer("src/main/resources/music/main_theme.mid");
    }

    @AfterEach
    void tearDown() {
        musicPlayer.stop();
    }

    @Test
    void testPlay() {
        musicPlayer.play();
        assertTrue(musicPlayer.isPlaying(), "Music should be playing after calling play()");
    }

    @Test
    void testStop() {
        musicPlayer.play();
        musicPlayer.stop();
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing after calling stop()");
    }

    @Test
    void testIsPlaying() {
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing initially");
        musicPlayer.play();
        assertTrue(musicPlayer.isPlaying(), "Music should be playing after calling play()");
        musicPlayer.stop();
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing after calling stop()");
    }

    @Test
    void testPlayInvalidFile() {
        MusicPlayer invalidPlayer = new MusicPlayer("invalid_path.mid");
        invalidPlayer.play();
        assertFalse(invalidPlayer.isPlaying(), "Music should not be playing for an invalid file path");
    }
}
