import java.util.Scanner;

public class Game {
    @SuppressWarnings("FieldMayBeFinal")
    private Player player;
    @SuppressWarnings("FieldMayBeFinal")
    private Computer computer;
    private int numberOfShipSunk = 0;
    private boolean isSunk;

    public Game(int size) {
        Board computerBoard = new Board(size);
        Board playerBoard = new Board(size);
        computer = new Computer(computerBoard);
        player = new Player(playerBoard);
    }


    public static void main(String[] args) throws InvalidInputMoveException {
        Game game = new Game(10);
        while(true){

            System.out.println("1.Make a move\n2.Print Board\n3.Quit");
            System.out.print("Enter your choice:");
            Scanner sc = new Scanner(System.in);
            int input= sc.nextInt();
            if (input==1){
                System.out.print("Enter your position guess:");
                String inputMove = game.player.getMove();

                game.makeMove(inputMove,game.computer);
                if(game.isSunk == true){
                    printPlayerBoard(game);
                    break;
                }
                printPlayerBoard(game);
            }
            else if(input==2){
                printPlayerBoard(game);
            }
            else if(input==3){
                System.out.println("Computer has won!");
                break;
            }
            else{
                System.out.println("Enter a valid option!!");
            }
        }
    }

    private static void printPlayerBoard(Game game) {
        System.out.println("\t Number of ships sunk: " + game.numberOfShipSunk);
        game.player.board.printBoard();
    }


    private void makeMove(String inputMove, Computer computer) {
        String result = this.checkHitOrMissORSink(inputMove,computer);
        int y = (int) (inputMove.charAt(0)) - 65;
        int x = Integer.parseInt(String.valueOf(inputMove.charAt(1)));
        switch (result) {
            case "MISS":
                player.board.boardMatrix[x][y] = 'M';
                break;
            case "HIT":
                player.board.boardMatrix[x][y] = 'H';
                break;
            case "SINK":
                player.board.boardMatrix[x][y] = 'H';
                numberOfShipSunk += 1;
                if (numberOfShipSunk == 5) {
                    System.out.println("You have Won!");
                    this.isSunk = true;
                }
                break;
        }
    }
    private String checkHitOrMissORSink(String inputMove,Computer computer) {
        int y = (int) (inputMove.charAt(0)) - 65;
        int x = Integer.parseInt(String.valueOf(inputMove.charAt(1)));
        if (computer.board.boardMatrix[x][y] == 's') {
            Ship thisShip = computer.getShip(inputMove);
            thisShip.gotHit();
            if (thisShip.hasSunk()) {
                System.out.println("Ship has sunk");
                return "SINK";
            }
            return "HIT";
        }
        return "MISS";
    }


}
