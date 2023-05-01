package Game.Context;

public abstract class Vector2d<T extends  Number> {
    public T x;
    public T y;
    
    public Vector2d(T x, T y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean isEqual(Vector2d<T> vector) {
        return this.x.equals(vector.x) && this.y.equals(vector.y);
    }
    public abstract Vector2d<T> add(Vector2d<T> increment);
}
