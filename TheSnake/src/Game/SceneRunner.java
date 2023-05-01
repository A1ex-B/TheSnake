package Game;

import Game.Context.ExecutionContext;
import Game.Context.OutOfCanvasException;
import Game.Scenes.Game;
import Game.Scenes.IScene;
import Game.Scenes.MainMenu;
import Game.Scenes.SceneResult;

import java.lang.reflect.InvocationTargetException;

public class SceneRunner {
    private ExecutionContext executionContext;
    
    public SceneRunner() {
        executionContext = new ExecutionContext(GlobalConfiguration.canvasWidth, GlobalConfiguration.canvasHeight, GlobalConfiguration.startTimerDelay);
    }
    public void Start() throws OutOfCanvasException, InterruptedException {
        Thread.currentThread().setName("Runner");
//        var sceneResult = new SceneResult(DebugScene.class.getSimpleName()); // ForDebug
        var sceneResult = new SceneResult(Game.class.getSimpleName());
        do {
            var nextScene = GetNextScene(sceneResult);
            sceneResult = nextScene.run(executionContext);
        } while (!sceneResult.NeedToExit());
        executionContext.close();
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
