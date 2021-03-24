import java.util.Scanner;

public class Player {
    char[][] playerBoard = new char[10][10];
    private int numberOfShipSunk = 0;
    int flag = 0;
    Player(){
        initialize();
    }

    private void initialize() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col <10; col++){
                playerBoard[row][col] = ('-');
            }
        }
    }


    public static void main(String[] args) {
        Board computerBoard = new Board();
        Player player = new Player();
        while(true){
            System.out.println("1.Make a move\n2.Print Board\n3.Quit");
            System.out.print("Enter:");
            Scanner sc = new Scanner(System.in);
            int input= sc.nextInt();
            if (input==1){
                System.out.print("Enter your position guess:");
                String inputMove = sc.next();
                inputMove= Character.toString(Character.toUpperCase(inputMove.charAt(0)))+Character.toString(inputMove.charAt(1));
                System.out.println(inputMove);
                player.makeMove(inputMove,computerBoard);
                if(player.flag == 1){
                    player.printBoard();
                    break;
                }
                player.printBoard();
            }
            else if(input==2){
                player.printBoard();
            }
            else if(input==3){
                break;
            }
            else{
                System.out.println("Enter a valid option!!");
            }
        }
    }

    private void printBoard() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                System.out.print(this.playerBoard[row][column]+" ");
            }
            System.out.println();
        }
    }

    private void makeMove(String inputMove, Board computerBoard) {
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
