public class Player {

    Board board;

    Player(int size) {
        board = new Board(10);
        board.initialize(size);
    }


     void printBoard(Board board) {
        System.out.println("  A B C D E F G H I J");
        for (int row = 0; row < 10; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < 10; column++) {
                System.out.print(board.boardMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

}


