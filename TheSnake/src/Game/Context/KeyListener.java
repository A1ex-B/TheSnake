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
                    if (nextKeyCode != -2 && nextKeyCode != lastKey.GetCode()) {
                        System.out.println("next code: " + nextKeyCode);
                    }
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
        System.out.println("Called constructor of " + KeyListener.class.getSimpleName() + ".");
        this.waitTimeout = waitTimeout;
        listener = new Listener("Listener");
        listener.start();
        System.out.println("Started thread of " + Listener.class.getSimpleName() + ". is Alive = " + isAlive);
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
