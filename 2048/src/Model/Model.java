package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Model {

    int[][] board;

    public Model() {
        this.resetBoard();
    }
    
    public void resetBoard() {
        this.board = new int[4][4];
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board.length; j++) {
                this.board[i][j] = 0;
            }
        }

        this.generateNewTile();
    }

    public void generateNewTile() {
        Tile selectedTile = this.getRandomEmptyTile();

        Random rand = new Random();
        if(rand.nextFloat() >= 0.1f) {
            this.board[selectedTile.getRow()][selectedTile.getCol()] = 2;
        }
        else {
            this.board[selectedTile.getRow()][selectedTile.getCol()] = 4;
        }
    }

    private Tile getRandomEmptyTile() {

        List<Tile> emptyTiles = new ArrayList<Tile>();

        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board.length; j++) {
                if(this.board[i][j] == 0) {
                    emptyTiles.add(new Tile(i, j));
                }
            }
        }

        Random random = new Random();
        return emptyTiles.get(random.nextInt(emptyTiles.size()));
    }

    private boolean isGameOver() {
        /*
        Returns true if the game is over. The game is over if the board is full and ifthe board cannot be swiped inany directions.
         */

        // check if board is full
        for(int row = 0; row < this.board.length; row++) {
            for(int col = 0; col < this.board[row].length; col++) {
                if(this.board[row][col] == 0) {
                    return false;
                }
            }
        }

        // check if valid move exists on full board


        return true;
    }

    private List<Integer> shift(List<Integer> list) {
        /*
        Removes all zeros from list and merges elements.
        */
        
        // remove all zeros
        list.removeAll(Collections.singleton(0));

        // merge elements
        for(int i = 0; i < list.size() - 1; i++) {
            if(list.get(i).equals(list.get(i + 1))) {
                int tileValue = list.get(i);
                list.set(i, tileValue * 2);
                list.remove(i + 1);
            }
        }

        // add trailing zeros
        while(list.size() < 4) {
            list.add(0);
        }

        return list;
    }

    public void shiftDown() {
        int[][] boardCopy = Arrays.stream(this.board).map(int[]::clone).toArray(int[][]::new);

        for(int row = 0; row < 4; row++) {
            List<Integer> rowList = new ArrayList<Integer>();
            for(int col = 0; col < 4; col++) {
                rowList.add(boardCopy[col][row]);
            }

            List<Integer> shiftedRowList = reverse(this.shift(reverse(rowList)));

            for(int col = 0; col < 4; col++) {
                boardCopy[col][row] = shiftedRowList.get(col);
            }
        }
        
        if(!Arrays.deepEquals(this.board, boardCopy)) {
            this.board = boardCopy;
            this.generateNewTile();
        }
    }

    public void shiftUp() {
        int[][] boardCopy = Arrays.stream(this.board).map(int[]::clone).toArray(int[][]::new);

        for(int row = 0; row < 4; row++) {
            List<Integer> rowList = new ArrayList<Integer>();
            for(int col = 0; col < 4; col++) { 
                rowList.add(boardCopy[col][row]);
            }

            rowList = this.shift(rowList);

            for(int col = 0; col < 4; col++) { 
                boardCopy[col][row] = rowList.get(col);
            }
        }

        if(!Arrays.deepEquals(this.board, boardCopy)) {
            this.board = boardCopy;
            this.generateNewTile();
        }
    }

    public void shiftRight() {
        int[][] boardCopy = Arrays.stream(this.board).map(int[]::clone).toArray(int[][]::new);

        for(int row = 0; row < 4; row++) {
            List<Integer> rowList = Arrays.stream(boardCopy[row]).boxed().collect(Collectors.toList());
            boardCopy[row] = reverse(this.shift(reverse(rowList))).stream().mapToInt(i->i).toArray();
        }

        if(!Arrays.deepEquals(this.board, boardCopy)) {
            this.board = boardCopy;
            this.generateNewTile();
        }
    }

    public void shiftLeft() {
        int[][] boardCopy = Arrays.stream(this.board).map(int[]::clone).toArray(int[][]::new);
        
        for(int row = 0; row < 4; row++) {
            boardCopy[row] = this.shift(Arrays.stream(boardCopy[row]).boxed().collect(Collectors.toList())).stream().mapToInt(i->i).toArray();;
        }

        if(!Arrays.deepEquals(this.board, boardCopy)) {
            this.board = boardCopy;
            this.generateNewTile();
        }
    }

    public int[][] getBoard() {
        return this.board;
    }

    private static List<Integer> reverse(List<Integer> list) {
        /*
        Reverses a list of integers.
        */
        for (int i = 0, j = list.size() - 1; i < j; i++) {
            list.add(i, list.remove(j));
        }

        return list;
    }

}
