package Game;

import Game.Scenes.IScene;
import Game.Scenes.SceneResult;

import java.lang.reflect.InvocationTargetException;

public class Game {
    public void Start() {
        var sceneResult = new SceneResult("MainMenu");
        do {
            var scene = GetNextScene(sceneResult);
            sceneResult = scene.Run();
        } while (!sceneResult.NeedToExit());
    }
    
    private IScene GetNextScene(SceneResult sceneResult) {
        Object scene;
        try {
            var sceneType = Class.forName("Game.Scenes." + sceneResult.GetNextSceneName());
            var sceneCtor = sceneType.getConstructor(); //
            scene =  sceneCtor.newInstance();
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
