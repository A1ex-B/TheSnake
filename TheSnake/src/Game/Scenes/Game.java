package Game.Scenes;

import Game.Context.ExecutionContext;
import Game.Context.Lines.DoubleLines;
import Game.Context.OutOfCanvasException;
import Game.Context.VectorInt2d;
import Game.GlobalConfiguration;
import Game.Scenes.Objects.Food;
import Game.Scenes.Objects.GameObject;
import Game.Scenes.Objects.Snake;

import java.util.ArrayList;

public class Game implements IScene {
    private int score = 0;
    private int scorePerFood = GlobalConfiguration.startFoodScore;
    private ArrayList<GameObject> gameObjects;
    
    public Game() {
    }
    
    private void drawField(ExecutionContext executionContext) throws OutOfCanvasException {
        var doubleLines = new DoubleLines();
        executionContext.plotter.drawRectangle(new VectorInt2d(0, 0), GlobalConfiguration.fieldWidth + 2,
                GlobalConfiguration.fieldHeight, doubleLines);
    }
    
    private void initGame(ExecutionContext executionContext) throws OutOfCanvasException {
        drawField(executionContext);
        var gameField = new byte[GlobalConfiguration.fieldWidth][GlobalConfiguration.fieldHeight];
        gameObjects = new ArrayList<>();
        gameObjects.add(new Food(executionContext, gameField));
        gameObjects.add(new Snake(executionContext, gameField));
    }
    
    private void collide() {
        for (int i = 0; i < gameObjects.size(); i++) {
            var firstPos = gameObjects.get(i).getPosition();
            for (int j = i + 1; j < gameObjects.size(); j++) {
                if (gameObjects.get(j).getPosition().isEqual(firstPos)) {
                    gameObjects.get(i).collide();
                    gameObjects.get(j).collide();
                    return;
                }
            }
        }
    }
    
    @Override
    public SceneResult run(ExecutionContext executionContext) throws OutOfCanvasException, InterruptedException {
        executionContext.plotter.clearCanvas();
        initGame(executionContext);
        for (var gameObject : gameObjects) {
            gameObject.draw();
        }

        boolean needExit = false;
        do {
            for (var gameObject: gameObjects) {
                var token = gameObject.move();
                if (token == -1) {
                    needExit = true;
                }
            }
            
            collide();
            executionContext.plotter.resetPosition();
            executionContext.timer.waitNextTick();
        } while (!needExit);
        return null;
    }
}
