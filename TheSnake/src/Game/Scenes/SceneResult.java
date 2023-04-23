package Game.Scenes;

public class SceneResult {
    private String nextSceneName;
    private boolean needExit = false;
    
    public SceneResult(String nextScene) {
        this.nextSceneName = nextScene;
        this.needExit = false;
    }
    
    public SceneResult() {
        this.nextSceneName = null;
        this.needExit = true;
    }
    
    public String GetNextSceneName() {
        return nextSceneName;
    }
    
    public boolean NeedToExit() {
        return needExit;
    }
}
