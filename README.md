# Tetris Game

[![Java CI with Maven](https://github.com/bhabegger/tetris/actions/workflows/ci.yml/badge.svg)](https://github.com/bhabegger/tetris/actions/workflows/ci.yml)
[![Pull Request Validation](https://github.com/bhabegger/tetris/actions/workflows/pr-validation.yml/badge.svg)](https://github.com/bhabegger/tetris/actions/workflows/pr-validation.yml)

A classic Tetris game implementation in Java using Swing, built with test-driven development and modern CI/CD practices.

![Tetris Game Screenshot](screenshot.png)

## Features

- ✅ All 7 classic Tetris pieces (I, O, T, S, Z, J, L)
- ✅ Full piece rotation and movement controls
- ✅ Line clearing with scoring system
- ✅ Level progression (every 10 lines)
- ✅ Real-time scoreboard display
- ✅ Sound effects for game events
- ✅ Game over detection
- ✅ Professional UI with dark theme

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## How to Build

```bash
mvn clean compile
```

## How to Run

```bash
mvn exec:java -Dexec.mainClass="tech.habegger.tetris.Main"
```

Or build and run the JAR:

```bash
mvn clean package
java -jar target/tetris-1.0-SNAPSHOT.jar
```

## How to Test

Run all unit tests:

```bash
mvn test
```

Run tests with coverage:

```bash
mvn verify
```

## How to Play

### Objective
Stack falling tetromino pieces to create complete horizontal lines. When a line is completed, it disappears and you earn points. The game ends when pieces stack up to the top of the board.

### Game Interface

The game window consists of two main sections:

**Left Side - Game Board (300×600 pixels)**
- 10 columns × 20 rows grid
- Black background with dark gray grid lines
- Colored tetromino pieces (current falling piece + locked pieces)

**Right Side - Scoreboard Panel (200×600 pixels)**
- **SCORE** - Current score (starts at 0)
- **LINES** - Total lines cleared
- **LEVEL** - Current level (increases every 10 lines)
- **CONTROLS** - Quick reference guide
- **GAME OVER** - Red message when game ends

### Controls

| Key | Action |
|-----|--------|
| **←** (Left Arrow) | Move piece left |
| **→** (Right Arrow) | Move piece right |
| **↓** (Down Arrow) | Soft drop (move down faster) |
| **↑** (Up Arrow) or **X** | Rotate piece clockwise |

**Tips:**
- Hold down arrow keys for continuous movement
- Rotation is validated - invalid rotations are automatically cancelled
- Soft drop speeds up descent but doesn't instantly drop the piece

### Tetromino Pieces

All 7 classic pieces with unique colors:

| Piece | Color | Shape | Blocks |
|-------|-------|-------|--------|
| **I** | Cyan | Straight line | 4 |
| **O** | Yellow | Square | 2×2 |
| **T** | Purple | T-shape | 4 |
| **S** | Green | S-shape | 4 |
| **Z** | Red | Z-shape | 4 |
| **J** | Blue | J-shape | 4 |
| **L** | Orange | L-shape | 4 |

### Scoring System

Points are awarded based on lines cleared simultaneously:

| Lines Cleared | Points | Name |
|---------------|--------|------|
| 1 line | 100 | Single |
| 2 lines | 300 | Double |
| 3 lines | 500 | Triple |
| 4 lines | 800 | **Tetris** |

**Level Progression:** Level = (Lines Cleared ÷ 10) + 1

### Sound Effects

- **Single beep** - Piece locks at bottom
- **Double beep** - Line(s) cleared
- **Descending beeps** - Game over

### Tips and Strategies

**For Beginners:**
- Take your time - pieces fall every 0.5 seconds
- Avoid gaps - stack pieces without leaving empty spaces
- Use the walls for precise placement
- Plan ahead for the next piece

**Advanced Techniques:**
- Save I-pieces to clear 4 lines at once (Tetris) for maximum points
- Keep the top of your stack as level as possible
- Create wells - leave a vertical gap for I-pieces
- Rotate pieces early while they're falling

## Project Structure

```
src/
├── main/
│   └── java/tech/habegger/tetris/
│       ├── model/          # Game models (Board, Tetromino, TetrominoType)
│       ├── view/           # Swing UI (GamePanel, ScorePanel, MainFrame)
│       ├── controller/     # Game logic (GameController)
│       ├── util/           # Utilities (SoundPlayer)
│       └── Main.java       # Application entry point
└── test/
    └── java/tech/habegger/tetris/
        ├── model/          # Model tests
        └── controller/     # Controller tests
```

## Development

This project follows:
- **Test-Driven Development** (80%+ code coverage)
- **SOLID principles**
- **MVC architecture**
- **Comprehensive JavaDoc documentation**
- **GitHub Actions CI/CD**

See [PLAN.md](PLAN.md) for the development roadmap and [AGENTS.md](AGENTS.md) for coding standards.

## CI/CD

The project uses GitHub Actions for continuous integration:
- Builds and tests on every push to `main`
- Validates all pull requests before merge
- Tests against Java 17 and 21
- Generates test reports and artifacts

## License

This project was created as a learning exercise.

## Acknowledgments

Built with AI assistance using incremental milestone-based development.
