package edu.softwarica.game.utils;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Assets {

    public static boolean canPlay = true;

    public static void toggleMusic() {
        if (canPlay) {
            canPlay = false;
        } else {
            canPlay = true;
        }
    }

    public void playSound(String path) {
        if (!canPlay) {
            return;
        }
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
