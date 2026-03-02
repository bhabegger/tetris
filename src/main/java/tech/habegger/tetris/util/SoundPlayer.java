package tech.habegger.tetris.util;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Utility class for playing sound effects in the Tetris game.
 * Uses Java's Clip API to play short audio clips.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class SoundPlayer {
    
    /** Sound effect types */
    public enum SoundEffect {
        PIECE_LOCK,
        LINE_CLEAR,
        GAME_OVER
    }
    
    /**
     * Plays a sound effect.
     * Uses system beep for now as a simple implementation.
     * 
     * @param effect the sound effect to play
     */
    public static void play(SoundEffect effect) {
        // For now, use different beep patterns for different sounds
        // In a production version, this would load and play actual audio files
        
        new Thread(() -> {
            try {
                switch (effect) {
                    case PIECE_LOCK:
                        // Single short beep for piece locking
                        playBeep(100, 800);
                        break;
                    case LINE_CLEAR:
                        // Two quick beeps for line clear
                        playBeep(80, 1000);
                        Thread.sleep(50);
                        playBeep(80, 1200);
                        break;
                    case GAME_OVER:
                        // Descending beeps for game over
                        playBeep(150, 800);
                        Thread.sleep(100);
                        playBeep(150, 600);
                        Thread.sleep(100);
                        playBeep(300, 400);
                        break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    
    /**
     * Plays a beep tone using Java's AudioSystem.
     * 
     * @param duration duration in milliseconds
     * @param frequency frequency in Hz
     */
    private static void playBeep(int duration, int frequency) {
        try {
            // Generate a simple sine wave tone
            int sampleRate = 8000;
            int numSamples = duration * sampleRate / 1000;
            byte[] buffer = new byte[numSamples];
            
            for (int i = 0; i < numSamples; i++) {
                double angle = 2.0 * Math.PI * i * frequency / sampleRate;
                buffer[i] = (byte) (Math.sin(angle) * 127.0);
            }
            
            // Create audio format
            AudioFormat format = new AudioFormat(sampleRate, 8, 1, true, false);
            
            // Create and play the clip
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            if (AudioSystem.isLineSupported(info)) {
                SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                line.open(format);
                line.start();
                line.write(buffer, 0, buffer.length);
                line.drain();
                line.close();
            }
        } catch (LineUnavailableException e) {
            // Silently fail if audio is not available
            System.err.println("Audio not available: " + e.getMessage());
        }
    }
    
    /**
     * Loads and plays a WAV file from resources.
     * This method can be used in the future to play actual sound files.
     * 
     * @param resourcePath path to the WAV file in resources
     */
    public static void playWavFile(String resourcePath) {
        try {
            URL soundURL = SoundPlayer.class.getResource(resourcePath);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + resourcePath);
                return;
            }
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
            
            // Close resources after playing
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }
}

