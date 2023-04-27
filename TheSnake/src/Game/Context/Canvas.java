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
    
    private boolean isFitToCanvas(Point point) {
        return !(point.position.x < 1 || point.position.x > width || point.position.y < 1 || point.position.y > height);
    }
    
    private void checkToCanvas(Point point) throws OutOfCanvasException {
        if (!isFitToCanvas(point)) {
            throw new OutOfCanvasException("Point '" + point.character + "', code " + (int) point.character + " [" + point.position.x + ";" + point.position.x + "] is out of canvas!");
        }
    }
    
    private void checkToCanvas(Point[] points) throws OutOfCanvasException {
        for (var point : points) {
            checkToCanvas(point);
        }
    }
    
    private String getPos(VectorInt2d position) {
        return String.format("%c[%d;%df", Key.Esc.GetCode(), position.y, position.x);
    }
    
    private @NotNull String getLine(Point @NotNull [] points) throws OutOfCanvasException {
        checkToCanvas(points);
        var line = new StringBuilder();
        int prevX = -1024;
        String shift;
        
        for (var point : points) {
            checkToCanvas(point);
            if (point.position.x == prevX + 1) {
                shift = "";
            } else {
                shift = getPos(point.position);
            }
            prevX = point.position.x;
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
        System.out.print(getPos(new VectorInt2d(1, 1)));
    }
    
    protected boolean isFitToCanvas(VectorInt2d position) {
        return isFitToCanvas(new Point(position, (char) 1));
    }
}
