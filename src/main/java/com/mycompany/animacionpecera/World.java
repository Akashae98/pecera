package com.mycompany.animacionpecera;

import java.util.ArrayList;
import java.util.List;

public class World {
    // 1. Lista de entidades (o un administrador de entidades)
    private final List<GameEntity> gameEntities = new ArrayList<>(); // List of bubbles
    private final List<GameSystem> gameSystems = new ArrayList<>();

    // Métodos principales
    public void addEntity(GameEntity entity) { gameEntities.add(entity); }
    public void addSystem(GameSystem system) { gameSystems.add(system); }
    public void update(double deltaTime) {
        for (GameSystem system : gameSystems) {
            system.update(gameEntities, deltaTime);
        }
    }
}
