package Game.Scenes;

import Game.Context.KeyListener;
import Game.Context.Keys;
import Game.GlobalConfiguration;

import java.io.Console;
import java.io.IOException;

public class DebugScene implements IScene {
    public SceneResult Run() {
        System.out.println("Hello!");
        var key = Keys.Default;
        var keyListener = new KeyListener(GlobalConfiguration.WaitBetweenKeyListenig);
        try {
            var prevKey = Keys.Default;
            int counter = 0;
            System.out.println("Started debug scene.");
            do {
                key = keyListener.GetKey();
                if (prevKey != key) {
                    System.out.print("Debug scene alive: " + key + " : " + key.GetCode() + "        : " + counter++ +"\r\n");
                    prevKey = key;
                }
            }
            while (key != Keys.Esc);
            keyListener.Stop();
            System.out.println("Bye! Press any key...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult("Exit", true);
    }
}
