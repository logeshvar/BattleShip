import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    void ShouldIncrementHitArrayIfShipGotHit() {
        Ship ship = new Ship("Destroyer",3,new Coordinate(2,2),0);

        ship.gotHit();

        assertEquals(1,ship.numberOfHits);

    }

    @Test
    void ShouldReturnTrueWhenShipHasSunk() {
        Ship ship = new Ship("Destroyer",3,new Coordinate(2,2),0);

        ship.gotHit();
        ship.gotHit();
        ship.gotHit();

        assertTrue(ship.hasSunk());
    }

    @Test
    void shouldReturnCorrectlyAssignedCoordinates(){
        Ship ship = new Ship("Destroyer",3,new Coordinate(2,2),0);
        ComputerBoard computerBoard = new ComputerBoard(10,new Computer(10));
        ship.setLocation(computerBoard);
        int x1 = ship.getLocation().get(0).getX();
        int y2 = ship.getLocation().get(1).getY();

        assertEquals(2,x1);
        assertEquals(3,y2);
    }

    @Test
    void shouldSetShipCorrectlyInComputerBoardForGivenLengthAndOrientation() {
        Coordinate coordinate = new Coordinate(2, 2);
        Ship ship = new Ship("Destroyer",3, coordinate,0);
        ComputerBoard computerBoard = new ComputerBoard(10, new Computer(10));
        ship.setLocation(computerBoard);
        assertEquals('s',computerBoard.getBoardMatrixCoordinateValue(coordinate));
    }
}