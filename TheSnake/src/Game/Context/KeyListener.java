package Game.Context;

import Game.GlobalConfiguration;

import java.io.Console;
import java.io.IOException;

public class KeyListener {
    private final Object syncObject = new Object();
    private Keys lastKey = Keys.ArrowUp;
    private boolean isAlive = true;
    private Listener listener;
    private int waitTimeout;
    
    private class Listener extends Thread {
        private Listener(String name) {
            this.setName(name);
        }
        
        public void run() {
            while (isAlive) {
                try {
                    int nextKeyCode;
                    nextKeyCode = RawConsoleInput.read(false);
                    var nextKey = Keys.GetKeyByCode(nextKeyCode);

                    synchronized (syncObject) {
                        if (nextKey != Keys.NotAvailable && lastKey != nextKey) {
                            lastKey = nextKey;
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    
    public KeyListener(int waitTimeout) {
        this.waitTimeout = waitTimeout;
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
    
    public Keys GetKey() {
        synchronized (syncObject) {
            return lastKey;
        }
    }
}
