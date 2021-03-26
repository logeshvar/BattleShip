import java.util.Scanner;

public class Player {

    Board board;


    Player(Board board) {
        this.board = board;
        board.initialize();
    }


    public String getMove() throws InvalidInputMoveException {
        Scanner sc = new Scanner(System.in);
        String inputMove = sc.next();
        checkValidInput(inputMove);
        inputMove= Character.toString(Character.toUpperCase(inputMove.charAt(0)))+ inputMove.charAt(1);
        return inputMove;
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
}


