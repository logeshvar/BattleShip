import java.util.ArrayList;

public class PlayerBoard {
    protected final char[][] boardMatrix;
    int boardSize;
    Player player;
    PlayerBoard(int size, Player player){
        boardMatrix = new char[boardSize][boardSize];
        this.boardSize = size;
        this.player = player;
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
        if(hitStatus == "MISS"){
            this.boardMatrix[x][y] = 'M';
        }
        else if(hitStatus == "HIT"){
            this.boardMatrix[x][y] = 'H';
        }
        else{
            for(int count = 0; count < coordinates.size();count++){
                x = coordinates.get(count).getX();
                y = coordinates.get(count).getY();
                this.boardMatrix[x][y] = 'S';
            }
        }
    }
}
