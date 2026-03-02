package tech.habegger.tetris.controller;

import tech.habegger.tetris.model.Board;
import tech.habegger.tetris.model.Tetromino;

import java.util.List;

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

    /** The current score */
    private int score;

    /** The number of lines cleared */
    private int linesCleared;

    /**
     * Creates a new GameController and initializes the game.
     */
    public GameController() {
        board = new Board();
        spawnNewPiece();
        gameOver = false;
        score = 0;
        linesCleared = 0;
    }

    /**
     * Spawns a new tetromino piece at the top center of the board.
     * Uses random piece selection.
     */
    public void spawnNewPiece() {
        currentPiece = Tetromino.createRandom(3, 0);

        // Check if the new piece collides with locked pieces (game over condition)
        if (board.isAtBottom(currentPiece)) {
            gameOver = true;
        }
    }
    
    /**
     * Updates the game state by one tick.
     * Moves the current piece down, and locks it if it reaches the bottom.
     * Clears completed lines and updates score.
     */
    public void tick() {
        if (gameOver) {
            return;
        }

        if (board.isAtBottom(currentPiece)) {
            // Lock the piece
            board.lockPiece(currentPiece);

            // Check for and clear completed lines
            List<Integer> completedLines = board.findCompletedLines();
            if (!completedLines.isEmpty()) {
                int cleared = board.clearLines(completedLines);
                linesCleared += cleared;
                updateScore(cleared);
            }

            // Spawn a new piece
            spawnNewPiece();
        } else {
            // Move the piece down
            currentPiece.moveDown();
        }
    }

    /**
     * Updates the score based on the number of lines cleared.
     * Scoring: 1 line = 100, 2 lines = 300, 3 lines = 500, 4 lines = 800
     *
     * @param lines the number of lines cleared
     */
    private void updateScore(int lines) {
        switch (lines) {
            case 1:
                score += 100;
                break;
            case 2:
                score += 300;
                break;
            case 3:
                score += 500;
                break;
            case 4:
                score += 800;
                break;
            default:
                score += lines * 100;
                break;
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
        score = 0;
        linesCleared = 0;
    }

    /**
     * Gets the current score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the number of lines cleared.
     *
     * @return the lines cleared
     */
    public int getLinesCleared() {
        return linesCleared;
    }
}

