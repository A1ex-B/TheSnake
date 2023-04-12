package Game.Context;

public class ExecutionContext {
    public Canvas canvas;
    public KeyListener keyListener;
    
    public ExecutionContext(int canvasWidth, int canvasHeight) {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
        this.keyListener = new KeyListener();
    }
}
