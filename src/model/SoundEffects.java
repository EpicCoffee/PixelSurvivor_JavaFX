package model;

import controller.SpaceInvaderController;
import javafx.scene.media.AudioClip;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * This class handles the sound.
 *
 * @author Isabelle Romhagen, Ludvig Lundin, Khazar Mehraban
 * @version 1.2
 */

public class SoundEffects {

    private static Clip clip;

    /**
     * Checks if sound is on, and plays if true.
     * @param soundPath link to where the sound file is stored
     */
    public static void playSound(String soundPath) {
        if (SpaceInvaderController.getController().isSoundOn()) {
            AudioClip effect = new AudioClip(new File(soundPath).toURI().toString());
            effect.setVolume(0.2);
            effect.play();
        }
    }

    public static void loopSoundtrack() {
        if (SpaceInvaderController.getController().isSoundOn()) {
            try {
                File musicPath = new File(Constants.SOUNDTRACK);

                if (musicPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    clip = AudioSystem.getClip();
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else {
                    System.out.println("Can't find file...");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void stopMusic() {
        clip.stop();
    }

}
