import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void shouldReturnInputMoveIfItIsValidPosition(){
        Player player = new Player();
        String input = "a5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String inputMove = player.getMove();
        assertEquals("a5",inputMove);

    }



}