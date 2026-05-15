/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Components.BoxCollider;
import com.mycompany.animacionpecera.Components.VelocityComponent;
import com.mycompany.animacionpecera.Components.ColliderComponent;
import com.mycompany.animacionpecera.Components.SpriteComponent;
import com.mycompany.animacionpecera.Components.Bubble;
import com.mycompany.animacionpecera.Components.CircleCollider;
import com.mycompany.animacionpecera.Entity;
import com.mycompany.animacionpecera.Components.Transform;
import java.util.List;

/**
 *
 * @author carol
 */
public class CanvasBounceSystem extends GameSystem {

    private final double canvasWidth;
    private final double canvasHeight;

    public CanvasBounceSystem(double canvasWidth, double canvasHeight) {
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }

    @Override
    public void update(List<Entity> entities, double deltaTime) {
        for (Entity entity : entities) {
            if (entity.hasComponent(BoxCollider.class) || entity.hasComponent(CircleCollider.class)) {
                Transform transform = entity.getComponent(Transform.class);
                VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
                BoxCollider boxCollider = entity.getComponent(BoxCollider.class);
                CircleCollider circleCollider = entity.getComponent(CircleCollider.class);

                // Bubbles reappear at the bottom of the canvas
                if (entity.hasComponent(Bubble.class)) {
                    double radius = circleCollider.getRadius(transform);
                    if (transform.getY() - radius < 0) {
                        transform.setY(canvasHeight + radius);
                        transform.setX(Math.random() * canvasWidth);
                    }

                } else if (entity.hasComponent(SpriteComponent.class)) {
                    handleBounce(transform, velocity, boxCollider);

                }
            }
        }
    }

    private void handleBounce(Transform transform, VelocityComponent velocity, ColliderComponent collider) {

        double leftEdge = collider.getLeft(transform);
        double rightEdge = collider.getRight(transform);
        double topEdge = collider.getTop(transform);
        double bottomEdge = collider.getBottom(transform);

        // Verify collisions on left and right
        if ((leftEdge <= 0 && velocity.velX < 0) || (rightEdge >= canvasWidth && velocity.velX > 0)) {
            velocity.velX = -velocity.velX; // Invert velocity in x
        }

        // Verify collision on top and bottom
        if ((topEdge <= 0 && velocity.velY < 0) || (bottomEdge >= canvasHeight && velocity.velY > 0)) {
            velocity.velY = -velocity.velY; // Invert velocity in y
        }

    }

}
