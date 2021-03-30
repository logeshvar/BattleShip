import java.util.Random;

public class Computer {

    private final int max, min;

    Computer(int size) {
        this.min = 0;
        this.max = size;
    }

    public Coordinate generateRandomCoordinate() {
        Random random = new Random();
        int tempColumnNumber = random.nextInt(this.max - this.min) + this.min;
        int tempRowNumber = random.nextInt(this.max - this.min) + this.min;
        return new Coordinate(tempRowNumber,tempColumnNumber);

    }

}

