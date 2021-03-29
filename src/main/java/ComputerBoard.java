import java.util.ArrayList;
import java.util.Random;

public class ComputerBoard implements Board{
    protected final char[][] boardMatrix;
    int boardSize;
    Computer computer;
    Ship[] ships;
    private final int orientations = 2;
    public ComputerBoard(int boardSize, Computer computer) {
        this.boardSize = boardSize;
        this.computer = computer;
        boardMatrix = new char[boardSize][boardSize];
        ships = new Ship[5];
    }

    public void initialize() {
        for (int row = 0; row < this.boardSize; row++) {
            for (int col = 0; col < this.boardSize; col++) {
                boardMatrix[row][col] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("  A B C D E F G H I J");
        for (int row = 0; row < this.boardSize; row++) {
            System.out.print(row + " ");
            for (int column = 0; column < this.boardSize; column++) {
                System.out.print(this.boardMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    public void setShips(String[] shipNames, int[] shipLengths){
        for(int position = 0; position <= shipNames.length; position++){
            Coordinate coordinate = computer.generateRandomCoordinate();
            int orientation = new Random().nextInt(orientations);
            if(this.checkValidCoordinate(coordinate, shipLengths[position], orientation)){
                ships[position] = new Ship(shipNames[position],shipLengths[0],coordinate,orientation);
                ArrayList<Coordinate> coordinatesList = this.updateBoard(coordinate,shipLengths[position],orientation);
                ships[position].setLocation(coordinatesList);
            }
        }
    }

    private ArrayList<Coordinate> updateBoard(Coordinate coordinate, int shipLength, int orientation) {
        int columnShift = getShiftValues(orientation)[1], rowShift = getShiftValues(orientation)[0];
        ArrayList<Coordinate> coordinateList = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int rowNo = x; rowNo <= rowNo+(rowShift*shipLength); rowNo++) {
            for (int colNo = y; colNo <= colNo+(columnShift*shipLength); colNo++) {
                coordinateList.add(new Coordinate(rowNo,colNo));
                this.boardMatrix[rowNo][colNo] = 's';
            }
        }
        return coordinateList;
    }

    private boolean checkValidCoordinate(Coordinate coordinate, int shipLength, int orientation) {
        if(withinRowBounds(coordinate, shipLength, orientation) && withinColumnBounds(coordinate, shipLength, orientation)) {
            int columnShift = getShiftValues(orientation)[1], rowShift = getShiftValues(orientation)[0];
            int x = coordinate.getX();
            int y = coordinate.getY();
            for (int start = 0; start < shipLength; start++) {
                if (this.boardMatrix[x +rowShift][y +columnShift] =='s'){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private int[] getShiftValues(int orientation) {
        int[] shiftValues = new int[2];
        if (orientation == 0) {
            shiftValues[1] = 1;
        } else {
            shiftValues[0] = 1;
        }
        return shiftValues;
    }

    private boolean withinRowBounds(Coordinate coordinate, int shipLength, int orientation) {
        return orientation == 0 && (coordinate.getX() + shipLength) <= this.boardSize;
    }

    private boolean withinColumnBounds(Coordinate coordinate, int shipLength, int orientation) {
        return orientation == 1 && (coordinate.getY() + shipLength) <= this.boardSize;
    }
}
