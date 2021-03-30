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

        shipNames = new String[]{"Carrier", "Battleship", "Destroyer", "Submarine", "Patrol Boat"};
        shipLengths = new int[]{5, 4, 3, 3, 2};

        computer = new Computer(size);
        computerBoard = new ComputerBoard(size, computer);
        computerBoard.initialize();
        computerBoard.setShips(shipNames, shipLengths);
        playerBoard = new PlayerBoard(size);
        playerBoard.initialize();

        player = new Player();

    }


    public static void main(String[] args) {
        Game game = new Game(10);
        game.playerBoard.printBoard();
        while (true) {

            System.out.println("Enter Q to Quit the Game");
            System.out.print("Enter your position guess:");
            String inputMove = game.player.getMove();

            if (inputMove.equals("122333444455555")) {
                game.computerBoard.printBoard();
            } else if (inputMove.equals("Q") || inputMove.equals("q")) {
                System.out.println("Computer has won!");
                break;
            } else if (game.computerBoard.checkValidInputMove(inputMove)) {
                ArrayList<Coordinate> coordinateArrayList;

                Coordinate coordinate = game.getCoordinates(inputMove);
                if (!game.playerBoard.checkIfAlreadyAttacked(coordinate)) {
                    coordinateArrayList = game.computerBoard.checkHitOrMissOrSink(coordinate);
                    if (coordinateArrayList == null) {
                        ArrayList<Coordinate> coordinates = new ArrayList<>();
                        coordinates.add(coordinate);
                        System.out.println("MISS");
                        game.playerBoard.updateBoard(coordinates, "MISS");
                    } else {
                        int coordinateArrayLength = coordinateArrayList.size();
                        if (coordinateArrayLength == 1) {
                            System.out.println("HIT");
                            game.playerBoard.updateBoard(coordinateArrayList, "HIT");
                        } else {
                            game.playerBoard.updateBoard(coordinateArrayList, "SINK");
                            game.numberOfShipSunk += 1;
                        }
                    }
                    System.out.println("Number of ships sunk: " + game.numberOfShipSunk);
                    game.playerBoard.printBoard();

                    if (game.numberOfShipSunk == game.shipNames.length) {
                        System.out.println("You Won The Game");
                        break;
                    }
                } else {
                    System.out.println("Board Coordinate Already Attacked!");
                }
            }
        }
    }

    private Coordinate getCoordinates(String inputMove) {
        int y = (int) (Character.toUpperCase(inputMove.charAt(0))) - 65;
        int x = Integer.parseInt(inputMove.substring(1)) - 1;
        return new Coordinate(x, y);
    }

}
