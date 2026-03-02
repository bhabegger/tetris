package tech.habegger.tetris;

import tech.habegger.tetris.view.MainFrame;

import javax.swing.SwingUtilities;

/**
 * Main entry point for the Tetris game application.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class Main {
    
    /**
     * Main method to start the Tetris game.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

