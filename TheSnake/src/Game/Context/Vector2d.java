package Game.Context;

public abstract class Vector2d<T extends  Number> {
    public T x;
    public T y;
    
    public Vector2d(T x, T y) {
        this.x = x;
        this.y = y;
    }
}
