package Game.Context.Lines;

public class DoubleLines implements Lines {
    private final char leftUpperCorner = 0x2554;
    private final char leftBottomCorner = 0x255a;
    private final char rightUpperCorner = 0x2557;
    private final char rightBottomCorner = 0x255d;
    private final char horizontalLine = 0x2550;
    private final char verticalLine = 0x2551;
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
/*

* */