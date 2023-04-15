package Game.Context;

public class ExecutionContext {
    public Plotter plotter;
    public KeyListener keyListener;
    
    public ExecutionContext(int canvasWidth, int canvasHeight) {
        this.plotter = new Plotter(canvasWidth, canvasHeight);
        this.keyListener = new KeyListener();
    }
}
