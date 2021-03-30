import java.util.ArrayList;

public class Ship {
    protected final String shipName;
    protected final Coordinate startingPosition;
    protected final int shipLength;
    protected final int orientation;
    protected int numberOfHits;
    private ArrayList<Coordinate> coordinatesList = null;


    Ship(String shipName, int shipLength, Coordinate coordinate, int orientation) {
        this.shipName = shipName;
        this.startingPosition = coordinate;
        this.shipLength = shipLength;
        this.orientation = orientation;
        this.numberOfHits = 0;
    }

    public void gotHit() {
        this.numberOfHits += 1;
    }

    public boolean hasSunk() {
        if (this.numberOfHits == this.shipLength) {
            System.out.println(this.shipName + " has sunk!");
            return true;
        }
        return false;
    }

    public void setLocation(ComputerBoard computerBoard) {
        if (this.orientation == 0) {
            this.coordinatesList = setShipHorizontal(computerBoard, this.startingPosition);
        } else {
            this.coordinatesList = setShipVertical(computerBoard, this.startingPosition);
        }
    }

    private ArrayList<Coordinate> setShipVertical(ComputerBoard computerBoard, Coordinate coordinate) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int value = 0; value < this.shipLength; value++) {
            Coordinate currentCoordinate = coordinate.addX(value);
            coordinates.add(currentCoordinate);
            computerBoard.boardMatrix[currentCoordinate.getX()][currentCoordinate.getY()] = 's';
        }
        return coordinates;
    }

    private ArrayList<Coordinate> setShipHorizontal(ComputerBoard computerBoard, Coordinate coordinate) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();
        for (int value = 0; value < this.shipLength; value++) {
            Coordinate currentCoordinate = coordinate.addY(value);
            coordinates.add(currentCoordinate);
            computerBoard.boardMatrix[currentCoordinate.getX()][currentCoordinate.getY()] = 's';
        }
        return coordinates;
    }

    public ArrayList<Coordinate> getLocation() {
        return this.coordinatesList;
    }

}
