/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Components.SpriteComponent;
import com.mycompany.animacionpecera.Components.Transform;
import com.mycompany.animacionpecera.Components.VelocityComponent;
import com.mycompany.animacionpecera.Entity;
import java.util.List;

/**
 This system manage rotation and flip of the image
 */
public class VelocitySystem extends GameSystem {


    @Override
    public void update(List<Entity> entities, double deltaTime) {
        for (Entity entity : entities) {
            if (entity.hasComponent(SpriteComponent.class) && entity.hasComponent(VelocityComponent.class)) {
                Transform transform = entity.getComponent(Transform.class);
                VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
                SpriteComponent sprite = entity.getComponent(SpriteComponent.class);

                // horizontal flip
                sprite.flip = velocity.velX > 0;

                // sets rotation to the direction of the object
                if ((velocity.velY > 0 && velocity.velX > 0) || (velocity.velY < 0 && velocity.velX < 0)) {
                    transform.setRotation(Math.toRadians(10));
                } else if ((velocity.velY < 0 && velocity.velX > 0) || (velocity.velY > 0 && velocity.velX < 0)) {
                    transform.setRotation(Math.toRadians(-10));
                } else {
                    transform.setRotation(0);
                }
            }
        }

    }
}
