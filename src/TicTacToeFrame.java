import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {
    private TicTacToeTile[][] board;
    private String currentPlayer;
    private int moveCount;

    /**
     * Initializes the game frame and sets up the board
     */
    public TicTacToeFrame() {
        super("Tic-Tac-Toe Game");
        this.setLayout(new BorderLayout());
        currentPlayer = "X";
        moveCount = 0;

        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        board = new TicTacToeTile[3][3];

        // Initialize board with TicTacToeTile buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
                board[row][col].addActionListener(new TileClickListener());
                gamePanel.add(board[row][col]);
            }
        }

        // Add Quit Button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));

        this.add(gamePanel, BorderLayout.CENTER);
        this.add(quitButton, BorderLayout.SOUTH);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class TileClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile tile = (TicTacToeTile) e.getSource();
            if (tile.getText().equals(" ")) // Valid move
            {
                tile.setText(currentPlayer);
                moveCount++;

                if (moveCount >= 5 && checkWin(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                    promptPlayAgain();
                } else if (moveCount == 9) {
                    JOptionPane.showMessageDialog(null, "It's a tie!");
                    promptPlayAgain();
                } else {
                    currentPlayer = currentPlayer.equals("X") ? "O" : "X"; // Switch player
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid move, try again.");
            }
        }
    }


    private boolean checkWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }


    private boolean isRowWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }


    private boolean isColWin(String player) {
        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player)) {
                return true;
            }
        }
        return false;
    }


    private boolean isDiagonalWin(String player) {
        // Check top-left to bottom-right diagonal
        if (board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player)) {
            return true;
        }
        // Check top-right to bottom-left diagonal
        if (board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    private void promptPlayAgain() {
        int response = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            resetGame(); // Restart the game
        } else {
            System.exit(0); // Exit the application
        }
    }

    /**
     * Resets the game board and starts a new game
     */
    private void resetGame() {
        currentPlayer = "X";
        moveCount = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
            }
        }
    }
}