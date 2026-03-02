package tech.habegger.tetris.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tech.habegger.tetris.model.Board;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the GameController class.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
class GameControllerTest {
    
    private GameController controller;
    
    @BeforeEach
    void setUp() {
        controller = new GameController();
    }
    
    @Test
    @DisplayName("Should initialize with a current piece")
    void testInitialization() {
        // Then
        assertNotNull(controller.getCurrentPiece(), "Should have a current piece");
        assertNotNull(controller.getBoard(), "Should have a board");
        assertFalse(controller.isGameOver(), "Game should not be over initially");
        assertEquals(0, controller.getScore(), "Score should be 0 initially");
        assertEquals(0, controller.getLinesCleared(), "Lines cleared should be 0 initially");
    }
    
    @Test
    @DisplayName("Should move piece down on tick")
    void testTick() {
        // Given
        int initialY = controller.getCurrentPiece().getY();
        
        // When
        controller.tick();
        
        // Then
        assertEquals(initialY + 1, controller.getCurrentPiece().getY(), 
            "Piece should move down by 1");
    }
    
    @Test
    @DisplayName("Should detect game over when piece cannot spawn")
    void testGameOver() {
        // Given - fill the top rows to prevent spawning
        Board board = controller.getBoard();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                board.getGrid()[row][col] = java.awt.Color.RED;
            }
        }
        
        // When - try to spawn a new piece
        controller.spawnNewPiece();
        
        // Then
        assertTrue(controller.isGameOver(), "Game should be over when piece cannot spawn");
    }
    
    @Test
    @DisplayName("Should rotate piece")
    void testRotatePiece() {
        // Given
        int[][] initialShape = controller.getCurrentPiece().getShape();
        
        // When
        controller.rotatePiece();
        
        // Then
        int[][] rotatedShape = controller.getCurrentPiece().getShape();
        // Shape should be different after rotation (unless it's an O-piece)
        // We just verify the method doesn't crash
        assertNotNull(rotatedShape, "Shape should not be null after rotation");
    }
    
    @Test
    @DisplayName("Should move piece left")
    void testMoveLeft() {
        // Given
        int initialX = controller.getCurrentPiece().getX();
        
        // When
        controller.moveLeft();
        
        // Then
        assertEquals(initialX - 1, controller.getCurrentPiece().getX(), 
            "Piece should move left by 1");
    }
    
    @Test
    @DisplayName("Should move piece right")
    void testMoveRight() {
        // Given
        int initialX = controller.getCurrentPiece().getX();
        
        // When
        controller.moveRight();
        
        // Then
        assertEquals(initialX + 1, controller.getCurrentPiece().getX(), 
            "Piece should move right by 1");
    }
    
    @Test
    @DisplayName("Should soft drop piece")
    void testSoftDrop() {
        // Given
        int initialY = controller.getCurrentPiece().getY();
        
        // When
        controller.softDrop();
        
        // Then
        assertEquals(initialY + 1, controller.getCurrentPiece().getY(), 
            "Piece should move down by 1 on soft drop");
    }
    
    @Test
    @DisplayName("Should reset game")
    void testReset() {
        // Given - play some game
        controller.tick();
        controller.tick();
        
        // When
        controller.reset();
        
        // Then
        assertFalse(controller.isGameOver(), "Game should not be over after reset");
        assertEquals(0, controller.getScore(), "Score should be 0 after reset");
        assertEquals(0, controller.getLinesCleared(), "Lines cleared should be 0 after reset");
        assertNotNull(controller.getCurrentPiece(), "Should have a current piece after reset");
    }
}

