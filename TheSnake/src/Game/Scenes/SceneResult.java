package Game.Scenes;

public class SceneResult {
    private String NextSceneName;
    private boolean NeedExit = false;
    
    public SceneResult(String nextScene, boolean needExit) {
        this(nextScene);
        this.NeedExit = needExit;
    }
    
    public SceneResult(String nextScene) {
        if (nextScene == null) {
            throw new IllegalArgumentException("Parameter nextScene can't be null");
        }
        this.NextSceneName = nextScene;
    }
    
    public String GetNextSceneName() {
        return NextSceneName;
    }
    
    public boolean NeedToExit() {
        return NeedExit;
    }
}
