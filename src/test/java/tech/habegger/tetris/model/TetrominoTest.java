package tech.habegger.tetris.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Tetromino class.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
class TetrominoTest {
    
    private Tetromino lPiece;
    
    @BeforeEach
    void setUp() {
        lPiece = Tetromino.createLPiece(3, 0);
    }
    
    @Test
    @DisplayName("Should create L-piece with correct initial position")
    void testCreateLPiece() {
        // Given - setup in @BeforeEach
        
        // When
        int x = lPiece.getX();
        int y = lPiece.getY();
        Color color = lPiece.getColor();
        
        // Then
        assertEquals(3, x, "L-piece should be at x=3");
        assertEquals(0, y, "L-piece should be at y=0");
        assertEquals(Color.ORANGE, color, "L-piece should be orange");
        assertNotNull(lPiece.getShape(), "L-piece should have a shape");
    }
    
    @Test
    @DisplayName("Should have correct L-shape pattern")
    void testLPieceShape() {
        // Given
        int[][] expectedShape = {
            {0, 0, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 1},
            {0, 0, 0, 0}
        };
        
        // When
        int[][] actualShape = lPiece.getShape();
        
        // Then
        assertArrayEquals(expectedShape, actualShape, "L-piece should have correct shape");
    }
    
    @Test
    @DisplayName("Should rotate L-piece 90 degrees clockwise")
    void testRotate() {
        // Given
        // Original L-piece:
        // 0 0 1 0
        // 0 0 1 0
        // 0 0 1 1
        // 0 0 0 0
        //
        // After 90° clockwise rotation:
        // 0 0 0 0
        // 0 0 0 0
        // 0 1 1 1
        // 0 1 0 0
        int[][] expectedShapeAfterRotation = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 1},
            {0, 1, 0, 0}
        };

        // When
        lPiece.rotate();
        int[][] actualShape = lPiece.getShape();

        // Then
        assertArrayEquals(expectedShapeAfterRotation, actualShape,
            "L-piece should rotate 90 degrees clockwise");
    }
    
    @Test
    @DisplayName("Should return to original shape after 4 rotations")
    void testFourRotations() {
        // Given
        int[][] originalShape = copyShape(lPiece.getShape());
        
        // When
        lPiece.rotate();
        lPiece.rotate();
        lPiece.rotate();
        lPiece.rotate();
        int[][] finalShape = lPiece.getShape();
        
        // Then
        assertArrayEquals(originalShape, finalShape, 
            "L-piece should return to original shape after 4 rotations");
    }
    
    @Test
    @DisplayName("Should update position when setters are called")
    void testSetPosition() {
        // Given
        int newX = 5;
        int newY = 10;

        // When
        lPiece.setX(newX);
        lPiece.setY(newY);

        // Then
        assertEquals(newX, lPiece.getX(), "X position should be updated");
        assertEquals(newY, lPiece.getY(), "Y position should be updated");
    }

    @Test
    @DisplayName("Should move piece down")
    void testMoveDown() {
        // Given
        int originalY = lPiece.getY();

        // When
        lPiece.moveDown();

        // Then
        assertEquals(originalY + 1, lPiece.getY(), "Y position should increase by 1");
    }

    @Test
    @DisplayName("Should move piece left")
    void testMoveLeft() {
        // Given
        int originalX = lPiece.getX();

        // When
        lPiece.moveLeft();

        // Then
        assertEquals(originalX - 1, lPiece.getX(), "X position should decrease by 1");
    }

    @Test
    @DisplayName("Should move piece right")
    void testMoveRight() {
        // Given
        int originalX = lPiece.getX();

        // When
        lPiece.moveRight();

        // Then
        assertEquals(originalX + 1, lPiece.getX(), "X position should increase by 1");
    }

    /**
     * Helper method to create a deep copy of a 2D array.
     *
     * @param original the original array to copy
     * @return a deep copy of the array
     */
    private int[][] copyShape(int[][] original) {
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i].clone();
        }
        return copy;
    }
}

