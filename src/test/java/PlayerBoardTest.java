import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerBoardTest {
    @Test
    void ShouldInitializeBoardMatrixWithHypenWhenSizeIsProvided() {
        PlayerBoard playerBoard = new PlayerBoard(10);
        playerBoard.initialize();
        assertEquals('-',playerBoard.boardMatrix[2][2]);
    }

    @Test
    void shouldReturnFalseIfCoordinateNotAlreadyAttacked(){
        PlayerBoard playerBoard = new PlayerBoard(10);
        playerBoard.initialize();
        boolean result = playerBoard.checkIfAlreadyAttacked(new Coordinate(2,2));
        assertFalse(result);
    }
    @Test
    void shouldReturnTrueIfCoordinateIsAlreadyAttacked(){
        PlayerBoard playerBoard = new PlayerBoard(10);
        playerBoard.initialize();
        ArrayList<Coordinate> coordinates= new ArrayList<>();
        coordinates.add(new Coordinate(2,2));
        playerBoard.updateBoard(coordinates,"MISS");
        boolean result = playerBoard.checkIfAlreadyAttacked(new Coordinate(2,2));
        assertTrue(result);
    }

    @Test
    void shouldUpdateBoardCorrectlyForGivenHitStatus(){
        PlayerBoard playerBoard = new PlayerBoard(10);
        playerBoard.initialize();
        ArrayList<Coordinate> coordinates= new ArrayList<>();
        coordinates.add(new Coordinate(2,2));
        playerBoard.updateBoard(coordinates,"MISS");
        assertEquals('M',playerBoard.boardMatrix[2][2]);
    }
}