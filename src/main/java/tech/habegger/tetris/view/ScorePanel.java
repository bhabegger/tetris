package tech.habegger.tetris.view;

import tech.habegger.tetris.controller.GameController;

import javax.swing.*;
import java.awt.*;

/**
 * Panel that displays game statistics including score, lines cleared, and level.
 * Positioned to the right of the game board.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class ScorePanel extends JPanel {
    
    /** The game controller */
    private GameController gameController;
    
    /** Label for displaying the score */
    private JLabel scoreLabel;
    
    /** Label for displaying lines cleared */
    private JLabel linesLabel;
    
    /** Label for displaying the level */
    private JLabel levelLabel;
    
    /** Label for displaying game over message */
    private JLabel gameOverLabel;
    
    /** Width of the score panel */
    private static final int PANEL_WIDTH = 200;
    
    /** Height of the score panel */
    private static final int PANEL_HEIGHT = 600;
    
    /**
     * Creates a new ScorePanel.
     * 
     * @param gameController the game controller managing game state
     */
    public ScorePanel(GameController gameController) {
        this.gameController = gameController;
        
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(40, 40, 40));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Add title
        JLabel titleLabel = createLabel("TETRIS", 24, true);
        add(titleLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Add score section
        add(createLabel("SCORE", 14, true));
        scoreLabel = createLabel("0", 20, false);
        add(scoreLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add lines section
        add(createLabel("LINES", 14, true));
        linesLabel = createLabel("0", 20, false);
        add(linesLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Add level section
        add(createLabel("LEVEL", 14, true));
        levelLabel = createLabel("1", 20, false);
        add(levelLabel);
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Add controls section
        add(createLabel("CONTROLS", 14, true));
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(createLabel("← → Move", 12, false));
        add(createLabel("↓ Soft Drop", 12, false));
        add(createLabel("↑ X Rotate", 12, false));
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Add game over label (initially hidden)
        gameOverLabel = createLabel("GAME OVER", 18, true);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setVisible(false);
        add(gameOverLabel);
    }
    
    /**
     * Creates a styled label with the specified text and font size.
     * 
     * @param text the text to display
     * @param fontSize the font size
     * @param bold whether the font should be bold
     * @return the created label
     */
    private JLabel createLabel(String text, int fontSize, boolean bold) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        if (bold) {
            label.setFont(new Font("Arial", Font.BOLD, fontSize));
        } else {
            label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        }
        
        return label;
    }
    
    /**
     * Updates the score panel with current game statistics.
     * Should be called whenever the game state changes.
     */
    public void updateStats() {
        scoreLabel.setText(String.valueOf(gameController.getScore()));
        linesLabel.setText(String.valueOf(gameController.getLinesCleared()));
        
        // Calculate level based on lines cleared (every 10 lines = 1 level)
        int level = (gameController.getLinesCleared() / 10) + 1;
        levelLabel.setText(String.valueOf(level));
        
        // Show/hide game over message
        gameOverLabel.setVisible(gameController.isGameOver());
    }
    
    /**
     * Paints the component.
     * 
     * @param g the Graphics object to paint with
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateStats();
    }
}

