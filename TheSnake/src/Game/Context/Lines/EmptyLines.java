package Game.Context.Lines;

public class EmptyLines implements Lines{
    
    @Override
    public char getLeftUpperCorner() {
        return ' ';
    }
    
    @Override
    public char getLeftBottomCorner() {
        return ' ';
    }
    
    @Override
    public char getRightUpperCorner() {
        return ' ';
    }
    
    @Override
    public char getRightBottomCorner() {
        return ' ';
    }
    
    @Override
    public char getHorizontalLine() {
        return ' ';
    }
    
    @Override
    public char getVerticalLine() {
        return ' ';
    }
}
