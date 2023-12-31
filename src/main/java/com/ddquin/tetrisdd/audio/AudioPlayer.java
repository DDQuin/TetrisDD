package com.ddquin.tetrisdd.audio;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;

import com.ddquin.tetrisdd.util.Util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class AudioPlayer {

    private Clip clip;

    public AudioPlayer(String s) {

        try {


            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Util.getFileFromResourceAsStream(s)));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(

                            decodeFormat, ais

                    );
            clip = AudioSystem.getClip();
            clip.open(dais);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Audio not in correct format, wont be playing");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AudioPlayer() {

    }

    public void setSound(String s) {
        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(Util.getFileFromResourceAsStream(s)));
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_UNSIGNED,
                    baseFormat.getSampleRate(),
                    8,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 1,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais =
                    AudioSystem.getAudioInputStream(

                            decodeFormat, ais

                    );
            clip = AudioSystem.getClip();
            clip.open(dais);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Audio not in correct format, wont be playing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void play() {
        if (clip == null) return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }

    public void stop() {
        if (clip != null && clip.isRunning()) clip.stop();
    }

    public void close() {
        stop();
        clip.close();
    }

}

