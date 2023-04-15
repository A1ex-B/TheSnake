package Game.Context;

import jdk.jshell.spi.ExecutionControl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Must be single instance.
 */
public class Canvas {
    private final char leftUpperCorner = 0x2554;
    private final char leftBottomCorner = 0x255a;
    private final char rightUpperCorner = 0x2557;
    private final char rightBottomCorner = 0x255d;
    private final char horizontalLine = 0x2550;
    private final char verticalLine = 0x2551;
    private int width;
    private int height;
    
    private void checkToCanvas(Point point) throws OutOfCanvasException {
        if (point.x < 0 || point.x > width - 1 || point.y < 0 || point.y > height - 1) {
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
    
    private String getLine(Point @NotNull [] points) throws OutOfCanvasException {
        var line = new StringBuilder();
        int prevX = -1024;
        String shift;
        
        for (var point : points) {
            checkToCanvas(point);
            if (point.x == prevX + 1 && false) {
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
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void draw(Point[] points) throws OutOfCanvasException {
        System.out.print(getLine(points));
    }
    
    public Point[] prepareRectangle(int topLeftX, int topLeftY, int width, int height) {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and height cannot be less than 1. Provided:"
                    + " width: " + width + ", height: " + height + ".");
        }
        if (width == 1 && height == 1) {
            return new Point[]{
                    new Point(topLeftX, topLeftY, leftUpperCorner)
            };
        }
        Point[] rectanglePoints;
        if (height == 1) {
            rectanglePoints = new Point[width];
            for (int xCount = 0; xCount < width; xCount++) {
                rectanglePoints[xCount] = new Point(topLeftX + xCount, topLeftY, horizontalLine);
            }
        } else if (width == 1) {
            rectanglePoints = new Point[width];
            for (int yCount = 0; yCount < height; yCount++) {
                rectanglePoints[yCount] = new Point(topLeftX, topLeftY + yCount, verticalLine);
            }
        } else {
            rectanglePoints = new Point[(width + height) * 2 - 4];
            int rectangleIndex = 0;
            
            rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY, leftUpperCorner);
            for (var index = 1; index < width - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + index, topLeftY, horizontalLine);
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY, rightUpperCorner);
            for (var index = 1; index < height - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY + index, verticalLine);
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY + height - 1, rightBottomCorner);
            for (var index = 1; index < width - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - index - 1, topLeftY + height - 1, horizontalLine);
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY + height - 1, leftBottomCorner);
            for (var index = 1; index < height - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY + height - index - 1, verticalLine);
            }
        }
        
        return rectanglePoints;
    }
}
