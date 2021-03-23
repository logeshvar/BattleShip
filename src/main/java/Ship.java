public class Ship {
    private final int rowNo;
    private final int columnNo;
    private final int shipLength;
    private final int shipDirection;
    private int[] hitArray;

    Ship(int rowNo, int columnNo, int shipLength, int shipDirection){
        this.rowNo = rowNo;
        this.columnNo =columnNo;
        this.shipLength = shipLength;
        this.shipDirection = shipDirection;
        this.hitArray =new int[shipLength];
    }
}
