package com.mycompany.animacionpecera;

import java.util.List;

public abstract class GameSystem {
    public abstract void update(List<GameEntity> entities, double deltaTime);
}
