package Game.Scenes;

import Game.Context.ExecutionContext;
import Game.Context.OutOfCanvasException;

public interface IScene {
    public SceneResult run(ExecutionContext executionContext) throws OutOfCanvasException, InterruptedException;
}
