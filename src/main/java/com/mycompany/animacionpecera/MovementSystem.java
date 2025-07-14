package com.mycompany.animacionpecera;

import java.util.List;


public class MovementSystem extends GameSystem {
    @Override
    public void update(List<GameEntity> entities, double deltaTime) {
        for (GameEntity e : entities) {
            if (!e.hasComponent(PositionComponent.class)) {
                return;
            }
            PositionComponent pos = e.getComponent(PositionComponent.class);
            // CHeck has position

            if (e.hasComponent(VelocityComponent.class)) {
                VelocityComponent vel = e.getComponent(VelocityComponent.class);
                pos.position = pos.position.displacement(vel.direction);
            }
        }
    }


}
