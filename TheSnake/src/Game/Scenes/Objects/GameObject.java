package Game.Scenes.Objects;

import Game.Context.ExecutionContext;
import Game.Context.OutOfCanvasException;
import Game.Context.VectorInt2d;

public abstract class GameObject {
    protected ExecutionContext executionContext;
    protected byte[][] gameField;
    public GameObject(ExecutionContext executionContext, byte[][] gameField) {
        this.executionContext = executionContext;
        this.gameField = gameField;
    }
    public abstract void draw() throws OutOfCanvasException;
    
    /**
     * Returns marker in new position;
     * */
    public abstract byte move() throws OutOfCanvasException;
    public abstract void collide();
    
    public abstract byte getMarker();
    public abstract VectorInt2d getPosition();
}
