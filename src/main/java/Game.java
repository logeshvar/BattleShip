import java.util.ArrayList;

public class Game {
    private final Player player;
    @SuppressWarnings("FieldCanBeLocal")
    private final Computer computer;
    ComputerBoard computerBoard;
    PlayerBoard playerBoard;
    private int numberOfShipSunk = 0;
    String[] shipNames;
    int[] shipLengths;

    public Game(int size) {

        shipNames= new String[]{"Carrier","Battleship","Destroyer","Submarine","Patrol Boat"};
        shipLengths = new int[]{5,4,3,3,2};

        computer = new Computer(size);
        computerBoard = new ComputerBoard(size,computer);
        computerBoard.initialize();
        computerBoard.setShips(shipNames,shipLengths);
        System.out.println(size);
        playerBoard = new PlayerBoard(size);
        playerBoard.initialize();

        player = new Player();

    }


    public static void main(String[] args) throws InvalidInputMoveException {
        Game game = new Game(10);
        while(true){

            System.out.println("Enter Q to Quit the Game");
            System.out.print("Enter your position guess:");
            String inputMove = game.player.getMove();

            if(inputMove.equals("122333444455555")){
                game.computerBoard.printBoard();
            }
            else if(inputMove.equals("Q")){
                System.out.println("Computer has won!");
                break;
            }
            else if(game.computerBoard.checkInvalidInputMove(inputMove)){
                ArrayList<Coordinate> coordinateArrayList;

                Coordinate coordinate = game.getCoordinates(inputMove);
                System.out.println(coordinate.getX() + " " + coordinate.getY());
                coordinateArrayList = game.computerBoard.checkHitOrMissOrSink(coordinate);
                int coordinateArrayLength = coordinateArrayList.size();
                if (coordinateArrayLength == 0) {
                    ArrayList<Coordinate> coordinates = new ArrayList<>();
                    coordinates.add(coordinate);
                    game.playerBoard.updateBoard(coordinates, "MISS");
                }
                else if(coordinateArrayLength == 1){
                    game.playerBoard.updateBoard(coordinateArrayList,"HIT");
                }
                else{
                   game.playerBoard.updateBoard(coordinateArrayList,"SINK");
                   game.numberOfShipSunk += 1;
                }

                System.out.println("Number of ships sunk: " + game.numberOfShipSunk);
                game.playerBoard.printBoard();

                if(game.numberOfShipSunk == game.shipNames.length){
                    System.out.println("You Won The Game");
                    break;
                }
            }
        }
    }
    private Coordinate getCoordinates(String inputMove){
        int y = (int)(Character.toUpperCase(inputMove.charAt(0)))-65;
        int x = Integer.parseInt(inputMove.substring(1));
        return new Coordinate(x,y);
    }

}
