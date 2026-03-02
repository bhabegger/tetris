package tech.habegger.tetris.model;

import java.awt.Color;

/**
 * Represents a Tetromino piece in the Tetris game.
 * A Tetromino is composed of 4 blocks arranged in a specific shape.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class Tetromino {
    
    /** The shape of the tetromino represented as a 2D array (4x4 grid) */
    private int[][] shape;
    
    /** The x-coordinate position on the board */
    private int x;
    
    /** The y-coordinate position on the board */
    private int y;
    
    /** The color of the tetromino */
    private Color color;
    
    /**
     * Creates a new Tetromino with the specified shape, position, and color.
     * 
     * @param shape the 2D array representing the tetromino shape (4x4 grid)
     * @param x the initial x-coordinate position
     * @param y the initial y-coordinate position
     * @param color the color of the tetromino
     */
    public Tetromino(int[][] shape, int x, int y, Color color) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    /**
     * Creates an L-shaped tetromino at the specified position.
     * The L-piece is orange and positioned at the given coordinates.
     * 
     * @param x the initial x-coordinate position
     * @param y the initial y-coordinate position
     * @return a new L-shaped Tetromino
     */
    public static Tetromino createLPiece(int x, int y) {
        int[][] lShape = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        };
        return new Tetromino(lShape, x, y, Color.ORANGE);
    }
    
    /**
     * Rotates the tetromino 90 degrees clockwise.
     * Uses matrix transposition followed by row reversal.
     */
    public void rotate() {
        int size = shape.length;
        int[][] rotated = new int[size][size];
        
        // Transpose the matrix
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][i] = shape[i][j];
            }
        }
        
        // Reverse each row
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size / 2; j++) {
                int temp = rotated[i][j];
                rotated[i][j] = rotated[i][size - 1 - j];
                rotated[i][size - 1 - j] = temp;
            }
        }
        
        this.shape = rotated;
    }
    
    /**
     * Gets the shape of the tetromino.
     * 
     * @return the 2D array representing the shape
     */
    public int[][] getShape() {
        return shape;
    }
    
    /**
     * Gets the x-coordinate position.
     * 
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Gets the y-coordinate position.
     * 
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the color of the tetromino.
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Sets the x-coordinate position.
     * 
     * @param x the new x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Sets the y-coordinate position.
     *
     * @param y the new y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Moves the tetromino down by one row.
     */
    public void moveDown() {
        this.y++;
    }
}

