import java.lang.Math;
import java.util.ArrayList;

class Gameboard {

    private Square[][] board;
    private boolean gameOver;
    private int leftToBeOpened;

    public Gameboard(int size) {
        gameOver = false;
        leftToBeOpened = 0;

        board = new Square[size][size];

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {

                //Square has a 10% chance of becoming a bomb.
                if(randInt(1, 10) == 1) {
                    board[i][j] = new Square(true);
                }
                else {
                    board[i][j] = new Square(false);
                    leftToBeOpened++;
                }
            }
        }

        //Set number of bomb neighbours for each square and adds their neighbours.
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                Square square = board[i][j];

                Square[][] b = board;

                //List of all neighbours
                ArrayList<Square> neighbours = new ArrayList<Square>();
                
                // b[i-1][j-1], b[i-1][j], b[i-1][j+1],
                // b[i][j-1],              b[i][j+1],
                // b[i+1][j-1], b[i+1][j], b[i+1][j+1];

                int endOfBoard = size-1;
                
                //Top neighbours
                if(i != 0) {
                    neighbours.add(b[i-1][j]);
                    square.addNeighbour(b[i-1][j]);

                    if(j != 0) neighbours.add(b[i-1][j-1]);
                    if(j != endOfBoard) neighbours.add(b[i-1][j+1]);
                }

                //Bottom neighbours
                if(i != endOfBoard) {
                    neighbours.add(b[i+1][j]);
                    square.addNeighbour(b[i+1][j]);

                    if(j != 0) neighbours.add(b[i+1][j-1]);
                    if(j != endOfBoard) neighbours.add(b[i+1][j+1]);
                }

                //Left and right neighbour
                if(j != 0) {
                    neighbours.add(b[i][j-1]);
                    square.addNeighbour(b[i][j-1]);
                }
                if(j != endOfBoard) {
                    neighbours.add(b[i][j+1]);
                    square.addNeighbour(b[i][j+1]);
                }
                
                //Check witch neighbours are bombs
                for(Square neighbour: neighbours) {
                    if(neighbour.isBomb()) square.increaseBombNeighbours();
                }
            }
        }

    }

    private int randInt(int min, int max) {
        return (int)(Math.random() * (max - min + 1) + min);
    }


    public void flagg(int row, int col) {
        board[row][col].flagg();
    }


    public void open(int row, int col) {
        Square sq = board[row][col];
        sq.open();

        if(sq.isBomb()) {
            gameOver = true;
        }
        else {
            leftToBeOpened--;
            openNeighbours(sq);
        }
    }


    private void openNeighbours(Square sq) {

        for(Square neighbour: sq.getNeighbours()) {

            if (!neighbour.isBomb() && neighbour.isHidden()) {
                neighbour.open();
                leftToBeOpened--;

                if(neighbour.isBomb()) gameOver = true;

                if(neighbour.getValue().equals("")) {
                    openNeighbours(neighbour);
                }
            }
        }
    }

    
    public boolean gameOver() {
        return gameOver;
    }

    public int leftToBeOpened() {
        return leftToBeOpened;
    }



    public String toStringHiddenValues() {
        String topRow = "\n     ";
        String string = "";

        for(int i=0; i < board.length; i++) {
            String rowNum = i + ".";
            string += "\n" + String.format("%5s", rowNum + "  ");
            topRow += String.format("%-3s", i + ".");

            for(int j=0; j < board[i].length; j++) {
                string += String.format("%-3s", board[i][j].getValue());
            }
        }

        return topRow + "\n" + string;
    }

    @Override
    public String toString() {
        String topRow = "\n     ";
        String string = "";

        for(int i=0; i < board.length; i++) {
            String rowNum = i + ".";
            string += "\n" + String.format("%5s", rowNum + "  ");
            topRow += String.format("%-3s", i + ".");

            for(int j=0; j < board[i].length; j++) {
                string += String.format("%-3s", board[i][j]);
            }
        }

        return topRow + "\n" + string + "\n";
    }
}