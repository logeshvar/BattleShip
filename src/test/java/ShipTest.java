import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    void ShouldIncrementHitArrayIfShipGotHit() {
        Ship ship = new Ship(2,2,2,1);
        ship.gotHit();
        assertEquals(1,ship.hitArray);

    }

    @Test
    void ShouldReturnTrueWhenShipHasSunk() {
        Ship ship = new Ship(2,2,2,1);
        ship.gotHit();
        ship.gotHit();
        assertTrue(ship.hasSunk());
    }
}