package Game.Context;

public class ExecutionContext {
    public Plotter plotter;
    public KeyListener keyListener;
    public Timer timer;
    
    public ExecutionContext(int canvasWidth, int canvasHeight, int startDelay) {
        this.plotter = new Plotter(canvasWidth, canvasHeight);
        this.keyListener = new KeyListener();
        this.timer = new Timer(startDelay);
    }
    
    public void close() {
        keyListener.Stop();
    }
}
