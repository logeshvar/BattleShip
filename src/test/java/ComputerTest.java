import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {
    @Test
    void shouldReturnCoordinateObjectWithRandomPosition() {
        Computer computer = new Computer(10);
        Coordinate coordinate = computer.generateRandomCoordinate();
        assertNotNull(coordinate);
    }
}