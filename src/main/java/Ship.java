public class Ship {
    protected final int rowNo;
    protected final int columnNo;
    protected final int shipLength;
    private final int  orientation;
    private int numberOfHits;
    String[] shipNames= new String[]{"Carrier","Battleship","Destroyer","Submarine","Patrol Boat"};
    int[] shipLengths = new int[]{5,4,3,3,2};

    Ship(Coordinate coordinate, int shipLength, int orientation){
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
        return this.numberOfHits == this.shipLength;
    }


}
