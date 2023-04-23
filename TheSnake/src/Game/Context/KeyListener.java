package Game.Context;

import java.io.IOException;

public class KeyListener {
    private final Object syncObject = new Object();
    private Key lastKeyPressed = Key.ArrowUp;
    private boolean isAlive = true;
    private Listener listener;
    
    private class Listener extends Thread {
        private Listener(String name) {
            this.setName(name);
        }
        
        public void run() {
            while (isAlive) {
                try {
                    int nextKeyCode;
                    nextKeyCode = RawConsoleInput.read(true);
                    var nextKey = Key.GetKeyByCode(nextKeyCode);

                    synchronized (syncObject) {
                        if (nextKey != Key.NotAvailable && lastKeyPressed != nextKey) {
                            lastKeyPressed = nextKey;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    protected KeyListener() {
        listener = new Listener("Listener");
        listener.start();
    }
    
    public void Stop() {
        isAlive = false;
        try {
            listener.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Key GetKey() {
        synchronized (syncObject) {
            return lastKeyPressed;
        }
    }
    
    public Key ConsumeKey() {
        synchronized (syncObject) {
            var nextKey = lastKeyPressed;
            lastKeyPressed = Key.NotAvailable;
            return nextKey;
        }
    }
}
