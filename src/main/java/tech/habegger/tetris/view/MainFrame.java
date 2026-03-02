package tech.habegger.tetris.view;

import tech.habegger.tetris.controller.GameController;

import javax.swing.JFrame;
import javax.swing.Timer;
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

    /** The game controller */
    private GameController gameController;

    /** The game loop timer */
    private Timer gameTimer;

    /** The delay between game ticks in milliseconds */
    private static final int TICK_DELAY = 500;

    /**
     * Creates a new MainFrame and sets up the window.
     */
    public MainFrame() {
        setTitle("Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create game controller
        gameController = new GameController();

        // Create and add the game panel
        gamePanel = new GamePanel(gameController);
        add(gamePanel);

        // Add keyboard listener for controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gameController.moveLeft();
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        gameController.moveRight();
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        gameController.softDrop();
                        gamePanel.repaint();
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_X:
                        gameController.rotatePiece();
                        gamePanel.repaint();
                        break;
                }
            }
        });

        // Set up the game loop timer
        gameTimer = new Timer(TICK_DELAY, e -> {
            if (!gameController.isGameOver()) {
                gameController.tick();
                gamePanel.repaint();
            }
        });

        // Pack the frame to fit the preferred size of components
        pack();

        // Center the window on screen
        setLocationRelativeTo(null);

        // Start the game loop
        gameTimer.start();
    }

    /**
     * Gets the game panel.
     *
     * @return the game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Gets the game controller.
     *
     * @return the game controller
     */
    public GameController getGameController() {
        return gameController;
    }
}

