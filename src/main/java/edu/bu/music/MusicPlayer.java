package edu.bu.music;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

/**
 * A class to handle the playback of MIDI music files. This class provides methods to play, stop,
 * and check the status of music playback.
 */
public class MusicPlayer {
    private Sequencer sequencer;
    private String filePath;

    /**
     * INTENT: To create a MusicPlayer instance with the specified MIDI file path.
     * PRECONDITION: The filePath must be a valid path to a MIDI file.
     * POSTCONDITION: A MusicPlayer instance is created with the specified file path.
     *
     * @param aFilePath The path to the MIDI file to be played.
     */
    public MusicPlayer(String aFilePath) {
        this.filePath = aFilePath;
    }

    /**
     * INTENT: To play the MIDI music file specified during the creation of the MusicPlayer instance.
     * PRECONDITION: The filePath must be a valid path to a readable MIDI file.
     * POSTCONDITION: The MIDI file is played in a loop.
     */
    public void play() {
        try {
            File midiFile = new File(filePath);
            sequencer = MidiSystem.getSequencer();
            sequencer.setSequence(MidiSystem.getSequence(midiFile));
            sequencer.open();

            // Loop the music
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
        } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * INTENT: To stop the playback of the MIDI music.
     * PRECONDITION: None.
     * POSTCONDITION: The MIDI music playback is stopped and the sequencer is closed.
     */
    public void stop() {
        if (sequencer != null && sequencer.isRunning()) {
            sequencer.stop();
            sequencer.close();
        }
    }

    /**
     * INTENT: To check if the MIDI music is currently playing.
     * PRECONDITION: None.
     * POSTCONDITION: Returns true if the MIDI music is playing, false otherwise.
     *
     * @return true if the music is playing, false otherwise.
     */
    public boolean isPlaying() {
        return sequencer != null && sequencer.isRunning();
    }
}

