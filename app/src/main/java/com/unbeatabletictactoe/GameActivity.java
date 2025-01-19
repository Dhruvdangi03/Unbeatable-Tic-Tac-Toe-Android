package com.unbeatabletictactoe;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private String[][] board = new String[3][3];
    private String currentPlayer;
    private MaterialButton[][] buttons;
    private String playerSymbol;
    private String botSymbol;
    private final Random random = new Random();
    private TextView statusText;
    private MaterialButton resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerSymbol = getIntent().getStringExtra("player");
        botSymbol = playerSymbol.equals("X") ? "O" : "X";
        currentPlayer = playerSymbol;

        statusText = findViewById(R.id.tv_status);
        resetButton = findViewById(R.id.btn_reset);
        resetButton.setOnClickListener(v -> resetGame());

        buttons = new MaterialButton[3][3];
        initializeButtons();
        updateStatusText();
    }

    private void updateStatusText() {
        String text = currentPlayer.equals(playerSymbol) ? "Your Turn" : "Bot's Turn";
        statusText.setText(text);
    }

    private void initializeButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "btn_" + i + "_" + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = findViewById(resID);

                final int row = i;
                final int col = j;
                buttons[i][j].setOnClickListener(v -> playerMove(row, col));
            }
        }
    }

    private void playerMove(int row, int col) {
        if (board[row][col] == null && currentPlayer.equals(playerSymbol)) {
            makeMove(row, col, playerSymbol);

            if (checkWinner(playerSymbol)) {
                showWinnerDialog("You Win!");
                return;
            } else if (isBoardFull()) {
                showWinnerDialog("It's a Draw!");
                return;
            }

            currentPlayer = botSymbol;
            updateStatusText();
            buttons[row][col].postDelayed(this::botMove, 500);
        } else if (board[row][col] != null) {
            Toast.makeText(this, "This spot is already taken!", Toast.LENGTH_SHORT).show();
        }
    }

    private void botMove() {
        int[] bestMove = getBestMove();
        makeMove(bestMove[0], bestMove[1], botSymbol);

        if (checkWinner(botSymbol)) {
            showWinnerDialog("Bot Wins!");
        } else if (isBoardFull()) {
            showWinnerDialog("It's a Draw!");
        } else {
            currentPlayer = playerSymbol;
            updateStatusText();
        }
    }

    private int[] getBestMove() {
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) {
                    board[row][col] = botSymbol;
                    int score = minimax(false);
                    board[row][col] = null;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWinner(playerSymbol)) return -1;
        if (checkWinner(botSymbol)) return 1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == null) {
                    board[row][col] = isMaximizing ? botSymbol : playerSymbol;
                    int score = minimax(!isMaximizing);
                    board[row][col] = null;
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }

        return bestScore;
    }

    private void makeMove(int row, int col, String symbol) {
        board[row][col] = symbol;
        buttons[row][col].setText(symbol);
        buttons[row][col].setTextColor(symbol.equals(playerSymbol) ?
                getResources().getColor(R.color.player_color) :
                getResources().getColor(R.color.bot_color));

        buttons[row][col].animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setDuration(100)
                .withEndAction(() -> buttons[row][col].animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100));
    }

    private boolean checkWinner(String symbol) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] != null && board[i][0].equals(symbol) &&
                    board[i][1] != null && board[i][1].equals(symbol) &&
                    board[i][2] != null && board[i][2].equals(symbol)) ||
                    (board[0][i] != null && board[0][i].equals(symbol) &&
                            board[1][i] != null && board[1][i].equals(symbol) &&
                            board[2][i] != null && board[2][i].equals(symbol))) {
                return true;
            }
        }

        // Check diagonals
        return (board[0][0] != null && board[0][0].equals(symbol) &&
                board[1][1] != null && board[1][1].equals(symbol) &&
                board[2][2] != null && board[2][2].equals(symbol)) ||
                (board[0][2] != null && board[0][2].equals(symbol) &&
                        board[1][1] != null && board[1][1].equals(symbol) &&
                        board[2][0] != null && board[2][0].equals(symbol));
    }

    private boolean isBoardFull() {
        for (String[] row : board) {
            for (String cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinnerDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage(message)
                .setPositiveButton("Play Again", (dialog, which) -> resetGame())
                .setNegativeButton("Main Menu", (dialog, which) -> finish())
                .setCancelable(false)
                .show();
    }

    private void resetGame() {
        board = new String[3][3];
        for (MaterialButton[] row : buttons) {
            for (MaterialButton button : row) {
                button.setText("");
            }
        }
        currentPlayer = playerSymbol;
        updateStatusText();
    }
}