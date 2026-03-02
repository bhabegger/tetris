package tech.habegger.tetris.view;

import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The main window frame for the Tetris game.
 * Contains the game panel and handles window setup.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class MainFrame extends JFrame {
    
    /** The game panel that displays the game */
    private GamePanel gamePanel;
    
    /**
     * Creates a new MainFrame and sets up the window.
     */
    public MainFrame() {
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Create and add the game panel
        gamePanel = new GamePanel();
        add(gamePanel);
        
        // Add keyboard listener for rotation
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_X) {
                    // Rotate the piece
                    if (gamePanel.getCurrentPiece() != null) {
                        gamePanel.getCurrentPiece().rotate();
                        gamePanel.repaint();
                    }
                }
            }
        });
        
        // Pack the frame to fit the preferred size of components
        pack();
        
        // Center the window on screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Gets the game panel.
     * 
     * @return the game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}

