import java.util.ArrayList;
import java.util.HashMap;

public class Game {
    private static final String CHEATCODE = "122333444455555";
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
        computerBoard = new ComputerBoard(size);
        computerBoard.initialize();
        computerBoard.setShips(shipNames, shipLengths, computer);
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
                Coordinate coordinate = game.getCoordinatesFromInputMove(inputMove);
                if (alreadyAttackedCoordinate(game, coordinate)) continue;
                HashMap<String,Object> resultHashMap;
                resultHashMap = game.computerBoard.checkHitOrMissOrSink(coordinate);

                if (missedCoordinate(game, resultHashMap)) continue;

                hitOrSinkShipAtCoordinate(game, resultHashMap);
                if (allShipsSunk(game)) break;
            }
            }
        }

    private static boolean allShipsSunk(Game game) {
        if (game.numberOfShipSunk == game.shipNames.length) {
            System.out.println("\nYou Won The Game");
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static void hitOrSinkShipAtCoordinate(Game game, HashMap<String, Object> resultHashMap) {
        if (resultHashMap.get("result") == Result.HIT) {
            updatePlayerBoardOnHitStatus(game, (ArrayList<Coordinate>) resultHashMap.get("coordinates"),  Result.HIT);
        } else {
            updatePlayerBoardOnHitStatus(game, (ArrayList<Coordinate>) resultHashMap.get("coordinates"), Result.SINK);
            game.numberOfShipSunk += 1;
            }
    }

    @SuppressWarnings("unchecked")
    private static boolean missedCoordinate(Game game, HashMap<String, Object> resultHashMap) {
        if (resultHashMap.get("result") == Result.MISS) {
            updatePlayerBoardOnHitStatus(game, (ArrayList<Coordinate>) resultHashMap.get("coordinates"),  Result.MISS);
            return true;
        }
        return false;
    }

    private static boolean alreadyAttackedCoordinate(Game game, Coordinate coordinate) {
        if(game.playerBoard.checkIfAlreadyAttacked(coordinate)){
            System.out.println("Board coordinate already attacked!");
            return true;
        }
        return false;
    }

    private static void printQuitGameMessage(Game game) {
        System.out.println("\nComputer has won!");
        game.computerBoard.printBoard();
    }

    private static void updatePlayerBoardOnHitStatus(Game game, ArrayList<Coordinate> coordinateArrayList, Result hitStatus) {
        System.out.println(hitStatus);
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
