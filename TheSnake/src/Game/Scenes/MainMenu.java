package Game.Scenes;

import Game.Context.ExecutionContext;
import Game.Context.Lines.EmptyLines;
import Game.Context.Lines.SingleLines;
import Game.Context.OutOfCanvasException;
import Game.Context.VectorInt2d;

import java.util.Arrays;

public class MainMenu implements IScene {
    private class MenuItem {
        private final String title;
        private final SceneResult result;
        
        private MenuItem(String title, SceneResult result) {
            this.title = title;
            this.result = result;
        }
    }
    
    private final char selector = 0x2560;
    private final MenuItem[] items;
    private int windowX;
    private final int itemMaxLen;
    private int windowY;
    private int windowWidth;
    private int windowHeight;
    private int selectedIndex;
    
    public MainMenu() {
        this.items = new MenuItem[]{
                new MenuItem("Start", new SceneResult(Game.class.getSimpleName())),
                new MenuItem("High scores", new SceneResult(HighScores.class.getSimpleName())),
                new MenuItem("Debug", new SceneResult(DebugScene.class.getSimpleName())),
                new MenuItem("Exit", new SceneResult())
        };
        this.selectedIndex = 0;
        itemMaxLen = Arrays.stream(items).map(item -> item.title.length()).max(Integer::compare).orElse(1);
    }
    
    private void drawSelector(ExecutionContext executionContext) throws OutOfCanvasException {
        executionContext.plotter.drawString(getSelectorPosition(selectedIndex), selector + "");
    }
    
    private void clearSelector(ExecutionContext executionContext) throws OutOfCanvasException {
        executionContext.plotter.drawString(getSelectorPosition(selectedIndex), " ");
    }
    
    private void moveUp(ExecutionContext executionContext) throws OutOfCanvasException {
        clearSelector(executionContext);
        if (selectedIndex == 0) {
            selectedIndex = items.length - 1;
        } else {
            selectedIndex--;
        }
        drawSelector(executionContext);
    }
    
    private void moveDown(ExecutionContext executionContext) throws OutOfCanvasException {
        clearSelector(executionContext);
        if (selectedIndex == items.length - 1) {
            selectedIndex = 0;
        } else {
            selectedIndex++;
        }
        drawSelector(executionContext);
    }
    
    private VectorInt2d getItemPosition(int itemIndex) {
        return new VectorInt2d(windowX + 2, windowY + 1 + itemIndex);
    }
    
    private VectorInt2d getSelectorPosition(int itemIndex) {
        return new VectorInt2d(windowX + 1, windowY + 1 + itemIndex);
    }
    
    private void drawWindow(ExecutionContext executionContext) throws OutOfCanvasException {
        executionContext.plotter.drawRectangle(new VectorInt2d(windowX, windowY), windowWidth, windowHeight, new SingleLines());
        drawItems(executionContext);
        drawSelector(executionContext);
    }
    
    private void eraseWindow(ExecutionContext executionContext) throws OutOfCanvasException {
        executionContext.plotter.drawRectangle(new VectorInt2d(windowX, windowY), windowWidth, windowHeight, new EmptyLines());
        eraseItems(executionContext);
        clearSelector(executionContext);
    }
    
    private void drawItems(ExecutionContext executionContext) throws OutOfCanvasException {
        for (var itemIndex = 0; itemIndex < items.length; itemIndex++) {
            executionContext.plotter.drawString(getItemPosition(itemIndex), items[itemIndex].title);
        }
    }
    
    private void eraseItems(ExecutionContext executionContext) throws OutOfCanvasException {
        for (var itemIndex = 0; itemIndex < items.length; itemIndex++) {
            executionContext.plotter.drawRectangle(getItemPosition(itemIndex), items[itemIndex].title.length(), 1, new EmptyLines());
        }
    }
    
    public SceneResult run(ExecutionContext executionContext) throws OutOfCanvasException {
        windowWidth = itemMaxLen + 3;
        windowHeight = items.length + 2;
        windowX = (executionContext.plotter.getCanvasWidth() - windowWidth) / 2;
        windowY = (executionContext.plotter.getCanvasHeight() - windowHeight) / 2;
        drawWindow(executionContext);
        SceneResult result = null;
        while (result == null) {
            var nextKey = executionContext.keyListener.ConsumeKey();
            switch (nextKey) {
                case ArrowUp:
                case ArrowLeft:
                    moveUp(executionContext);
                    break;
                case ArrowDown:
                case ArrowRight:
                    moveDown(executionContext);
                    break;
                case Enter:
                    result = items[selectedIndex].result;
                    break;
            }
        }
        eraseWindow(executionContext);
        return result;
    }
}
