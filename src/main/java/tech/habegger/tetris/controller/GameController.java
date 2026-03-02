package tech.habegger.tetris.controller;

import tech.habegger.tetris.model.Board;
import tech.habegger.tetris.model.Tetromino;

/**
 * Controls the game logic and state for Tetris.
 * Manages the current piece, board, and game flow.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
public class GameController {
    
    /** The game board */
    private Board board;
    
    /** The current falling piece */
    private Tetromino currentPiece;
    
    /** Flag indicating if the game is over */
    private boolean gameOver;
    
    /**
     * Creates a new GameController and initializes the game.
     */
    public GameController() {
        board = new Board();
        spawnNewPiece();
        gameOver = false;
    }
    
    /**
     * Spawns a new tetromino piece at the top center of the board.
     */
    public void spawnNewPiece() {
        currentPiece = Tetromino.createLPiece(3, 0);
        
        // Check if the new piece collides with locked pieces (game over condition)
        if (board.isAtBottom(currentPiece)) {
            gameOver = true;
        }
    }
    
    /**
     * Updates the game state by one tick.
     * Moves the current piece down, and locks it if it reaches the bottom.
     */
    public void tick() {
        if (gameOver) {
            return;
        }
        
        if (board.isAtBottom(currentPiece)) {
            // Lock the piece and spawn a new one
            board.lockPiece(currentPiece);
            spawnNewPiece();
        } else {
            // Move the piece down
            currentPiece.moveDown();
        }
    }
    
    /**
     * Rotates the current piece if the rotation is valid.
     */
    public void rotatePiece() {
        if (!gameOver && currentPiece != null) {
            currentPiece.rotate();
            // If rotation causes collision, rotate back
            if (!board.isValidPosition(currentPiece)) {
                // Rotate back 3 times (equivalent to rotating back once)
                currentPiece.rotate();
                currentPiece.rotate();
                currentPiece.rotate();
            }
        }
    }

    /**
     * Moves the current piece left if the move is valid.
     */
    public void moveLeft() {
        if (!gameOver && currentPiece != null) {
            currentPiece.moveLeft();
            // If move causes collision, move back
            if (!board.isValidPosition(currentPiece)) {
                currentPiece.moveRight();
            }
        }
    }

    /**
     * Moves the current piece right if the move is valid.
     */
    public void moveRight() {
        if (!gameOver && currentPiece != null) {
            currentPiece.moveRight();
            // If move causes collision, move back
            if (!board.isValidPosition(currentPiece)) {
                currentPiece.moveLeft();
            }
        }
    }

    /**
     * Moves the current piece down faster (soft drop).
     */
    public void softDrop() {
        if (!gameOver && currentPiece != null) {
            if (!board.isAtBottom(currentPiece)) {
                currentPiece.moveDown();
            }
        }
    }
    
    /**
     * Gets the current falling piece.
     * 
     * @return the current tetromino
     */
    public Tetromino getCurrentPiece() {
        return currentPiece;
    }
    
    /**
     * Gets the game board.
     * 
     * @return the board
     */
    public Board getBoard() {
        return board;
    }
    
    /**
     * Checks if the game is over.
     * 
     * @return true if game is over, false otherwise
     */
    public boolean isGameOver() {
        return gameOver;
    }
    
    /**
     * Resets the game to initial state.
     */
    public void reset() {
        board.clear();
        spawnNewPiece();
        gameOver = false;
    }
}

