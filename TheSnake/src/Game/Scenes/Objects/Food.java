package Game.Scenes.Objects;

import Game.Context.ExecutionContext;
import Game.Context.OutOfCanvasException;
import Game.Context.VectorInt2d;
import Game.GlobalConfiguration;

public class Food extends GameObject {
    private VectorInt2d position;
    private int lifeTime;
    
    public Food(ExecutionContext executionContext, byte[][] gameField) {
        super(executionContext, gameField);
        position = getNextPosition();
        gameField[position.x][position.y] = 2;
    }
    
    @Override
    public void draw() throws OutOfCanvasException {
        executionContext.plotter.drawString(position, "*");
    }
    
    private VectorInt2d getNextPosition() {
        int nextX;
        int nextY;
        do {
            nextY = (int) Math.round(Math.random() * gameField[0].length);
            nextX = (int) Math.round(Math.random() * gameField.length);
        } while (gameField[nextX][nextY] != 0);
        var nextPosition = new VectorInt2d((int) nextX, (int) nextY);
        return nextPosition;
    }
    
    @Override
    public byte move() {
        if (--lifeTime == 0) {
            gameField[position.x][position.y] = 0;
            position = getNextPosition();
            gameField[position.x][position.y] = 2;
            lifeTime = (int) Math.round(Math.random() * GlobalConfiguration.maxFoodLifeTime);
        }
        return getMarker();
    }
    
    @Override
    public void collide() {
        getNextPosition();
    }
    
    @Override
    public byte getMarker() {
        return 2;
    }
    
    @Override
    public VectorInt2d getPosition() {
        return position;
    }
}
