package tech.habegger.tetris.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Board class.
 * 
 * @author Tetris Team
 * @version 1.0
 * @since 2026-03-02
 */
class BoardTest {
    
    private Board board;
    
    @BeforeEach
    void setUp() {
        board = new Board();
    }
    
    @Test
    @DisplayName("Should create empty board with correct dimensions")
    void testCreateBoard() {
        // Given - setup in @BeforeEach
        
        // When
        Color[][] grid = board.getGrid();
        
        // Then
        assertEquals(Board.HEIGHT, grid.length, "Board should have correct height");
        assertEquals(Board.WIDTH, grid[0].length, "Board should have correct width");
        
        // Check all cells are empty
        for (int row = 0; row < Board.HEIGHT; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                assertNull(grid[row][col], "All cells should be empty initially");
            }
        }
    }
    
    @Test
    @DisplayName("Should detect piece at bottom edge")
    void testIsAtBottomEdge() {
        // Given
        Tetromino piece = Tetromino.createLPiece(3, 17); // Near bottom
        
        // When
        boolean atBottom = board.isAtBottom(piece);
        
        // Then
        assertTrue(atBottom, "Piece should be at bottom when touching bottom edge");
    }
    
    @Test
    @DisplayName("Should detect piece not at bottom")
    void testIsNotAtBottom() {
        // Given
        Tetromino piece = Tetromino.createLPiece(3, 5); // Middle of board
        
        // When
        boolean atBottom = board.isAtBottom(piece);
        
        // Then
        assertFalse(atBottom, "Piece should not be at bottom in middle of board");
    }
    
    @Test
    @DisplayName("Should lock piece onto board")
    void testLockPiece() {
        // Given
        Tetromino piece = Tetromino.createLPiece(3, 17);
        
        // When
        board.lockPiece(piece);
        
        // Then
        // Check that some cells are now occupied
        boolean hasLockedPieces = false;
        for (int row = 0; row < Board.HEIGHT; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                if (board.getCell(row, col) != null) {
                    hasLockedPieces = true;
                    assertEquals(Color.ORANGE, board.getCell(row, col), 
                        "Locked piece should have correct color");
                }
            }
        }
        assertTrue(hasLockedPieces, "Board should have locked pieces after locking");
    }
    
    @Test
    @DisplayName("Should detect collision with locked pieces")
    void testCollisionWithLockedPieces() {
        // Given
        Tetromino piece1 = Tetromino.createLPiece(3, 17);
        board.lockPiece(piece1);
        
        Tetromino piece2 = Tetromino.createLPiece(3, 14); // Above the locked piece
        
        // When - move piece2 down until it would collide
        while (!board.isAtBottom(piece2)) {
            piece2.moveDown();
        }
        
        // Then
        assertTrue(board.isAtBottom(piece2), "Piece should detect collision with locked pieces");
    }
    
    @Test
    @DisplayName("Should clear board")
    void testClearBoard() {
        // Given
        Tetromino piece = Tetromino.createLPiece(3, 17);
        board.lockPiece(piece);
        
        // When
        board.clear();
        
        // Then
        for (int row = 0; row < Board.HEIGHT; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                assertNull(board.getCell(row, col), "All cells should be empty after clear");
            }
        }
    }
    
    @Test
    @DisplayName("Should return null for out of bounds cells")
    void testGetCellOutOfBounds() {
        // When/Then
        assertNull(board.getCell(-1, 0), "Should return null for negative row");
        assertNull(board.getCell(0, -1), "Should return null for negative column");
        assertNull(board.getCell(Board.HEIGHT, 0), "Should return null for row >= HEIGHT");
        assertNull(board.getCell(0, Board.WIDTH), "Should return null for col >= WIDTH");
    }

    @Test
    @DisplayName("Should validate piece in valid position")
    void testIsValidPosition() {
        // Given
        Tetromino piece = Tetromino.createLPiece(3, 5);

        // When
        boolean isValid = board.isValidPosition(piece);

        // Then
        assertTrue(isValid, "Piece should be valid in center of board");
    }

    @Test
    @DisplayName("Should detect invalid position - left wall")
    void testIsInvalidPositionLeftWall() {
        // Given
        Tetromino piece = Tetromino.createLPiece(-3, 5); // Far enough left to be invalid

        // When
        boolean isValid = board.isValidPosition(piece);

        // Then
        assertFalse(isValid, "Piece should be invalid when outside left wall");
    }

    @Test
    @DisplayName("Should detect invalid position - right wall")
    void testIsInvalidPositionRightWall() {
        // Given
        Tetromino piece = Tetromino.createLPiece(8, 5);

        // When
        boolean isValid = board.isValidPosition(piece);

        // Then
        assertFalse(isValid, "Piece should be invalid when outside right wall");
    }

    @Test
    @DisplayName("Should detect invalid position - overlapping locked piece")
    void testIsInvalidPositionOverlap() {
        // Given
        Tetromino piece1 = Tetromino.createLPiece(3, 17);
        board.lockPiece(piece1);

        Tetromino piece2 = Tetromino.createLPiece(3, 17);

        // When
        boolean isValid = board.isValidPosition(piece2);

        // Then
        assertFalse(isValid, "Piece should be invalid when overlapping locked piece");
    }

    @Test
    @DisplayName("Should find completed lines")
    void testFindCompletedLines() {
        // Given - fill the bottom row completely
        for (int col = 0; col < Board.WIDTH; col++) {
            board.getGrid()[Board.HEIGHT - 1][col] = Color.RED;
        }

        // When
        List<Integer> completedLines = board.findCompletedLines();

        // Then
        assertEquals(1, completedLines.size(), "Should find 1 completed line");
        assertEquals(Board.HEIGHT - 1, completedLines.get(0), "Should be the bottom row");
    }

    @Test
    @DisplayName("Should find no completed lines on empty board")
    void testFindNoCompletedLines() {
        // Given - empty board

        // When
        List<Integer> completedLines = board.findCompletedLines();

        // Then
        assertTrue(completedLines.isEmpty(), "Should find no completed lines");
    }

    @Test
    @DisplayName("Should find multiple completed lines")
    void testFindMultipleCompletedLines() {
        // Given - fill bottom two rows
        for (int row = Board.HEIGHT - 2; row < Board.HEIGHT; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                board.getGrid()[row][col] = Color.BLUE;
            }
        }

        // When
        List<Integer> completedLines = board.findCompletedLines();

        // Then
        assertEquals(2, completedLines.size(), "Should find 2 completed lines");
    }

    @Test
    @DisplayName("Should clear completed lines and shift rows down")
    void testClearLines() {
        // Given - fill bottom row and add a piece above it
        for (int col = 0; col < Board.WIDTH; col++) {
            board.getGrid()[Board.HEIGHT - 1][col] = Color.RED;
        }
        board.getGrid()[Board.HEIGHT - 2][0] = Color.BLUE;

        List<Integer> linesToClear = List.of(Board.HEIGHT - 1);

        // When
        int cleared = board.clearLines(linesToClear);

        // Then
        assertEquals(1, cleared, "Should clear 1 line");
        assertNull(board.getCell(Board.HEIGHT - 1, 1), "Bottom row should be empty after clear");
        assertEquals(Color.BLUE, board.getCell(Board.HEIGHT - 1, 0),
            "Blue piece should have shifted down");
    }

    @Test
    @DisplayName("Should return 0 when clearing empty list")
    void testClearNoLines() {
        // Given
        List<Integer> emptyList = List.of();

        // When
        int cleared = board.clearLines(emptyList);

        // Then
        assertEquals(0, cleared, "Should clear 0 lines");
    }

    @Test
    @DisplayName("Should clear multiple lines correctly")
    void testClearMultipleLines() {
        // Given - fill bottom 3 rows completely, with a gap in row 17
        for (int row = Board.HEIGHT - 3; row < Board.HEIGHT; row++) {
            for (int col = 0; col < Board.WIDTH; col++) {
                board.getGrid()[row][col] = Color.RED;
            }
        }
        // Add a piece above the completed lines
        board.getGrid()[Board.HEIGHT - 4][0] = Color.BLUE;
        board.getGrid()[Board.HEIGHT - 4][1] = Color.BLUE;

        List<Integer> linesToClear = List.of(Board.HEIGHT - 3, Board.HEIGHT - 2, Board.HEIGHT - 1);

        // When
        int cleared = board.clearLines(linesToClear);

        // Then
        assertEquals(3, cleared, "Should clear 3 lines");

        // Check that the blue pieces shifted down to the bottom
        assertEquals(Color.BLUE, board.getCell(Board.HEIGHT - 1, 0),
            "Blue piece should have shifted down to bottom row");
        assertEquals(Color.BLUE, board.getCell(Board.HEIGHT - 1, 1),
            "Blue piece should have shifted down to bottom row");

        // Check that top rows are empty
        for (int col = 0; col < Board.WIDTH; col++) {
            assertNull(board.getCell(0, col), "Top row should be empty");
            assertNull(board.getCell(1, col), "Second row should be empty");
            assertNull(board.getCell(2, col), "Third row should be empty");
        }
    }
}

