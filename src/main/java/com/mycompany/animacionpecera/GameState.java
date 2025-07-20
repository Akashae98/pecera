package com.mycompany.animacionpecera;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameState {
    private boolean debugActivo = false;
    private IntegerProperty fps = new SimpleIntegerProperty(0);

    // Singleton para acceso global (opcional)
    private static final GameState instance = new GameState();
    public static GameState getInstance() { return instance; }

    // Getter/Setter
    public boolean isDebugEnabled() { return debugActivo; }
    public void setDebug(boolean debugActivo) { this.debugActivo = debugActivo; }

    public IntegerProperty getFps() {
        return fps;
    }

    public void setFps(int value) {
        fps.setValue(value);
    }
}