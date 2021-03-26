public class Board {
    protected final char[][] boardMatrix;
    int boardSize;
    public Board(int boardSize) {
        this.boardSize = boardSize;
        boardMatrix = new char[boardSize][boardSize];


    }

    void initialize() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                boardMatrix[row][col] = '-';
            }
        }
    }
    void printBoard() {
        System.out.println("  A B C D E F G H I J");
        for (int row = 0; row < this.boardSize; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < this.boardSize; column++) {
                System.out.print(this.boardMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }
}
