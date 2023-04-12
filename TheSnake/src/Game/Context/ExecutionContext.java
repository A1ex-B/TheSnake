package Game.Context;

public class ExecutionContext {
    public Drawer drawer;
    public KeyListener keyListener;
    
    public ExecutionContext() {
        this.drawer = new Drawer();
        this.keyListener = new KeyListener();
    }
}
