package tech.habegger.tetris.model;

import java.awt.Color;

/**
 * Enumeration of all Tetromino types in Tetris.
 * Each type has a unique shape and color.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public enum TetrominoType {
    
    /** I-piece (cyan, straight line) */
    I(new int[][]{
        {0, 0, 0, 0},
        {1, 1, 1, 1},
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    }, Color.CYAN),
    
    /** O-piece (yellow, square) */
    O(new int[][]{
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }, Color.YELLOW),
    
    /** T-piece (purple, T-shape) */
    T(new int[][]{
        {0, 0, 0, 0},
        {0, 1, 1, 1},
        {0, 0, 1, 0},
        {0, 0, 0, 0}
    }, new Color(128, 0, 128)), // Purple
    
    /** S-piece (green, S-shape) */
    S(new int[][]{
        {0, 0, 0, 0},
        {0, 0, 1, 1},
        {0, 1, 1, 0},
        {0, 0, 0, 0}
    }, Color.GREEN),
    
    /** Z-piece (red, Z-shape) */
    Z(new int[][]{
        {0, 0, 0, 0},
        {0, 1, 1, 0},
        {0, 0, 1, 1},
        {0, 0, 0, 0}
    }, Color.RED),
    
    /** J-piece (blue, J-shape) */
    J(new int[][]{
        {0, 0, 0, 0},
        {0, 1, 0, 0},
        {0, 1, 1, 1},
        {0, 0, 0, 0}
    }, Color.BLUE),
    
    /** L-piece (orange, L-shape) */
    L(new int[][]{
        {0, 0, 1, 0},
        {0, 0, 1, 0},
        {0, 0, 1, 1},
        {0, 0, 0, 0}
    }, Color.ORANGE);
    
    /** The shape matrix for this tetromino type */
    private final int[][] shape;
    
    /** The color for this tetromino type */
    private final Color color;
    
    /**
     * Creates a tetromino type with the specified shape and color.
     * 
     * @param shape the 4x4 shape matrix
     * @param color the color
     */
    TetrominoType(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }
    
    /**
     * Gets the shape matrix for this tetromino type.
     * Returns a copy to prevent modification.
     * 
     * @return a copy of the shape matrix
     */
    public int[][] getShape() {
        int[][] copy = new int[shape.length][];
        for (int i = 0; i < shape.length; i++) {
            copy[i] = shape[i].clone();
        }
        return copy;
    }
    
    /**
     * Gets the color for this tetromino type.
     * 
     * @return the color
     */
    public Color getColor() {
        return color;
    }
}

