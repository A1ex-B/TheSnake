package Game.Context;

import jdk.jshell.spi.ExecutionControl;

/**
 * Must be single instance.
 */
public class Canvas {
    private final char leftUpperCorner = ' ';
    private final char leftBottomCorner = ' ';
    private final char rightUpperCorner = ' ';
    private final char rightBottomCorner = ' ';
    private int width;
    private int height;
    
    private void checkToCanvas(Point point) throws OutOfCanvasException {
        if (point.x < 0 || point.x > width - 1 || point.y < 0 || point.y > height - 1) {
            throw new OutOfCanvasException("Point '" + point.character + "', code " + (int)point.character + " [" + point.x + ";" + point.x + "] is out of canvas!");
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
    
    private String getLine(Point[] points) throws OutOfCanvasException {
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
    
    public void draw(Point[] points) throws OutOfCanvasException {
        System.out.print(getLine(points));
    }
    
    public Point[] prepareRectangle(int x, int y, int width, int height) {
        return null; // TODO: Complete this...
    }
}
