package Game.Scenes;

import Game.Context.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DebugScene implements IScene {
    public SceneResult Run(@NotNull ExecutionContext executionContext) {
        System.out.println("Hello!");
        var key = Key.Default;
        var keyListener = executionContext.keyListener;
        var canvas = executionContext.canvas;
        try {
            System.in.read();
            var prevKey = Key.Default;
            int counter = 0;
            System.out.println(" Started debug scene.");
            canvas.draw(canvas.prepareRectangle(1, 1, canvas.getWidth() - 1, canvas.getHeight() - 1));
            System.in.read();
            if (true) {
                return new SceneResult();
            }
            var w = 2;
            var h = 2;
            int x = 1, y = 1;
            do {
                key = keyListener.ConsumeKey();
                if (true || prevKey != key) {
                    switch (key) {
                        case ArrowUp:
                            if (y > 1) {
                                y--;
                            }
                            break;
                        case ArrowLeft:
                            if (x > 1) {
                                x--;
                            }
                            break;
                        case ArrowDown:
                            if (y < canvas.getHeight() - h) {
                                y++;
                            }
                            break;
                        case ArrowRight:
                            if (x < canvas.getWidth() - w) {
                                x++;
                            }
                            break;
                    }
                    
                    canvas.draw(canvas.prepareRectangle(x, y, w, h));
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
        } catch (OutOfCanvasException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult();
    }
}
