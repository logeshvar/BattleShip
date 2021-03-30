import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ComputerBoardTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void shouldPrintBoardMatrixWithValues() {
        ComputerBoard computerBoard = new ComputerBoard(2, new Computer(2));
        computerBoard.initialize();
        computerBoard.printBoard();
        assertEquals("    A B\n  1 - - \n  2 - - \n",outContent.toString());
    }

    @Test
    void shouldSetShipsWhenShipNameAndLengthIsProvided() {
        String[] shipName = new String[]{ "Destroyer","Carrier"};
        int[] shipLength = new int[]{3,5};
        ComputerBoard computerBoard = new ComputerBoard(10,new Computer(10));
        computerBoard.setShips(shipName,shipLength);
        int n = 0;
        for(int i = 0;i<5;i++){
            if(computerBoard.ships[i] != null){
                n += 1;
            }
        }
        assertEquals(2,n);
    }

    @Test
    void shouldReturnTrueForValidInputMove(){
        ComputerBoard computerBoard = new ComputerBoard(10, new Computer(10));
        String inputMove = "A1";
        boolean result = computerBoard.checkValidInputMove(inputMove);
        assertTrue(result);
    }
    @Test
    void shouldReturnFalseForInvalidInputMove(){
        ComputerBoard computerBoard = new ComputerBoard(10, new Computer(10));
        String inputMove1 = "A11";
        String inputMove2 = "s1";
        String inputMove3 = "awertq";
        boolean result1 = computerBoard.checkValidInputMove(inputMove1);
        boolean result2 = computerBoard.checkValidInputMove(inputMove2);
        boolean result3 = computerBoard.checkValidInputMove(inputMove3);
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);

    }

    @Test
    void shouldReturnNullIfShipIsNotInCoordinate() {
        ComputerBoard computerBoard = new ComputerBoard(10, new Computer(10));
        String[] shipName = new String[]{ "Destroyer","Carrier"};
        int[] shipLength = new int[]{3,5};
        computerBoard.initialize();
        computerBoard.setShips(shipName,shipLength);
        assertNull(computerBoard.checkHitOrMissOrSink(new Coordinate(1,1)));
    }
}