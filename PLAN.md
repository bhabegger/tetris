# Tetris Game Development Plan - Java Desktop Application

## Project Overview
Build a fully functional Tetris game as a locally running Java desktop application with Swing GUI.
Development follows an **incremental approach** where each step produces a working, testable application.

## Technology Stack
- **Language**: Java 17+
- **GUI Framework**: Java Swing
- **Build Tool**: Maven or Gradle
- **Testing**: JUnit 5
- **Graphics**: Java 2D Graphics API

## Development Philosophy
Each milestone should be **runnable and testable**. You should be able to see visual progress after completing each step.

---

## Milestone 1: Display L-Piece in Center (FOUNDATION)
**Goal**: Create a window with an L-shaped piece displayed in the center of the screen.

### 1.1 Project Setup
- [ ] Create Maven/Gradle project structure
- [ ] Set up Java 17+ configuration
- [ ] Create package structure: `tech.habegger.tetris.model`, `tech.habegger.tetris.view`, `tech.habegger.tetris.Main`
- [ ] Add JUnit 5 dependency
- [ ] Create .gitignore file

### 1.2 Basic L-Piece Model
- [ ] Create `Tetromino` class with:
  - `int[][] shape` - 2D array representing L-piece (4x4 grid)
  - `int x, y` - position coordinates
  - `Color color` - orange color for L-piece
- [ ] Hardcode L-piece shape as a 4x4 matrix
- [ ] Position piece at center of board (x=3, y=0)

### 1.3 Basic Swing Window
- [ ] Create `MainFrame` class extending JFrame
- [ ] Set window size (400x600 pixels)
- [ ] Set title "Tetris"
- [ ] Set close operation to EXIT_ON_CLOSE
- [ ] Make window visible

### 1.4 Game Panel with L-Piece Rendering
- [ ] Create `GamePanel` class extending JPanel
- [ ] Define constants: `CELL_SIZE = 30`, `BOARD_WIDTH = 10`, `BOARD_HEIGHT = 20`
- [ ] Override `paintComponent(Graphics g)`:
  - Draw grid background (10x20 cells)
  - Draw the L-piece at its current position
  - Use filled rectangles for blocks
- [ ] Add GamePanel to MainFrame

### 1.5 Main Entry Point
- [ ] Create `Main` class with `main()` method
- [ ] Instantiate MainFrame
- [ ] Create and display the window with L-piece

**✅ MILESTONE 1 COMPLETE**: Run the app and see an L-piece displayed in the center!

---

## Milestone 2: Rotate the L-Piece
**Goal**: Press a key to rotate the L-piece clockwise.

### 2.1 Rotation Logic
- [ ] Add `rotate()` method to Tetromino class
- [ ] Implement 90-degree clockwise rotation algorithm:
  - Transpose the matrix
  - Reverse each row
- [ ] Add unit tests for rotation logic

### 2.2 Keyboard Input
- [ ] Implement KeyListener in GamePanel
- [ ] Listen for UP arrow key or 'X' key
- [ ] On key press, call `tetromino.rotate()`
- [ ] Call `repaint()` to redraw the panel

### 2.3 Visual Feedback
- [ ] Ensure rotated piece renders correctly
- [ ] Test all 4 rotation states of L-piece

**✅ MILESTONE 2 COMPLETE**: Run the app and rotate the L-piece with keyboard!

---

## Milestone 3: Move the Piece Down Automatically
**Goal**: Piece falls down automatically and locks at the bottom.

### 3.1 Game Loop
- [ ] Create `GameLoop` class implementing Runnable
- [ ] Use `javax.swing.Timer` or Thread with sleep
- [ ] Set tick interval (e.g., 500ms)
- [ ] On each tick, move piece down by 1 row

### 3.2 Downward Movement
- [ ] Add `moveDown()` method to Tetromino
- [ ] Increment `y` coordinate
- [ ] Update GamePanel to use the game loop

### 3.3 Bottom Collision Detection
- [ ] Add `isAtBottom()` method
- [ ] Check if `y + piece height >= BOARD_HEIGHT`
- [ ] Stop movement when piece reaches bottom

