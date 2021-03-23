public class Ship {
    protected final int rowNo;
    protected final int columnNo;
    protected final int shipLength;
    private final int shipDirection;
    private int hitArray;

    Ship(int rowNo, int columnNo, int shipLength, int shipDirection){
        this.rowNo = rowNo;
        this.columnNo =columnNo;
        this.shipLength = shipLength;
        this.shipDirection = shipDirection;
        this.hitArray =0;
    }
}
