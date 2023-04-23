package Game.Context.Lines;

public class SingleLines implements Lines {
    private final char leftUpperCorner = 0x250C;
    private final char leftBottomCorner = 0x2514;
    private final char rightUpperCorner = 0x2510;
    private final char rightBottomCorner = 0x2518;
    private final char horizontalLine = 0x2500;
    private final char verticalLine = 0x2502;
    @Override
    public char getLeftUpperCorner() {
        return leftUpperCorner;
    }
    
    @Override
    public char getLeftBottomCorner() {
        return leftBottomCorner;
    }
    
    @Override
    public char getRightUpperCorner() {
        return rightUpperCorner;
    }
    
    @Override
    public char getRightBottomCorner() {
        return rightBottomCorner;
    }
    
    @Override
    public char getHorizontalLine() {
        return horizontalLine;
    }
    
    @Override
    public char getVerticalLine() {
        return verticalLine;
    }
}
