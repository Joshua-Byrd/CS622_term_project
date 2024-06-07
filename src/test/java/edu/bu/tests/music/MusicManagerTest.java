package edu.bu.tests.music;

import edu.bu.music.MusicManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicManagerTest {

    @BeforeEach
    void setUp() {
        MusicManager.init();
    }

    @AfterEach
    void tearDown() {
        MusicManager.stopMusic();
    }

    private void waitForMusicToStart() {
        try {
            Thread.sleep(1000); // Wait for 500ms to let the music start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPlayAmbientMusic() {
        MusicManager.playAmbientMusic();
        waitForMusicToStart();
        assertTrue(MusicManager.ambientPlayer.isPlaying(), "Ambient music should be playing");
        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing");
        assertFalse(MusicManager.logoPlayer.isPlaying(), "Logo music should not be playing");
    }

    @Test
    void testPlayBattleMusic() {
        MusicManager.playBattleMusic();
        waitForMusicToStart();
        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing");
        assertTrue(MusicManager.battlePlayer.isPlaying(), "Battle music should be playing");
        assertFalse(MusicManager.logoPlayer.isPlaying(), "Logo music should not be playing");
    }

    @Test
    void testPlayLogoMusic() {
        MusicManager.playLogoMusic();
        waitForMusicToStart();
        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing");
        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing");
        assertTrue(MusicManager.logoPlayer.isPlaying(), "Logo music should be playing");
    }

    @Test
    void testStopMusic() {
        MusicManager.playAmbientMusic();
        waitForMusicToStart();
        MusicManager.stopMusic();
        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing after stop");

        MusicManager.playBattleMusic();
        waitForMusicToStart();
        MusicManager.stopMusic();
        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing after stop");
    }

    @Test
    void testSwitchMusic() {
        MusicManager.playAmbientMusic();
        waitForMusicToStart();
        assertTrue(MusicManager.ambientPlayer.isPlaying(), "Ambient music should be playing");
        MusicManager.playBattleMusic();
        waitForMusicToStart();
        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing after switching to battle music");
        assertTrue(MusicManager.battlePlayer.isPlaying(), "Battle music should be playing");

        MusicManager.playLogoMusic();
        waitForMusicToStart();
        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing after switching to logo music");
        assertTrue(MusicManager.logoPlayer.isPlaying(), "Logo music should be playing");
    }
}

