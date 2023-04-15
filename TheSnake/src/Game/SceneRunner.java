package Game;

import Game.Context.ExecutionContext;
import Game.Scenes.DebugScene;
import Game.Scenes.IScene;
import Game.Scenes.SceneResult;

import java.lang.reflect.InvocationTargetException;

public class SceneRunner {
    private ExecutionContext executionContext;
    
    public SceneRunner() {
        executionContext = new ExecutionContext(25, 25);
    }
    public void Start() {
        Thread.currentThread().setName("Runner");
        var sceneResult = new SceneResult(DebugScene.class.getSimpleName()); // ForDebug
//        var sceneResult = new SceneResult("MainMenu");
        do {
            var nextScene = GetNextScene(sceneResult);
            sceneResult = nextScene.Run(executionContext);
        } while (!sceneResult.NeedToExit());
    }
    
    private IScene GetNextScene(SceneResult sceneResult) {
        Object scene;
        try {
            var sceneType = Class.forName("Game.Scenes." + sceneResult.GetNextSceneName());
            var sceneCtor = sceneType.getConstructor(); //
            scene = sceneCtor.newInstance();
            if (!(scene instanceof IScene)) {
                throw new InstantiationException("Class " + sceneResult.GetNextSceneName() + " is not a scene!");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return (IScene) scene;
    }
}
