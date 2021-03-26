import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void shouldReturnInputMoveIfItIsValidPosition() throws InvalidInputMoveException {
        Board board = new Board(10);
        Player player = new Player(board);


        String input = "a5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String inputMove = player.getMove();
        assertEquals("A5",inputMove);

    }

    @Test
    void shouldThrowExceptionIfInputHasInvalidColumnPosition() {
        Board board = new Board(10);
        Player player = new Player(board);

        String message = "";
        String input = "y5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter the valid column",message);
    }

    @Test
    void shouldThrowExceptionIfInputHasInvalidRowPosition() {
        Board board = new Board(10);
        Player player = new Player(board);

        String message = "";
        String input = "at";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter the valid row number",message);
    }

    @Test
    void shouldThrowExceptionWhenInputOfInvalidSize() {
        Board board = new Board(10);
        Player player = new Player(board);

        String message = "";
        String input = "A11";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter valid input move of length two",message);

    }

    @Test
    void shouldReturnTrueIfBoardIsCreatedSuccessfully(){
        FakeBoard fakeBoard = new FakeBoard(10);
        Player player = new Player(fakeBoard);
        player.board.initialize();
        assertTrue(fakeBoard.isCalled);
    }

    private static class FakeBoard extends Board{
        boolean isCalled;

        public FakeBoard(int boardSize) {
            super(boardSize);

        }

        @Override
        void initialize() {
            super.initialize();
            isCalled = true;
        }
    }
}