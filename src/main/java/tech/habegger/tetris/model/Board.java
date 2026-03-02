package tech.habegger.tetris.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Tetris game board.
 * Manages the grid of locked pieces and collision detection.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class Board {
    
    /** The width of the board in cells */
    public static final int WIDTH = 10;
    
    /** The height of the board in cells */
    public static final int HEIGHT = 20;
    
    /** The grid storing locked pieces (null = empty, Color = occupied) */
    private Color[][] grid;
    
    /**
     * Creates a new empty game board.
     */
    public Board() {
        grid = new Color[HEIGHT][WIDTH];
    }
    
    /**
     * Locks a tetromino piece onto the board.
     * Copies the piece's blocks into the board grid at its current position.
     * 
     * @param piece the tetromino to lock
     */
    public void lockPiece(Tetromino piece) {
        int[][] shape = piece.getShape();
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        Color color = piece.getColor();
        
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = pieceX + col;
                    int boardY = pieceY + row;
                    
                    if (boardY >= 0 && boardY < HEIGHT && boardX >= 0 && boardX < WIDTH) {
                        grid[boardY][boardX] = color;
                    }
                }
            }
        }
    }
    
    /**
     * Checks if a tetromino piece is at the bottom of the board.
     * A piece is at the bottom if it would collide with the bottom edge
     * or with locked pieces if moved down one more row.
     * 
     * @param piece the tetromino to check
     * @return true if the piece is at the bottom, false otherwise
     */
    public boolean isAtBottom(Tetromino piece) {
        int[][] shape = piece.getShape();
        int pieceX = piece.getX();
        int pieceY = piece.getY();
        
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = pieceX + col;
                    int boardY = pieceY + row + 1; // Check one row below
                    
                    // Check if it would hit the bottom edge
                    if (boardY >= HEIGHT) {
                        return true;
                    }
                    
                    // Check if it would hit a locked piece
                    if (boardY >= 0 && boardX >= 0 && boardX < WIDTH && grid[boardY][boardX] != null) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    /**
     * Gets the color of a cell on the board.
     * 
     * @param row the row index
     * @param col the column index
     * @return the color of the cell, or null if empty
     */
    public Color getCell(int row, int col) {
        if (row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH) {
            return grid[row][col];
        }
        return null;
    }
    
    /**
     * Gets the entire grid.
     * 
     * @return the 2D array representing the board grid
     */
    public Color[][] getGrid() {
        return grid;
    }
    
    /**
     * Clears the entire board.
     */
    public void clear() {
        grid = new Color[HEIGHT][WIDTH];
    }

    /**
     * Checks if a tetromino piece is in a valid position.
     * A position is valid if all blocks are within bounds and don't overlap locked pieces.
     *
     * @param piece the tetromino to check
     * @return true if the position is valid, false otherwise
     */
    public boolean isValidPosition(Tetromino piece) {
        int[][] shape = piece.getShape();
        int pieceX = piece.getX();
        int pieceY = piece.getY();

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    int boardX = pieceX + col;
                    int boardY = pieceY + row;

                    // Check if out of bounds horizontally
                    if (boardX < 0 || boardX >= WIDTH) {
                        return false;
                    }

                    // Check if out of bounds vertically (only check bottom, top is ok during spawn)
                    if (boardY >= HEIGHT) {
                        return false;
                    }

                    // Check if overlaps with locked piece (only if within bounds)
                    if (boardY >= 0 && grid[boardY][boardX] != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Finds all completed lines on the board.
     * A line is complete when all cells in the row are filled.
     *
     * @return list of row indices that are complete (from top to bottom)
     */
    public List<Integer> findCompletedLines() {
        List<Integer> completedLines = new ArrayList<>();

        for (int row = 0; row < HEIGHT; row++) {
            if (isLineComplete(row)) {
                completedLines.add(row);
            }
        }

        return completedLines;
    }

    /**
     * Checks if a specific line is complete.
     *
     * @param row the row index to check
     * @return true if all cells in the row are filled, false otherwise
     */
    private boolean isLineComplete(int row) {
        for (int col = 0; col < WIDTH; col++) {
            if (grid[row][col] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clears the specified lines and shifts rows above down.
     *
     * @param lines list of row indices to clear
     * @return the number of lines cleared
     */
    public int clearLines(List<Integer> lines) {
        if (lines.isEmpty()) {
            return 0;
        }

        // Create a new grid with non-completed lines
        Color[][] newGrid = new Color[HEIGHT][WIDTH];
        int newRow = HEIGHT - 1; // Start from bottom of new grid

        // Copy non-completed lines from bottom to top
        for (int oldRow = HEIGHT - 1; oldRow >= 0; oldRow--) {
            if (!lines.contains(oldRow)) {
                // This row is not completed, copy it to new grid
                newGrid[newRow] = grid[oldRow].clone();
                newRow--;
            }
        }

        // Fill remaining top rows with empty rows
        while (newRow >= 0) {
            newGrid[newRow] = new Color[WIDTH];
            newRow--;
        }

        // Replace the grid
        grid = newGrid;

        return lines.size();
    }
}

