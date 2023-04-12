package Game.Context;

/**
 * Must be single instance.
 */
public class Drawer {
    private String getPos(int x, int y) {
        return String.format("%c[%d;%df", Key.Esc.GetCode(), y, x);
    }
    
    private String getLine(Point[] points) {
        var line = new StringBuilder();
        int prevX = -1024;
        String shift;
        
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
    
    protected Drawer()
    {
    
    }
    
    public synchronized void draw(Point[] points) {
        System.out.print(getLine(points));
    }
}
