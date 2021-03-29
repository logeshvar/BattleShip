import java.util.Random;

public class Computer {

    private final int max, min;

    Computer(int size) {
        this.min = 0;
        this.max = size;
    }

    public Coordinate generateRandomCoordinate() {
        Random random = new Random();
        int tempColumnNumber = random.nextInt(this.max - this.min) + this.min;
        int tempRowNumber = random.nextInt(this.max - this.min) + this.min;
        Coordinate coordinate = new Coordinate(tempRowNumber,tempColumnNumber);
        return coordinate;

    }

}
    /*
    private void assignShipAtRandomPositions(Board board,Set<String> generatedPositions, Set<Integer> generatedLengthIndexes, int[] generatedLength, int[] generatedDir) {
        Random random = new Random();
        while (!(generatedPositions.size() == shipsNeeded)) {

            int tempColumnNumber = random.nextInt(this.max - this.min) + this.min;
            String tempColumn = String.valueOf((char) ((char) 65 + tempColumnNumber));
            int tempRowNumber = random.nextInt(this.max - this.min) + this.min;
            int tempDirection = random.nextInt(2);
            int tempLengthArrayIndex = random.nextInt(shipLengthArray.length);

            if ((!generatedPositions.contains(tempColumn.concat(String.valueOf(tempRowNumber)))) && (!generatedLengthIndexes.contains(tempLengthArrayIndex))) {

                if ((tempDirection == 0 && tempColumnNumber + shipLengthArray[tempLengthArrayIndex] < this.max) || (tempDirection == 1 && tempRowNumber + shipLengthArray[tempLengthArrayIndex] < this.max)) {
                    int rowShift, columnShift;
                    int[] shiftValues = getShiftValues(tempDirection);
                    rowShift = shiftValues[0];
                    columnShift = shiftValues[1];
                    if (board.boardMatrix[tempRowNumber][tempColumnNumber] == '-') {
                        if (checkIfValidShipPositions(board, shipLengthArray, tempColumnNumber, tempRowNumber, tempLengthArrayIndex, rowShift, columnShift))
                            generatePositions(generatedPositions, generatedLengthIndexes, generatedLength, generatedDir, shipLengthArray, tempColumnNumber, tempColumn, tempRowNumber, tempLengthArrayIndex, tempDirection, board);
                    }
                }
            }
        }
    }*/

    /*private int[] getShiftValues(int tempDirection) {
         int[] shiftValues = new int[2];
        if (tempDirection == 0) {
            shiftValues[1] = 1;
        } else {
            shiftValues[0] = 1;
        }
        return shiftValues;
    }

    private HashMap<String, Ship> generateShipHashMap(Set<String> generatedPositions, int[] generatedLength, int[] generatedDir) {
        HashMap<String, Ship> shipHashMap = new HashMap<>();
        Ship[] shipArray = new Ship[shipsNeeded];
        int arrayPosition = 0;
        for (String position : generatedPositions) {
            int y = (int) (position.charAt(0)) - 65;
            int x = Integer.parseInt(String.valueOf(position.charAt(1)));
            shipArray[arrayPosition] = new Ship(x, y, generatedLength[arrayPosition], generatedDir[arrayPosition]);
            int shipDirection = generatedDir[arrayPosition];
            int rowShift, columnShift;
            int[] shiftValues = getShiftValues(shipDirection);
            rowShift = shiftValues[0];
            columnShift = shiftValues[1];
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
        for (int position = 1; position < shipLengthArray[tempLengthArrayIndex]; position++) {
            if ((tempRowNumber + rowShift) >= 0 && (tempRowNumber + rowShift) < this.max && (tempColumnNumber + columnShift) >= 0 && (tempColumnNumber + columnShift) < this.max) {
                if (board.boardMatrix[tempRowNumber + (rowShift * position)][tempColumnNumber + (columnShift * position)] == 's') {
                    return false;
                }
            }
        }
        return true;
    }

    private void generatePositions(Set<String> generatedPositions, Set<Integer> generatedLengthIndexes, int[] generatedLength, int[] generatedDir, int[] shipLengthArray, int tempColumnNumber, String tempColumn, int tempRowNumber, int tempLengthArrayIndex, int tempDirection, Board board) {
        int rowShift, columnShift;
        int[] shiftValues = getShiftValues(tempDirection);
        rowShift = shiftValues[0];
        columnShift = shiftValues[1];
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

    public int getGeneratedPositionsSize() {
        return generatedPositions.size();
    }

     */

