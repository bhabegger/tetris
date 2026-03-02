# Agent Guidance for Tetris Project

## Project Overview
This is a Java Swing-based Tetris game built as a desktop application. All code should follow professional Java standards with comprehensive testing and documentation.

---

## Code Quality Standards

### 1. Testing Requirements

#### Unit Tests (MANDATORY)
- **Coverage Target**: Minimum 80% code coverage for all business logic
- **Framework**: JUnit 5
- **Location**: Mirror source structure in `src/test/java/`
- **Naming Convention**: `ClassNameTest.java`

**What MUST be tested:**
- [ ] All game logic methods (movement, rotation, collision detection)
- [ ] Tetromino shape definitions and rotations
- [ ] Board state management (line clearing, piece locking)
- [ ] Scoring calculations
- [ ] Level progression logic
- [ ] Game state transitions (start, pause, game over)
- [ ] Edge cases and boundary conditions

**Test Structure:**
```java
@Test
@DisplayName("Should clear full line and update score")
void testLineClear() {
    // Given - setup
    // When - action
    // Then - assertion
}
```

#### Integration Tests
- Test interaction between Controller, Model, and View components
- Test game loop timing and threading
- Test keyboard input handling

#### Test Guidelines
- Use descriptive test method names
- Follow AAA pattern (Arrange, Act, Assert)
- One assertion concept per test
- Use `@BeforeEach` and `@AfterEach` for setup/teardown
- Mock external dependencies when needed
- Test both happy paths and error cases

---

### 2. Documentation Requirements

#### JavaDoc (MANDATORY)
Every public class, method, and field MUST have JavaDoc comments.

**Class-level JavaDoc:**
```java
/**
 * Represents a Tetris game board with a 10x20 grid.
 * Manages piece placement, collision detection, and line clearing.
 * 
 * @author [Your Name]
 * @version 1.0
 * @since 2026-03-02
 */
public class Board {
    // ...
}
```

**Method-level JavaDoc:**
```java
/**
 * Attempts to move the current piece in the specified direction.
 * 
 * @param direction the direction to move (LEFT, RIGHT, or DOWN)
 * @return true if the move was successful, false if blocked
 * @throws IllegalStateException if game is not in PLAYING state
 */
public boolean movePiece(Direction direction) {
    // ...
}
```

**Field-level JavaDoc:**
```java
/** The width of the game board in blocks (typically 10) */
private static final int BOARD_WIDTH = 10;
```

#### Code Comments
- Use inline comments for complex algorithms
- Explain WHY, not WHAT (code should be self-explanatory)
- Comment any non-obvious business rules
- Document assumptions and constraints

#### README Updates
- Keep README.md updated with:
  - How to build and run the project
  - How to run tests
  - Game controls and rules
  - Project structure overview
  - Dependencies and requirements

---

### 3. Code Style & Best Practices

#### Naming Conventions
- **Classes**: PascalCase (e.g., `GameController`, `TetrominoType`)
- **Methods**: camelCase (e.g., `movePiece()`, `clearLines()`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `BOARD_WIDTH`, `MAX_LEVEL`)
- **Variables**: camelCase, descriptive names (e.g., `currentPiece`, `linesCleared`)
- **Packages**: lowercase (e.g., `model`, `view`, `controller`, `util`)

#### Class Design
- Follow SOLID principles
- Keep classes focused (Single Responsibility)
- Prefer composition over inheritance
- Use interfaces for abstraction
- Make fields private, provide getters/setters only when needed
- Use immutability where possible (final fields, unmodifiable collections)

#### Method Design
- Methods should do ONE thing
- Keep methods short (< 20 lines ideally)
- Minimize parameters (< 4 parameters)
- Return early to reduce nesting
- Use meaningful return types (avoid boolean traps)

#### Error Handling
- Use exceptions for exceptional cases
- Validate input parameters
- Provide meaningful error messages
- Don't swallow exceptions
- Use try-with-resources for resource management

---

### 4. Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── tech/habegger/tetris/
│   │       ├── model/          # Domain models (Board, Tetromino, GameState)
│   │       ├── view/           # Swing UI components (GamePanel, MainFrame)
│   │       ├── controller/     # Game logic (GameController, GameLoop)
│   │       ├── util/           # Utilities (Constants, ColorScheme)
│   │       └── Main.java       # Application entry point
│   └── resources/
│       ├── images/             # Sprites, icons (if any)
│       └── sounds/             # Sound effects (if any)
└── test/
    └── java/
        └── tech/habegger/tetris/
            ├── model/          # Model tests
            ├── controller/     # Controller tests
            └── util/           # Utility tests
```

---

### 5. Git Commit Guidelines

- Use clear, descriptive commit messages
- Format: `[Component] Brief description`
- Examples:
  - `[Model] Add Tetromino rotation logic`
  - `[Test] Add unit tests for Board class`
  - `[View] Implement game rendering with Swing`
  - `[Docs] Update JavaDoc for GameController`

---

### 6. Development Workflow

**Before implementing any feature:**
1. ✅ Review PLAN.md for requirements
2. ✅ Design the class/method structure
3. ✅ Write JavaDoc for public API
4. ✅ Implement the feature
5. ✅ Write unit tests (aim for 80%+ coverage)
6. ✅ Run all tests and ensure they pass
7. ✅ Update documentation if needed
8. ✅ Commit with clear message

**When modifying existing code:**
1. ✅ Update affected tests first
2. ✅ Make the changes
3. ✅ Update JavaDoc if API changed
4. ✅ Run full test suite
5. ✅ Check for broken functionality

---

## Quality Checklist

Before considering any component "complete":
- [ ] All public methods have JavaDoc
- [ ] Unit tests written with 80%+ coverage
- [ ] All tests pass
- [ ] No compiler warnings
- [ ] Code follows naming conventions
- [ ] No magic numbers (use constants)
- [ ] Error handling implemented
- [ ] Code is readable and well-structured

---

## Additional Notes

- **Performance**: Game should run at 60 FPS minimum
- **Thread Safety**: Ensure game loop and UI updates are thread-safe
- **Extensibility**: Design for future features (multiplayer, themes, etc.)
- **Maintainability**: Code should be easy to understand and modify

---

## Questions or Clarifications?

If any requirement is unclear or conflicts arise, ask before proceeding.

