package Game.Context;

public class VectorInt2d extends Vector2d<Integer> {
    public VectorInt2d(Integer x, Integer y) {
        super(x, y);
    }
    
    @Override
    public Vector2d<Integer> add(Vector2d<Integer> increment) {
        return new VectorInt2d(this.x + increment.x, this.y + increment.y);
    }
}
