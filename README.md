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

## Controls

- **← →** - Move piece left/right
- **↓** - Soft drop (faster descent)
- **↑ or X** - Rotate piece clockwise

## Scoring

- 1 line = 100 points
- 2 lines = 300 points
- 3 lines = 500 points
- 4 lines (Tetris) = 800 points

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
