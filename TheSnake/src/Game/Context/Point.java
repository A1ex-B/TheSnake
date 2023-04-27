package Game.Context;

public class Point {
    public final VectorInt2d position;
    public final char character;
    protected Point(VectorInt2d position, char character) {
        this.position = position;
        this.character = character;
    }
}
