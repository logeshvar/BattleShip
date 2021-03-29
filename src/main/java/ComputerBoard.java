import java.util.ArrayList;
import java.util.Random;

public class ComputerBoard implements Board{
    protected final char[][] boardMatrix;
    int boardSize;
    Computer computer;
    Ship[] ships;
    @SuppressWarnings("FieldCanBeLocal")
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
        System.out.print("   ");
        for(int start = 0; start<this.boardSize;start++){ System.out.print(" "+(char)(65+start)); }
        System.out.println();
        for (int row = 0; row < this.boardSize; row++) {
            System.out.printf("%3d ",(row+1));
            for (int column = 0; column < this.boardSize; column++) {
                System.out.print(this.boardMatrix[row][column] + " ");
            }
            System.out.println();
        }
    }

    public void setShips(String[] shipNames, int[] shipLengths){
        int shipsAssigned = 0;
        while(shipsAssigned!=shipNames.length){
            Coordinate coordinate = computer.generateRandomCoordinate();
            int orientation = new Random().nextInt(orientations);
            if(this.checkValidCoordinate(coordinate, shipLengths[shipsAssigned], orientation)){
                ships[shipsAssigned] = new Ship(shipNames[shipsAssigned],shipLengths[shipsAssigned],coordinate,orientation);
                ArrayList<Coordinate> coordinatesList = this.updateBoard(coordinate,shipLengths[shipsAssigned],orientation);
                ships[shipsAssigned].setLocation(coordinatesList);
                shipsAssigned+=1;
            }
        }
    }

    private ArrayList<Coordinate> updateBoard(Coordinate coordinate, int shipLength, int orientation) {
        int columnShift = getShiftValues(orientation)[1], rowShift = getShiftValues(orientation)[0];
        ArrayList<Coordinate> coordinateList = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();
        for (int start =0; start<shipLength; start++){
            int rowNo = x + (rowShift * start);
            int colNo = y + (columnShift * start);
            Coordinate coordinate1 = new Coordinate(rowNo,colNo);
            coordinateList.add(coordinate1);
            this.boardMatrix[rowNo][colNo] = 's';
            }
        return coordinateList;
    }

    public boolean checkInvalidInputMove(String inputMove){

        if(inputMove.length() > (Integer.toString(this.boardSize).length()+1) ){
            System.out.println("Enter proper input with valid row and column");
            return false;
        }
        int column = (int)(Character.toUpperCase(inputMove.charAt(0)))-65;
        if(column<0 || column >= this.boardSize){
            System.out.println("Enter proper input with valid column");
            return false;
        }

        int row = Integer.parseInt(inputMove.substring(1));

        if(row<1 || row > this.boardSize){
            System.out.println("Enter proper input with valid row");
            return false;
        }
        return true;

    }
    private boolean checkValidCoordinate(Coordinate coordinate, int shipLength, int orientation) {
        if(withinRowBounds(coordinate, shipLength, orientation) || withinColumnBounds(coordinate, shipLength, orientation)) {
            int columnShift = getShiftValues(orientation)[1], rowShift = getShiftValues(orientation)[0];
            int x = coordinate.getX();
            int y = coordinate.getY();
            for (int start = 0; start < shipLength; start++) {
                if (this.boardMatrix[x +(rowShift*start)][y +(columnShift*start)] =='s'){
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
        return orientation == 1 && (coordinate.getX() + shipLength) <= this.boardSize;
    }

    private boolean withinColumnBounds(Coordinate coordinate, int shipLength, int orientation) {
        return orientation == 0 && (coordinate.getY() + shipLength) <= this.boardSize;
    }

    public ArrayList<Coordinate> checkHitOrMissOrSink(Coordinate coordinate) {

        for (Ship ship : ships) {
            if (ship.getLocation().contains(coordinate)) {
                ship.gotHit();
                if (ship.hasSunk()) {
                    return ship.getLocation();
                }
                ArrayList<Coordinate> coordinatesList = new ArrayList<>();
                coordinatesList.add(coordinate);
                return coordinatesList;
            }
        }
        return null;
    }
}
