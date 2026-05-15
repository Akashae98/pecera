/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Components.VelocityComponent;
import com.mycompany.animacionpecera.Components.Transform;
import com.mycompany.animacionpecera.Entity;
import java.util.List;

/**
 *
 * @author carol
 */
public class MovementSystem extends GameSystem {

    public MovementSystem() {
    }

    @Override
    public void update(List<Entity> entities, double deltaTime) {
        for (Entity entity : entities) {
            if (entity.hasComponent(VelocityComponent.class) && entity.hasComponent(Transform.class)) {

                Transform transform = entity.getComponent(Transform.class);
                VelocityComponent velocity = entity.getComponent(VelocityComponent.class);

                //to move:
                transform.setX(transform.getX() + velocity.velX * deltaTime);
                transform.setY(transform.getY() + velocity.velY * deltaTime);
            }
        }
    }
}
