import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test
    void ShouldReturnTrueIfSizeOfBoardMatrixIsSameAsTheInputSize() {
        Board board = new Board(5);
        boolean rowAssertion = 5 == board.boardMatrix.length;
        boolean columnAssertion = 5 == board.boardMatrix[0].length;

        assertTrue(rowAssertion);
        assertTrue(columnAssertion);
    }

    @Test
    void ShouldInitializeBoardMatrixWithHyphenWhenInitializeMethodIsCalled(){
        Board board = new Board(5);

        board.initialize();

        assertEquals('-',board.boardMatrix[0][0]);
    }


}