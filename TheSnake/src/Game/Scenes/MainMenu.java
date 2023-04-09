package Game.Scenes;

import Game.RawConsoleInput;

import java.io.IOException;

public class MainMenu implements IScene {
    public SceneResult Run() {
        System.out.println("Hello!");
        int ch = 0;
        
        try {
            do {
                ch = RawConsoleInput.read(true);
                System.out.print(ch + "    \r");
            }
            while (ch != 27);
            System.out.println("Bye! Press any key...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult("Exit", true);
    }
}
