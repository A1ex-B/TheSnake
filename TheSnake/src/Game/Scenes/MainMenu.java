package Game.Scenes;

import Game.RawConsoleInput;

import java.io.IOException;

public class MainMenu implements IScene {
    public SceneResult Run() {
        System.out.println("Hello!");
        int ch = 0;
        try {
            ch = System.in.read();
            System.in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ch = " + ch);
        try {
            var temp = RawConsoleInput.read(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult("Exit", true);
    }
}
