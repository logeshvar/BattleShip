import java.util.Scanner;

public class Game {
    Player player;
    Computer computer;
    private int numberOfShipSunk = 0;
    int flag = 0;

    public Game() {
        computer = new Computer(10);
        player = new Player(10);
    }


    public static void main(String[] args) throws InvalidInputMoveException {
        Game game = new Game();
        while(true){
            System.out.println("1.Make a move\n2.Print Board\n3.Quit");
            System.out.print("Enter:");
            Scanner sc = new Scanner(System.in);
            int input= sc.nextInt();
            if (input==1){
                System.out.print("Enter your position guess:");
                String inputMove = sc.next();
                checkValidInput(inputMove);
                inputMove= Character.toString(Character.toUpperCase(inputMove.charAt(0)))+ inputMove.charAt(1);
                game.makeMove(inputMove,game.computer);
                if(game.flag == 1){
                    game.player.board.printBoard();
                    break;
                }
                game.player.board.printBoard();
            }
            else if(input==2){
                game.player.board.printBoard();
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

    private static void checkValidInput(String inputMove) throws InvalidInputMoveException {
        if(inputMove.length() != 2){
            throw new InvalidInputMoveException("Enter valid input move of length two");
        }
        else if(!(((int) inputMove.charAt(0)>= 65 && (int) inputMove.charAt(0)<=74) || ((int) inputMove.charAt(0)>= 97 && (int) inputMove.charAt(0)<=106))){
            throw new InvalidInputMoveException("Enter the valid column");
        }
        else if(!((int) inputMove.charAt(1)>=48 && (int) inputMove.charAt(1)<=57)){
            throw new InvalidInputMoveException("Enter the valid row number");
        }
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
                    this.flag = 1;
                }
                break;
        }
    }
    public String checkHitOrMissORSink(String inputMove,Computer computer) {
        int y = (int) (inputMove.charAt(0)) - 65;
        int x = Integer.parseInt(String.valueOf(inputMove.charAt(1)));
        if (computer.board.boardMatrix[x][y] == 1) {
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
