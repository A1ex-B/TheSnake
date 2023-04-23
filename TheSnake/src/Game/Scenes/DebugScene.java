package Game.Scenes;

import Game.Context.*;
import Game.Context.Lines.EmptyLines;
import Game.Context.Lines.SingleLines;
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
            for (var i = 0; i < 5; i++) {
                System.out.print("i: " + i + "\r");
                executionContext.timer.waitNextTick();
            }
            var prevKey = Key.Default;
            int counter = 0;
            System.out.println(" Started debug scene. Press any key when ready. ");
            var lines = new SingleLines();
            var emptyLines = new EmptyLines();
            plotter.drawRectangle(1, 1, plotter.getCanvasWidth(), plotter.getCanvasHeight(), lines);
            
            var w = 4;
            var h = 3;
            int x = 2, y = 2;
            plotter.drawRectangle(x, y, h + 3, h + 3, lines);
            do {
                key = keyListener.GetKey();
                if (true || prevKey != key) {
                    var prevX = x;
                    var prevY = y;
                    var deltaX = 0;
                    var deltaY = 0;
                    var isMove = true;
                    switch (key) {
                        case ArrowUp:
                            if (y > 2) {
                                deltaX = 0;
                                deltaY = -1;
                            }
                            break;
                        case ArrowLeft:
                            if (x > 2) {
                                deltaX = -1;
                                deltaY = 0;
                            }
                            break;
                        case ArrowDown:
                            if (y < plotter.getCanvasHeight() - h) {
                                deltaX = 0;
                                deltaY = 1;
                            }
                            break;
                        case ArrowRight:
                            if (x < plotter.getCanvasWidth() - w) {
                                deltaX = 1;
                                deltaY = 0;
                            }
                            break;
                        default:
                            isMove = false;
                    }
                    isMove = plotter.isFitToCanvas(x + deltaX, y + deltaY);
                    if (isMove) {
                        x += deltaX;
                        y += deltaY;
                        plotter.drawRectangle(prevX, prevY, w, h, emptyLines);
                        plotter.drawRectangle(x, y, w, h, lines);
                        plotter.resetPosition();
                    }
                    
                    prevKey = key;
                }
                executionContext.timer.waitNextTick();
            }
            while (key != Key.Esc);
            plotter.drawString(1, plotter.getCanvasHeight(), "Bye! Press any key.");
            keyListener.Stop();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (OutOfCanvasException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult();
    }
}
