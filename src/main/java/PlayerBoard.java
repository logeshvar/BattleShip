import java.util.ArrayList;

public class PlayerBoard {
    protected final char[][] boardMatrix;
    int boardSize;
    PlayerBoard(int size){
        this.boardSize = size;
        boardMatrix = new char[boardSize][boardSize];

    }

    public void initialize() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                boardMatrix[row][col] = '-';
            }
        }
    }

    public void printBoard() {
        for(int start = 0; start<this.boardSize;start++){ System.out.print(" "+(char)65+start); }
        System.out.println();
        for (int row = 0; row < this.boardSize; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < this.boardSize; column++) {
                System.out.print(this.boardMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    public void updateBoard(ArrayList<Coordinate> coordinates, String hitStatus){
        int x = coordinates.get(0).getX();
        int y = coordinates.get(1).getY();
        if(hitStatus.equals("MISS")){
            this.boardMatrix[x][y] = 'M';
        }
        else if(hitStatus.equals("HIT")){
            this.boardMatrix[x][y] = 'H';
        }
        else{
            for (Coordinate coordinate : coordinates) {
                x = coordinate.getX();
                y = coordinate.getY();
                this.boardMatrix[x][y] = 'S';
            }
        }
    }


}
