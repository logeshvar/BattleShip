import java.util.Scanner;

public class PlayerBoard {
    char[][] playerBoard = new char[10][10];
    private int numberOfShipSunk = 0;
    int flag = 0;
    PlayerBoard(){
        initialize();
    }

    private void initialize() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col <10; col++){
                playerBoard[row][col] = ('-');
            }
        }
    }


    public static void main(String[] args) throws InvalidInputMoveException {
        ComputerBoard computerBoard = new ComputerBoard();
        PlayerBoard playerBoard = new PlayerBoard();
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
                playerBoard.makeMove(inputMove,computerBoard);
                if(playerBoard.flag == 1){
                    playerBoard.printBoard();
                    break;
                }
                playerBoard.printBoard();
            }
            else if(input==2){
                playerBoard.printBoard();
            }
            else if(input==3){
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

    private void printBoard() {
        System.out.println("  A B C D E F G H I J");
        for (int row = 0; row < 10; row++) {
            System.out.print(row+" ");
            for (int column = 0; column < 10; column++) {
                System.out.print(this.playerBoard[row][column]+" ");
            }
            System.out.println();
        }
    }

    private void makeMove(String inputMove, ComputerBoard computerBoard) {
        String result = computerBoard.checkHitOrMissORSink(inputMove);
        int y = (int) (inputMove.charAt(0)) - 65;
        int x = Integer.parseInt(String.valueOf(inputMove.charAt(1)));

        switch (result) {
            case "MISS":
                this.playerBoard[x][y] = 'M';
                break;
            case "HIT":
                this.playerBoard[x][y] = 'H';
                break;
            case "SINK":
                this.playerBoard[x][y] = 'H';
                numberOfShipSunk += 1;
                if (numberOfShipSunk == 5) {
                    System.out.println("You have Won!");
                    this.flag = 1;
                }
                break;
        }
    }

}
