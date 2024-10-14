import javax.swing.*;

public class TicTacToeRunner extends JButton {
    public static void main(String[] args)
    {
        new TicTacToeFrame();

        TicTacToeTile[][] board = new TicTacToeTile[3][3];

        for( int row = 0; row < 3; row++)
            for(int col= 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].setText(" ");
            }
    }
}