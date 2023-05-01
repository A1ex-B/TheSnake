package Game.Scenes.Objects;

import Game.Context.ExecutionContext;
import Game.Context.Key;
import Game.Context.OutOfCanvasException;
import Game.Context.VectorInt2d;

import java.util.ArrayList;

public class Snake extends GameObject {
    private VectorInt2d direction;
    private ArrayList<VectorInt2d> bodyArray;
    private char headUp = 0x2569;
    private char headLeft = 0x2563;
    private char headDown = 0x2566;
    private char headRight = 0x2560;
    private char body = 0x25a0;
    
    public Snake(ExecutionContext executionContext, byte[][] gameField) {
        super(executionContext, gameField);
        direction = new VectorInt2d(0, -1);
        bodyArray = new ArrayList<>();
        var startX = gameField.length / 2;
        var startY = gameField[0].length / 2;
        bodyArray.add(new VectorInt2d(startX, startY));
        gameField[startX][startY] = getMarker();
    }
    
    private void drawHead() throws OutOfCanvasException {
        if (direction.y == -1) {
            this.executionContext.plotter.drawString(bodyArray.get(0), headUp + "");
        } else if (direction.x == -1) {
            this.executionContext.plotter.drawString(bodyArray.get(0), headLeft + "");
        } else if (direction.y == 1) {
            this.executionContext.plotter.drawString(bodyArray.get(0), headDown + "");
        } else {
            this.executionContext.plotter.drawString(bodyArray.get(0), headRight + "");
        }
    }
    
    private void drawNeck() throws OutOfCanvasException {
        if (bodyArray.size() > 1) {
            this.executionContext.plotter.drawString(bodyArray.get(1), body + "");
        }
    }
    
    private void eraseTail(VectorInt2d tail) throws OutOfCanvasException {
        if (tail == null) {
            return;
        }
        this.executionContext.plotter.drawString(tail, " ");
    }
    
    @Override
    public byte getMarker() {
        return 1;
    }
    
    @Override
    public VectorInt2d getPosition() {
        return bodyArray.get(0);
    }
    
    @Override
    public void draw() throws OutOfCanvasException {
        drawHead();
        for (var i = 1; i < bodyArray.size(); i++) {
            this.executionContext.plotter.drawString(bodyArray.get(i), body + "");
        }
    }
    
    private boolean canTurn(Key key) {
        boolean canTurn = false;
        if (key == Key.ArrowUp && direction.y != 1 || key == Key.ArrowLeft && direction.x != 1 ||
                key == Key.ArrowDown && direction.y != -1 || key == Key.ArrowRight && direction.x != -1) {
            canTurn = true;
        }
        return canTurn;
    }
    
    private boolean checkIfPositionInField(VectorInt2d nextPosition) {
        return nextPosition.x < 0 || nextPosition.x > gameField.length - 1 ||
                nextPosition.y < 0 || nextPosition.y > gameField[0].length - 1;
    }
    
    private byte getMarkerAhead(VectorInt2d nextHeadPosition) {
        if (checkIfPositionInField(nextHeadPosition)) {
            return -1;
        }
        return gameField[nextHeadPosition.x][nextHeadPosition.y];
    }
    
    private void getDirection(Key key) {
        switch (key) {
            case ArrowUp:
                direction = new VectorInt2d(0, -1);
                break;
            case ArrowLeft:
                direction = new VectorInt2d(-1, 0);
                break;
            case ArrowDown:
                direction = new VectorInt2d(0, 1);
                break;
            case ArrowRight:
                direction = new VectorInt2d(1, 0);
                break;
        }
    }
    
    @Override
    public byte move() throws OutOfCanvasException {
        var key = executionContext.keyListener.GetKey();
        byte marker = 0;
        if (key == Key.Esc) {
            return -1;
        } else if (canTurn(key)) {
            getDirection(key);
        }
        var headPosition = bodyArray.get(0);
        var nextHeadPosition = (VectorInt2d) headPosition.add(direction);
        marker = getMarkerAhead(nextHeadPosition);
        gameField[nextHeadPosition.x][nextHeadPosition.y] = getMarker();
        bodyArray.set(0, nextHeadPosition);
        var nextPosition = headPosition;
        VectorInt2d nextPiece = null;
        for (int i = 0; i < bodyArray.size() - 1; i++) {
            nextPiece = bodyArray.get(i);
            bodyArray.set(i, nextPosition);
            nextPosition = nextPiece;
        }
        drawHead();
        drawNeck();
        eraseTail(nextPosition);
        return marker;
    }
    
    @Override
    public void collide() {
        feed();
    }
    
    private void feed() {
        bodyArray.add(null);
    }
}
