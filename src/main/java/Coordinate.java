import java.util.Objects;

public class Coordinate {
    private final int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public Coordinate addX(int value){
        return new Coordinate(this.x+value,this.y);
    }
    public Coordinate addY(int value){
        return new Coordinate(this.x,this.y+value);
    }
}