### 3.4 Piece Locking
- [ ] Create `Board` class with `int[][] grid` (10x20)
- [ ] Add `lockPiece(Tetromino t)` method
- [ ] Copy piece blocks into board grid
- [ ] Render locked pieces in GamePanel

**✅ MILESTONE 3 COMPLETE**: Watch the piece fall and lock at the bottom!

---

## Milestone 4: Spawn New Piece After Locking
**Goal**: When a piece locks, a new L-piece appears at the top.

### 4.1 Game Controller
- [ ] Create `GameController` class
- [ ] Manage current piece and board state
- [ ] Add `spawnNewPiece()` method

### 4.2 Piece Lifecycle
- [ ] Detect when piece locks (bottom collision)
- [ ] Lock current piece to board
- [ ] Spawn new L-piece at top center (x=3, y=0)
- [ ] Continue game loop with new piece

### 4.3 Top Collision (Game Over Detection)
- [ ] Check if new piece collides with locked pieces
- [ ] If collision at spawn, set game over state
- [ ] Display "Game Over" message

**✅ MILESTONE 4 COMPLETE**: Pieces continuously spawn and stack!

---

## Milestone 5: Add Left/Right Movement
**Goal**: Control the falling piece with arrow keys.

### 5.1 Horizontal Movement
- [ ] Add `moveLeft()` and `moveRight()` methods to Tetromino
- [ ] Listen for LEFT and RIGHT arrow keys
- [ ] Move piece horizontally on key press

### 5.2 Wall Collision Detection
- [ ] Add `isValidPosition()` method to Board
- [ ] Check if piece is within bounds (0 <= x < 10)
- [ ] Prevent movement if it would go out of bounds

### 5.3 Piece-to-Piece Collision
- [ ] Check if new position overlaps locked pieces
- [ ] Only allow movement if position is valid

**✅ MILESTONE 5 COMPLETE**: Full control of falling pieces!

---

## Milestone 6: Clear Completed Lines
**Goal**: Remove full rows and shift remaining blocks down.

### 6.1 Line Detection
- [ ] Add `findCompletedLines()` method to Board
- [ ] Check each row to see if all cells are filled
- [ ] Return list of completed line indices

### 6.2 Line Clearing
- [ ] Add `clearLines(List<Integer> lines)` method
- [ ] Remove completed rows
- [ ] Shift all rows above down by number of cleared lines

### 6.3 Visual Feedback
- [ ] Add brief animation/flash for cleared lines (optional)
- [ ] Update display after clearing

### 6.4 Score Tracking
- [ ] Create `GameState` class with score field
- [ ] Award points for cleared lines:
  - 1 line = 100 points
  - 2 lines = 300 points
  - 3 lines = 500 points
  - 4 lines = 800 points
- [ ] Display score on screen

**✅ MILESTONE 6 COMPLETE**: Lines disappear and score increases!

---

## Milestone 7: Add All Tetromino Shapes
**Goal**: Support all 7 standard Tetris pieces.

### 7.1 Tetromino Types
- [ ] Create `TetrominoType` enum (I, O, T, S, Z, J, L)
- [ ] Define shape matrices for all 7 types
- [ ] Assign colors to each type:
  - I: Cyan
  - O: Yellow
  - T: Purple
  - S: Green
  - Z: Red
  - J: Blue
  - L: Orange

### 7.2 Random Piece Generation
- [ ] Create `TetrominoFactory` class
- [ ] Add `createRandomTetromino()` method
- [ ] Use Random to select piece type
- [ ] Update spawn logic to use random pieces

### 7.3 Rotation for All Pieces
- [ ] Test rotation for all piece types
- [ ] Handle special case for O-piece (no rotation needed)
- [ ] Ensure rotations stay within bounds

**✅ MILESTONE 7 COMPLETE**: Full variety of Tetris pieces!

---

## Milestone 8: Add Game Features & Polish
**Goal**: Complete the game with standard features.

### 8.1 Level System
- [ ] Increase level every 10 lines cleared
- [ ] Speed up game loop based on level
- [ ] Display current level

### 8.2 Next Piece Preview
- [ ] Show next piece in side panel
- [ ] Generate next piece in advance
- [ ] Display in smaller preview area

