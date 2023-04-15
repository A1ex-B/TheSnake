package Game.Context;

import Game.Context.Lines.Lines;
import org.jetbrains.annotations.NotNull;

public class Plotter {
    private Canvas canvas;
    
    public Plotter(int canvasWidth, int canvasHeight) {
        this.canvas = new Canvas(canvasWidth, canvasHeight);
    }
    
    private Point @NotNull [] prepareRectangle(int topLeftX, int topLeftY, int width, int height, Lines lines) throws OutOfCanvasException {
        if (width < 1 || height < 1) {
            throw new IllegalArgumentException("Width and height cannot be less than 1. Provided:"
                    + " width: " + width + ", height: " + height + ".");
        }
        if (width == 1 && height == 1) {
            return new Point[]{
                    new Point(topLeftX, topLeftY, lines.getLeftUpperCorner())
            };
        }
        Point[] rectanglePoints;
        if (height == 1) {
            rectanglePoints = new Point[width];
            for (int xCount = 0; xCount < width; xCount++) {
                rectanglePoints[xCount] = new Point(topLeftX + xCount, topLeftY, lines.getHorizontalLine());
            }
        } else if (width == 1) {
            rectanglePoints = new Point[height];
            for (int yCount = 0; yCount < height; yCount++) {
                rectanglePoints[yCount] = new Point(topLeftX, topLeftY + yCount, lines.getVerticalLine());
            }
        } else {
            rectanglePoints = new Point[(width + height) * 2 - 4];
            int rectangleIndex = 0;
            
            rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY, lines.getLeftUpperCorner());
            for (var index = 1; index < width - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + index, topLeftY, lines.getHorizontalLine());
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY, lines.getRightUpperCorner());
            for (var index = 1; index < height - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY + index, lines.getVerticalLine());
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - 1, topLeftY + height - 1, lines.getRightBottomCorner());
            for (var index = 1; index < width - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX + width - index - 1, topLeftY + height - 1, lines.getHorizontalLine());
            }
            rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY + height - 1, lines.getLeftBottomCorner());
            for (var index = 1; index < height - 1; index++) {
                rectanglePoints[rectangleIndex++] = new Point(topLeftX, topLeftY + height - index - 1, lines.getVerticalLine());
            }
        }
        
        return rectanglePoints;
    }
    
    private Point[] prepareString(int x, int y, String string) {
        var points = new Point[string.length()];
        for (var index = 0; index < string.length(); index++) {
            points[index] = new Point(x + index, y, string.charAt(index));
        }
        return points;
    }
    
    public int getWidth() {
        return this.canvas.getWidth();
    }
    
    public int getHeight() {
        return this.canvas.getHeight();
    }
    
    public void drawRectangle(int x, int y, int width, int height, Lines lines) throws OutOfCanvasException {
        var rectangle = prepareRectangle(x, y, width, height, lines);
        canvas.draw(rectangle);
    }
    
    public void drawString(int x, int y, String string) throws OutOfCanvasException {
        var points = prepareString(x, y, string);
        canvas.draw(points);
    }
    
    public void resetPosition() {
        canvas.resetPosition();
    }
}
