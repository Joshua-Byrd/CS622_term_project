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

//    @Test
//    void testPlayAmbientMusic() {
//        MusicManager.playAmbientMusic();
//        assertTrue(MusicManager.ambientPlayer.isPlaying(), "Ambient music should be playing");
//        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing");
//        assertFalse(MusicManager.logoPlayer.isPlaying(), "Logo music should not be playing");
//    }
//
//    @Test
//    void testPlayBattleMusic() {
//        MusicManager.playBattleMusic();
//        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing");
//        assertTrue(MusicManager.battlePlayer.isPlaying(), "Battle music should be playing");
//        assertFalse(MusicManager.logoPlayer.isPlaying(), "Logo music should not be playing");
//    }
//
//    @Test
//    void testPlayLogoMusic() {
//        MusicManager.playLogoMusic();
//        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing");
//        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing");
//        assertTrue(MusicManager.logoPlayer.isPlaying(), "Logo music should be playing");
//    }
//
//    @Test
//    void testStopMusic() {
//        MusicManager.playAmbientMusic();
//        MusicManager.stopMusic();
//        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing after stop");
//
//        MusicManager.playBattleMusic();
//        MusicManager.stopMusic();
//        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing after stop");
//    }
//
//    @Test
//    void testSwitchMusic() {
//        MusicManager.playAmbientMusic();
//        assertTrue(MusicManager.ambientPlayer.isPlaying(), "Ambient music should be playing");
//        MusicManager.playBattleMusic();
//        assertFalse(MusicManager.ambientPlayer.isPlaying(), "Ambient music should not be playing after switching to battle music");
//        assertTrue(MusicManager.battlePlayer.isPlaying(), "Battle music should be playing");
//
//        MusicManager.playLogoMusic();
//        assertFalse(MusicManager.battlePlayer.isPlaying(), "Battle music should not be playing after switching to logo music");
//        assertTrue(MusicManager.logoPlayer.isPlaying(), "Logo music should be playing");
//    }
}
