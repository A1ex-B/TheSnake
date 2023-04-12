package Game.Context;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Must be single instance.
 */
public class Drawer {
    private String getPos(int x, int y) {
        return String.format("%c[%d;%df", Keys.Esc.GetCode(), y, x);
    }
    
    private String getLine(Point[] points) {
        var line = new StringBuilder();
        int prevX = -1024;
        String shift = null;
        for (var point: points) {
            if (point.x == prevX + 1) {
                shift = "";
            } else {
                shift = getPos(point.x, point.y);
            }
            prevX = point.x;
            line.append(shift + point.character);
        }
        return line.toString();
    }
    
    public synchronized void draw(Point[] points) {
        int prevX = -1024;
        System.out.print(getLine(points));
    }
}
