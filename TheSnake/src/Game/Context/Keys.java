package Game.Context;

import java.util.HashMap;

public enum Keys {
    Esc(27),
    ArrowUp(57416),
    ArrowLeft(57419),
    ArrowDown(57424),
    ArrowRight(57421),
    Enter(13),
    Default(-1),
    NotAvailable(-2);
    
    private int code;
    private static HashMap<Integer, Keys> allKeys = null;
    
    private static void SaveAllKeys() {
        allKeys = new HashMap<Integer, Keys>();
        for (var val : Keys.values()) {
            allKeys.put(val.GetCode(), val);
        }
    }
    
    Keys(int code) {
        this.code = code;
    }
    
    public int GetCode() {
        return code;
    }
    
    public static Keys GetKeyByCode(int code) {
        if (allKeys == null) {
            SaveAllKeys();
        }
        if (allKeys.containsKey(code)) {
            return allKeys.get(code);
        }
        return Keys.Default;
    }
}
