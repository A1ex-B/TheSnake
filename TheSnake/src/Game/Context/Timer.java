package Game.Context;

public class Timer {
    private int delay;
    public Timer(int delay) {
        this.delay = delay;
    }
    
    public void waitNextTick() throws InterruptedException {
        Thread.sleep(delay);
    }
    
    public void setDelay(int newDelay) {
        this.delay = newDelay;
    }
}
