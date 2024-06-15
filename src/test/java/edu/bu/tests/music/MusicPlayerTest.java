package edu.bu.tests.music;

import edu.bu.music.MusicPlayer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {

    private MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer("music/main_theme.mid");
    }

    @AfterEach
    void tearDown() {
        musicPlayer.stop();
    }

    @Test
    void testPlay() {
        musicPlayer.play();
        // Wait briefly to ensure the music starts playing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(musicPlayer.isPlaying(), "Music should be playing after calling play()");
    }

    @Test
    void testStop() {
        musicPlayer.play();
        // Wait briefly to ensure the music starts playing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        musicPlayer.stop();
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing after calling stop()");
    }

    @Test
    void testIsPlaying() {
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing initially");
        musicPlayer.play();
        // Wait briefly to ensure the music starts playing
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(musicPlayer.isPlaying(), "Music should be playing after calling play()");
        musicPlayer.stop();
        assertFalse(musicPlayer.isPlaying(), "Music should not be playing after calling stop()");
    }
}

