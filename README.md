# Unbeatable Tic-Tac-Toe Android App

This repository contains an Android application version of the unbeatable Tic-Tac-Toe game built using Java and Android Studio. The app offers a user-friendly UI for playing against a bot that never loses, powered by the Minimax algorithm.

## Features
- Play against an unbeatable AI bot.
- Choose your symbol: `X` or `O`.
- Interactive and responsive UI.
- Detects wins, draws, and invalid moves.
- Clean and intuitive game board design.

## Project Structure
```
.
├── APK                      # Contains the built APK file for installation
├── app                      # Application source code
├── gradle                   # Gradle wrapper files
├── .gitignore               # Git ignore file
├── build.gradle.kts         # Project-level build file
├── gradle.properties        # Gradle properties
├── gradlew                  # Gradle wrapper script (Linux/Mac)
├── gradlew.bat              # Gradle wrapper script (Windows)
└── settings.gradle.kts      # Gradle settings file
```

## Getting Started

### Prerequisites
- Android Studio Flamingo or higher.
- Android device or emulator.

### Running the App
1. Clone this repository:
   ```bash
   git clone https://github.com/Dhruvdangi03/Unbeatable-Tic-Tac-Toe-Android/
   ```
2. Open the project in **Android Studio**.
3. Sync the Gradle files when prompted.
4. Run the app on an emulator or connected Android device by clicking the **Run** button.

### Installing the APK
1. Navigate to the `APK` directory.
2. Transfer the APK file to your Android device.
3. Enable installation from unknown sources.
4. Install and launch the game.

## How to Play
1. Launch the app.
2. Choose your symbol (`X` or `O`).
3. Tap on a tile to place your symbol.
4. The bot will make its move.
5. The game ends when there is a winner or a draw.

## Game Rules
- Players take turns placing their symbols (`X` or `O`) on the board.
- The first player to align three of their symbols in a row, column, or diagonal wins.
- If the board is full with no winner, the game ends in a draw.

## Algorithms Used
- **Minimax Algorithm:** Ensures that the bot always makes the optimal move, either winning or forcing a draw.

## Screenshots
*Coming soon*

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any changes or improvements.
