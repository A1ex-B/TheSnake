package Game.Context;

import jdk.jshell.spi.ExecutionControl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Must be single instance.
 */
public class Canvas {
    
    private int width;
    private int height;
    
    private void checkToCanvas(Point point) throws OutOfCanvasException {
        if (point.x < 1 || point.x > width || point.y < 1 || point.y > height) {
            throw new OutOfCanvasException("Point '" + point.character + "', code " + (int) point.character + " [" + point.x + ";" + point.x + "] is out of canvas!");
        }
    }
    
    private void checkToCanvas(Point[] points) throws OutOfCanvasException {
        for (var point : points) {
            checkToCanvas(point);
        }
    }
    
    private String getPos(int x, int y) {
        return String.format("%c[%d;%df", Key.Esc.GetCode(), y, x);
    }
    
    private @NotNull String getLine(Point @NotNull [] points) throws OutOfCanvasException {
        checkToCanvas(points);
        var line = new StringBuilder();
        int prevX = -1024;
        String shift;
        
        for (var point : points) {
            checkToCanvas(point);
            if (point.x == prevX + 1) {
                shift = "";
            } else {
                shift = getPos(point.x, point.y);
            }
            prevX = point.x;
            line.append(shift).append(point.character);
        }
        
        return line.toString();
    }
    
    protected Canvas(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    protected int getWidth() {
        return this.width;
    }
    
    protected int getHeight() {
        return this.height;
    }
    
    protected void draw(Point[] points) throws OutOfCanvasException {
        System.out.print(getLine(points));
    }
    
    protected void resetPosition() {
        System.out.print(getPos(1, 1));
    }
}
