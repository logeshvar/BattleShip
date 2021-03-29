import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    @Test
    void shouldReturnCorrectCoordinateObjectForGivenCoordinate(){
        Coordinate coordinate = new Coordinate(2,2);
        assertEquals(2,coordinate.getX());
        assertEquals(2,coordinate.getY());
    }

    @Test
    void shouldReturnTrueForEqualCoordinates(){
        Coordinate coordinate1 = new Coordinate(2,2);
        Coordinate coordinate2 = new Coordinate(2,2);
        assertEquals(coordinate2, coordinate1);
    }

    @Test
    void shouldReturnFalseForUnequalCoordinates(){
        Coordinate coordinate1 = new Coordinate(2,2);
        Coordinate coordinate2 = new Coordinate(3,2);
        assertNotEquals(coordinate2, coordinate1);
    }
}