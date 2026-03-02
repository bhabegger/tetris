package tech.habegger.tetris.view;

import tech.habegger.tetris.model.Tetromino;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * The main game panel that renders the Tetris game board and pieces.
 * Extends JPanel to provide custom rendering using Java 2D Graphics.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class GamePanel extends JPanel {
    
    /** The size of each cell in pixels */
    private static final int CELL_SIZE = 30;
    
    /** The width of the game board in cells */
    private static final int BOARD_WIDTH = 10;
    
    /** The height of the game board in cells */
    private static final int BOARD_HEIGHT = 20;
    
    /** The current tetromino piece being displayed */
    private Tetromino currentPiece;
    
    /**
     * Creates a new GamePanel with the specified dimensions.
     * Sets up the panel size based on board dimensions and cell size.
     */
    public GamePanel() {
        setPreferredSize(new Dimension(
            BOARD_WIDTH * CELL_SIZE,
            BOARD_HEIGHT * CELL_SIZE
        ));
        setBackground(Color.BLACK);
        
        // Create an L-piece in the center of the board
        currentPiece = Tetromino.createLPiece(3, 8);
    }
    
    /**
     * Sets the current tetromino piece to be displayed.
     * 
     * @param piece the tetromino to display
     */
    public void setCurrentPiece(Tetromino piece) {
        this.currentPiece = piece;
    }
    
    /**
     * Gets the current tetromino piece.
     * 
     * @return the current tetromino
     */
    public Tetromino getCurrentPiece() {
        return currentPiece;
    }
    
    /**
     * Custom paint method to render the game board and tetromino.
     * 
     * @param g the Graphics object to paint with
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Draw the grid
        drawGrid(g2d);
        
        // Draw the current piece
        if (currentPiece != null) {
            drawTetromino(g2d, currentPiece);
        }
    }
    
    /**
     * Draws the game board grid.
     * 
     * @param g2d the Graphics2D object to draw with
     */
    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(Color.DARK_GRAY);
        
        // Draw vertical lines
        for (int x = 0; x <= BOARD_WIDTH; x++) {
            g2d.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE);
        }
        
        // Draw horizontal lines
        for (int y = 0; y <= BOARD_HEIGHT; y++) {
            g2d.drawLine(0, y * CELL_SIZE, BOARD_WIDTH * CELL_SIZE, y * CELL_SIZE);
        }
    }
    
    /**
     * Draws a tetromino piece on the board.
     * 
     * @param g2d the Graphics2D object to draw with
     * @param piece the tetromino to draw
     */
    private void drawTetromino(Graphics2D g2d, Tetromino piece) {
        int[][] shape = piece.getShape();
        Color color = piece.getColor();
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        
        // Draw each block of the tetromino
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int x = (pieceX + col) * CELL_SIZE;
                    int y = (pieceY + row) * CELL_SIZE;
                    
                    // Fill the block with color
                    g2d.setColor(color);
                    g2d.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    
                    // Draw a border around the block
                    g2d.setColor(color.darker());
                    g2d.drawRect(x, y, CELL_SIZE - 1, CELL_SIZE - 1);
                }
            }
        }
    }
    
    /**
     * Gets the cell size in pixels.
     * 
     * @return the cell size
     */
    public static int getCellSize() {
        return CELL_SIZE;
    }
    
    /**
     * Gets the board width in cells.
     * 
     * @return the board width
     */
    public static int getBoardWidth() {
        return BOARD_WIDTH;
    }
    
    /**
     * Gets the board height in cells.
     * 
     * @return the board height
     */
    public static int getBoardHeight() {
        return BOARD_HEIGHT;
    }
}

