import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void shouldReturnInputMoveIfItIsValidPosition() throws InvalidInputMoveException {
        Player player = new Player(10);


        String input = "a5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String inputMove = player.getMove();
        assertEquals("A5",inputMove);

    }

    @Test
    void shouldThrowExceptionIfInputHasInvalidColumnPosition() {
        Player player = new Player(10);

        String message = "";
        String input = "y5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            String inputMove = player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter the valid column",message);
    }

    @Test
    void shouldThrowExceptionIfInputHasInvalidRowPosition() {
        Player player = new Player(10);

        String message = "";
        String input = "at";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            String inputMove = player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter the valid row number",message);
    }

    @Test
    void shouldThrowExceptionWhenInputOfInvalidSize() {
        Player player = new Player(10);

        String message = "";
        String input = "A11";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        try {
            String inputMove = player.getMove();
        }
        catch(InvalidInputMoveException receivedException){
            message = receivedException.getMessage();
        }
        assertEquals("Enter valid input move of length two",message);

    }
}