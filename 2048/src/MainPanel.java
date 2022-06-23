import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

public class MainPanel extends JPanel {
    
    JLabel[][] board;

    public MainPanel() {
        this.setLayout(new GridLayout(4, 4, 5, 5));
        this.createBoard();

    }

    private void createBoard() {
        this.board = new JLabel[4][4];
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                this.board[row][col] = new JLabel();
                this.board[row][col].setOpaque(true);
                this.add(board[row][col]);
            }
        }
    }

    public void updateBoard(int[][]board) throws Exception {
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[row].length; col++) {
                if(board[row][col] != 0) {
                    this.board[row][col].setText(Integer.toString(board[row][col]));
                }
                else {
                    this.board[row][col].setText("");
                }
                this.board[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                this.board[row][col].setFont(new Font("Comic Sans", Font.BOLD, 30));
                
                switch(board[row][col]) {
                    case 0: 
                        this.board[row][col].setBackground(Color.LIGHT_GRAY);
                        break;
                    case 2: 
                        this.board[row][col].setBackground(new Color(0, 255, 0));
                        break;
                    case 4: 
                        this.board[row][col].setBackground(new Color(0, 255, 85));
                        break;
                    case 8: 
                        this.board[row][col].setBackground(new Color(0, 255, 170));
                        break;
                    case 16: 
                        this.board[row][col].setBackground(new Color(0, 255, 255));
                        break;
                    case 32: 
                        this.board[row][col].setBackground(new Color(0, 170, 255));
                        break;
                    case 64: 
                        this.board[row][col].setBackground(new Color(0, 85, 255));
                        break;
                    case 128: 
                        this.board[row][col].setBackground(new Color(0, 0, 255));
                        break;
                    case 256: 
                        this.board[row][col].setBackground(new Color(85, 0, 255));
                        break;
                    case 512: 
                        this.board[row][col].setBackground(new Color(170, 0, 255));
                        break;
                    case 1024: 
                        this.board[row][col].setBackground(new Color(255, 0, 255));
                        break;
                    case 2048: 
                        this.board[row][col].setBackground(new Color(255, 0, 170));
                        break;
                    case 4096: 
                        this.board[row][col].setBackground(new Color(255, 0, 85));
                        break;
                    case 8192: 
                        this.board[row][col].setBackground(new Color(255, 0, 0));
                        break;
                    default: 
                        throw new Exception("Invalid number");
                }
            }
        }
    }

}
