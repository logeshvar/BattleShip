import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class Computer {

    private  HashMap<String, Ship> shipHashMap;

    private final int[] shipLengthArray = new int[]{2, 3, 3, 4, 5};
    private final int max , min;
    private final int shipsNeeded;
    Board board;

     Computer(Board board) {
        this.board = board;
        board.initialize();
        min = 0;
        max = board.boardSize;
        shipsNeeded = 5;

        Set<String> generatedPositions = new LinkedHashSet<>();
        Set<Integer> generatedLengthIndexes = new LinkedHashSet<>();

        int[] generatedLength = new int[5];
        int[] generatedDir = new int[5];


        assignShipAtRandomPositions(board,generatedPositions, generatedLengthIndexes, generatedLength, generatedDir);
        shipHashMap = generateShipHashMap( generatedPositions, generatedLength, generatedDir);

    }

    private void assignShipAtRandomPositions(Board board,Set<String> generatedPositions, Set<Integer> generatedLengthIndexes, int[] generatedLength, int[] generatedDir) {
        Random random = new Random();
        while (!(generatedPositions.size() == shipsNeeded)) {

            int tempColumnNumber = random.nextInt(max - min) + min;
            String tempColumn = String.valueOf((char) ((char) 65 + tempColumnNumber));
            int tempRowNumber = random.nextInt(max - min) + min;
            int tempDirection = random.nextInt(2);
            int tempLengthArrayIndex = random.nextInt(shipLengthArray.length);

            if ((!generatedPositions.contains(tempColumn.concat(String.valueOf(tempRowNumber)))) && (!generatedLengthIndexes.contains(tempLengthArrayIndex))) {

                if ((tempDirection == 0 && tempColumnNumber + shipLengthArray[tempLengthArrayIndex] < max) || (tempDirection == 1 && tempRowNumber + shipLengthArray[tempLengthArrayIndex] < max)) {
                    int rowShift, columnShift;
                    if (tempDirection == 0) {
                        rowShift = 0;
                        columnShift = 1;
                    } else {
                        rowShift = 1;
                        columnShift = 0;
                    }
                    if (board.boardMatrix[tempRowNumber][tempColumnNumber] == '-') {
                        if (checkIfValidShipPositions(board, shipLengthArray, tempColumnNumber, tempRowNumber, tempLengthArrayIndex, rowShift, columnShift))
                            generatePositions(generatedPositions, generatedLengthIndexes, generatedLength, generatedDir, shipLengthArray, tempColumnNumber, tempColumn, tempRowNumber, tempLengthArrayIndex, tempDirection, board);
                    }
                }
            }
        }
        shipHashMap = generateShipHashMap(generatedPositions, generatedLength, generatedDir);
    }

    private static HashMap<String, Ship> generateShipHashMap(Set<String> generatedPositions, int[] generatedLength, int[] generatedDir) {
        HashMap<String, Ship> shipHashMap = new HashMap<>();
        Ship[] shipArray = new Ship[5];
        int arrayPosition = 0;
        for (String position : generatedPositions) {
            int y = (int) (position.charAt(0)) - 65;
            int x = Integer.parseInt(String.valueOf(position.charAt(1)));
            shipArray[arrayPosition] = new Ship(x, y, generatedLength[arrayPosition], generatedDir[arrayPosition]);
            int shipDirection = generatedDir[arrayPosition];
            int rowShift, columnShift;
            if (shipDirection == 0){
                rowShift = 0;
                columnShift = 1;
            } else {
                rowShift = 1;
                columnShift = 0;
            }
            for (int start = 0; start < generatedLength[arrayPosition]; start++) {
                String shipPosition;
                shipPosition = ((char) (65 + y + (columnShift * start)) + String.valueOf(x + (rowShift * start)));
                shipHashMap.put(shipPosition, shipArray[arrayPosition]);
            }
            arrayPosition += 1;
        }
        return shipHashMap;
    }

    private boolean checkIfValidShipPositions(Board board, int[] shipLengthArray, int tempColumnNumber, int tempRowNumber, int tempLengthArrayIndex, int rowShift, int columnShift) {
        for (int startPosition = 1; startPosition < shipLengthArray[tempLengthArrayIndex]; startPosition++) {
            if ((tempRowNumber + rowShift) >= 0 && (tempRowNumber + rowShift) < 10 && (tempColumnNumber + columnShift) >= 0 && (tempColumnNumber + columnShift) < 10) {
                if (board.boardMatrix[tempRowNumber + (rowShift * startPosition)][tempColumnNumber + (columnShift * startPosition)] == 's') {
                    return false;
                }
            }
        }
        return true;
    }

    private void generatePositions(Set<String> generatedPositions, Set<Integer> generatedLengthIndexes, int[] generatedLength, int[] generatedDir, int[] shipLengthArray, int tempColumnNumber, String tempColumn, int tempRowNumber, int tempLengthArrayIndex, int tempDirection, Board board) {
        int rowShift, columnShift;
        if (tempDirection == 0) {
            rowShift = 0;
            columnShift = 1;
        } else {
            rowShift = 1;
            columnShift = 0;
        }
        board.boardMatrix[tempRowNumber][tempColumnNumber] = 's';
        for (int start = 1; start < shipLengthArray[tempLengthArrayIndex]; start++) {

            board.boardMatrix[tempRowNumber + (rowShift * start)][tempColumnNumber + (columnShift * start)] = 's';
        }
        generatedPositions.add(tempColumn.concat(String.valueOf(tempRowNumber)));
        generatedLengthIndexes.add(tempLengthArrayIndex);
        generatedLength[generatedPositions.size() - 1] = shipLengthArray[tempLengthArrayIndex];
        generatedDir[generatedPositions.size() - 1] = tempDirection;
    }


    public Ship getShip(String inputMove) {
         return this.shipHashMap.get(inputMove);
    }
}
