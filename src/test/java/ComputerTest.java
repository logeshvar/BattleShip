import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerTest {

    @Test
    void shouldReturnTrueIfComputerGeneratesPositionsForShipsNeeded() {
        Computer computer = new Computer(new ComputerBoard(10));

        assertEquals(5, computer.getGeneratedPositionsSize());
    }


    @Test
    void shouldReturnTrueIfBoardIsCalledByComputer() {
        FakeBoard fakeBoard = new FakeBoard(10);
        Computer computer = new Computer(fakeBoard);

        computer.board.initialize();

        assertTrue(fakeBoard.isCalled);
    }

    private static class FakeBoard extends ComputerBoard {
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