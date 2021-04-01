import java.util.ArrayList;

public class Game {
    public static final String CHEATCODE = "122333444455555";
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
            String inputMove = game.player.getMove();

            if (inputMove.equals(CHEATCODE)) {
                game.computerBoard.printBoard();
            } else if (inputMove.equals("Q") || inputMove.equals("q")) {
                printQuitGameMessage(game);
                break;
            } else if (game.computerBoard.checkValidInputMove(inputMove)) {
                ArrayList<Coordinate> coordinateArrayList;

                Coordinate coordinate = game.getCoordinatesFromInputMove(inputMove);
                if(game.playerBoard.checkIfAlreadyAttacked(coordinate)){
                    System.out.println("Board coordinate already attacked!");
                    continue;
                }
                coordinateArrayList = game.computerBoard.checkHitOrMissOrSink(coordinate);
                int coordinateArrayLength = coordinateArrayList.size();
                if (coordinateArrayLength == 0) {  //Checking for miss
                    updatePlayerBoardOnHitStatus(game, coordinateArrayList, "MISS", Result.MISS);
                    continue;
                }

                if (coordinateArrayLength == 1) {
                    updatePlayerBoardOnHitStatus(game, coordinateArrayList, "HIT", Result.HIT);
                } else {
                    updatePlayerBoardOnHitStatus(game, coordinateArrayList, "SINK", Result.SINK);
                    game.numberOfShipSunk += 1;
                    if (game.numberOfShipSunk == game.shipNames.length) {
                        System.out.println("\nYou Won The Game");
                        break;
                        }
                    }
                }
            }
        }

    private static void printQuitGameMessage(Game game) {
        System.out.println("\nComputer has won!");
        game.computerBoard.printBoard();
    }

    private static void updatePlayerBoardOnHitStatus(Game game, ArrayList<Coordinate> coordinateArrayList, String status, Result hitStatus) {
        System.out.println(status);
        game.playerBoard.updateBoard(coordinateArrayList, hitStatus);
        System.out.println("\nNumber of ships sunk: " + game.numberOfShipSunk);
        game.playerBoard.printBoard();
    }

    private Coordinate getCoordinatesFromInputMove(String inputMove) {
        int y = (int) (Character.toUpperCase(inputMove.charAt(0))) - 65;
        int x = Integer.parseInt(inputMove.substring(1)) - 1;
        return new Coordinate(x, y);
    }

}
