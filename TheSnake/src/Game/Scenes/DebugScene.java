package Game.Scenes;

import Game.Context.*;
import Game.Context.Lines.EmptyLines;
import Game.Context.Lines.SingleLines;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DebugScene implements IScene {
    public SceneResult run(@NotNull ExecutionContext executionContext) {
        System.out.println("Hello!");
        var key = Key.Default;
        var keyListener = executionContext.keyListener;
        var plotter = executionContext.plotter;
        
        try {
            var prevKey = Key.Default;
            System.out.println(" Started debug scene.");
            var lines = new SingleLines();
            var emptyLines = new EmptyLines();
            plotter.drawRectangle(new VectorInt2d(1, 1), plotter.getCanvasWidth(), plotter.getCanvasHeight(), lines);
            
            var w = 4;
            var h = 3;
            int x = 2, y = 2;
            plotter.drawRectangle(new VectorInt2d(x, y), h + 3, h + 3, lines);
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
                    isMove = plotter.isFitToCanvas(new VectorInt2d(x + deltaX, y + deltaY));
                    if (isMove) {
                        x += deltaX;
                        y += deltaY;
                        plotter.drawRectangle(new VectorInt2d(prevX, prevY), w, h, emptyLines);
                        plotter.drawRectangle(new VectorInt2d(x, y), w, h, lines);
                        plotter.resetPosition();
                    }
                    
                    prevKey = key;
                }
                executionContext.timer.waitNextTick();
            }
            while (key != Key.Esc);
            plotter.drawString(new VectorInt2d(1, plotter.getCanvasHeight()), "Bye! Press any key.");
        } catch (OutOfCanvasException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new SceneResult();
    }
}
