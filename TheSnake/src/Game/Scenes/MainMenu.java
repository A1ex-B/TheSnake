package Game.Scenes;

public class MainMenu implements IScene {
    public SceneResult Run() {
        System.out.println("Hello!");
        return new SceneResult("Exit", true);
    }
}
