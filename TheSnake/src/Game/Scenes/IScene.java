package Game.Scenes;

import Game.Context.ExecutionContext;

public interface IScene {
    public SceneResult Run(ExecutionContext executionContext);
}
