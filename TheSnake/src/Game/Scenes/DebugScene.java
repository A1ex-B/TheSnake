package Game.Scenes;

import Game.Context.*;
import Game.GlobalConfiguration;

import java.io.IOException;

public class DebugScene implements IScene {
    public SceneResult Run(ExecutionContext executionContext) {
        System.out.println("Hello!");
        var key = Key.Default;
        var keyListener = executionContext.keyListener;
        var drawer = executionContext.drawer;
        try {
            var prevKey = Key.Default;
            int counter = 0;
            System.out.println("Started debug scene.");
            
            int x = 0, y = 0;
            int maxX = 20, maxY = 20;
            do {
                key = keyListener.ConsumeKey();
                if (true || prevKey != key) {
                    switch (key) {
                        case ArrowUp:
                            if (y > 0) {
                                y--;
                            }
                            break;
                        case ArrowLeft:
                            if (x > 0) {
                                x--;
                            }
                            break;
                        case ArrowDown:
                            if (y < maxY) {
                                y++;
                            }
                            break;
                        case ArrowRight:
                            if (x < maxX) {
                                x++;
                            }
                            break;
                    }
                    var points = new Point[]{
                            new Point(x, y, 'O'),
                            new Point(x + 1, y, '-'),
                            new Point(x + 2, y, 'X'),
                    };
                    drawer.draw(points);
//                    System.out.print("<3" + key + ", "+ key.GetCode() + ";");
                    prevKey = key;
                }
            }
            while (key != Key.Esc);
            keyListener.Stop();
            System.out.println("Bye! Press any key...");
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult("Exit", true);
    }
}
