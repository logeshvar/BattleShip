import java.util.ArrayList;

public class Ship {
    protected final String shipName;
    protected final int rowNo;
    protected final int columnNo;
    protected final int shipLength;
    protected final int  orientation;
    private int numberOfHits;
    private ArrayList<Coordinate> coordinatesList = null;


    Ship(String shipName, int shipLength, Coordinate coordinate, int orientation){
        this.shipName = shipName;
        this.rowNo = coordinate.getX();
        this.columnNo =coordinate.getY();
        this.shipLength = shipLength;
        this.orientation = orientation;
        this.numberOfHits =0;
    }

    public void gotHit() {
        this.numberOfHits += 1;
    }

    public boolean hasSunk() {
        if(this.numberOfHits == this.shipLength){
            System.out.println(this.shipName + " has sunk!" );
            return true;
        }
        return false;
    }

    public void setLocation(ArrayList<Coordinate> coordinatesList) {
        this.coordinatesList = coordinatesList;
    }
    public ArrayList<Coordinate> getLocation(){
        return this.coordinatesList;
    }
}