### 8.3 Hard Drop
- [ ] Listen for SPACE bar
- [ ] Instantly drop piece to bottom
- [ ] Lock immediately

### 8.4 Pause Functionality
- [ ] Listen for 'P' key
- [ ] Pause/resume game loop
- [ ] Display "PAUSED" overlay

### 8.5 UI Polish
- [ ] Add grid lines to board
- [ ] Add borders around pieces
- [ ] Display controls/instructions
- [ ] Add game over screen with restart option

**✅ MILESTONE 8 COMPLETE**: Fully playable Tetris game!

---

## Milestone 9: Testing & Documentation
**Goal**: Ensure code quality and maintainability.

### 9.1 Unit Tests
- [ ] Test rotation logic for all pieces
- [ ] Test collision detection
- [ ] Test line clearing algorithm
- [ ] Test scoring calculations
- [ ] Achieve >80% code coverage

### 9.2 Documentation
- [ ] Add JavaDoc to all public classes/methods
- [ ] Update README with:
  - How to build and run
  - Game controls
  - Screenshots
- [ ] Add code comments for complex logic

### 9.3 Packaging
- [ ] Create executable JAR file
- [ ] Test on different platforms
- [ ] Create launch scripts

**✅ MILESTONE 9 COMPLETE**: Production-ready Tetris game!

---

## Optional Milestone 10: Advanced Features
**Goal**: Add polish and advanced gameplay features.

### 10.1 Hold Piece Functionality
- [ ] Add ability to hold current piece for later
- [ ] Press 'C' or Shift to swap current and held piece
- [ ] Display held piece in side panel
- [ ] Limit to one hold per piece drop

### 10.2 Ghost Piece
- [ ] Show transparent outline of where piece will land
- [ ] Calculate drop position
- [ ] Render with lighter/transparent color

### 10.3 High Score Persistence
- [ ] Save high scores to local file (JSON or properties)
- [ ] Load scores on startup
- [ ] Display top 10 scores
- [ ] Prompt for player name on new high score

### 10.4 Sound Effects (Optional)
- [ ] Add sound for piece rotation
- [ ] Add sound for piece locking
- [ ] Add sound for line clear
- [ ] Add special sound for Tetris (4 lines)
- [ ] Add background music

### 10.5 Visual Polish
- [ ] Add smooth animations for line clearing
- [ ] Add visual effects for Tetris
- [ ] Implement different color themes
- [ ] Add screen shake or particle effects

**✅ MILESTONE 10 COMPLETE**: Professional-quality Tetris!

---

## Success Criteria
- ✅ Fully playable Tetris game with standard rules
- ✅ Smooth gameplay (60 FPS)
- ✅ All 7 tetromino types working correctly
- ✅ Line clearing and scoring functional
- ✅ Responsive keyboard controls
- ✅ Clean, maintainable code with >80% test coverage
- ✅ Complete JavaDoc documentation
- ✅ Executable JAR file

## Timeline Estimate (Incremental Approach)
- **Milestone 1**: 2-3 hours (Display L-piece)
- **Milestone 2**: 1 hour (Rotation)
- **Milestone 3**: 2-3 hours (Auto-fall and locking)
- **Milestone 4**: 1-2 hours (Piece spawning)
- **Milestone 5**: 1-2 hours (Movement controls)
- **Milestone 6**: 2-3 hours (Line clearing)
- **Milestone 7**: 2-3 hours (All pieces)
- **Milestone 8**: 3-4 hours (Polish & features)
- **Milestone 9**: 3-4 hours (Testing & docs)
- **Milestone 10**: 4-6 hours (Advanced features - optional)

**Total Core Game**: ~1-2 days of focused work
**With Advanced Features**: ~2-3 days total

---

## Development Tips

### After Each Milestone:
1. ✅ Run the application and verify it works
2. ✅ Commit your changes with clear message
3. ✅ Write/update tests for new functionality
4. ✅ Add JavaDoc comments
5. ✅ Take a break and celebrate progress! 🎉

### Testing as You Go:
- Write unit tests alongside each milestone
- Test edge cases (boundaries, collisions)
- Manual testing: play the game!

### Keep It Simple:
- Don't over-engineer early milestones
- Refactor as you learn what works
- Focus on getting each milestone working first

