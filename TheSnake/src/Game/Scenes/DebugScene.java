package Game.Scenes;

import Game.Context.*;
import Game.Context.Lines.DoubleLines;
import Game.Context.Lines.EmptyLines;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DebugScene implements IScene {
    public SceneResult Run(@NotNull ExecutionContext executionContext) {
        System.out.println("Hello!");
        var key = Key.Default;
        var keyListener = executionContext.keyListener;
        var plotter = executionContext.plotter;
        try {
            System.in.read();
            var prevKey = Key.Default;
            int counter = 0;
            System.out.println(" Started debug scene.");
            var doubleLines = new DoubleLines();
            var emptyLines = new EmptyLines();
            plotter.drawRectangle(1, 1, plotter.getWidth(), plotter.getHeight(), doubleLines);
            
            var w = 4;
            var h = 3;
            int x = 2, y = 2;
            plotter.drawRectangle(x, y, h + 3, h + 3, doubleLines);
            do {
                key = keyListener.ConsumeKey();
                if (true || prevKey != key) {
                    var prevX = x;
                    var prevY = y;
                    var isMove = true;
                    switch (key) {
                        case ArrowUp:
                            if (y > 2) {
                                y--;
                            }
                            break;
                        case ArrowLeft:
                            if (x > 2) {
                                x--;
                            }
                            break;
                        case ArrowDown:
                            if (y < plotter.getHeight() - h) {
                                y++;
                            }
                            break;
                        case ArrowRight:
                            if (x < plotter.getWidth() - w) {
                                x++;
                            }
                            break;
                        default:
                            isMove = false;
                    }
                    if (isMove) {
                        plotter.drawRectangle(prevX, prevY, w, h, emptyLines);
                        plotter.drawRectangle(x, y, w, h, doubleLines);
                        plotter.resetPosition();
                    }
                    
                    prevKey = key;
                }
            }
            while (key != Key.Esc);
            plotter.drawString(1, plotter.getHeight(), "Bye! Press any key.");
            keyListener.Stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (OutOfCanvasException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult();
    }
}
