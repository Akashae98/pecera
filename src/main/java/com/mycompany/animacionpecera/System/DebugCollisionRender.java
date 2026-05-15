/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Components.BoxCollider;
import com.mycompany.animacionpecera.Components.CircleCollider;
import com.mycompany.animacionpecera.Components.Transform;
import com.mycompany.animacionpecera.Entity;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class DebugCollisionRender extends GameSystem {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private boolean showBox = false;

    public DebugCollisionRender(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public void setShowBox(boolean show) {
        this.showBox = show;
    }

    @Override
    public void update(List<Entity> entities, double deltaTime) {
        if (!showBox) {
            return;
        }
        for (Entity entity : entities) {
            if ((entity.hasComponent(BoxCollider.class) || entity.hasComponent(CircleCollider.class))
                    && entity.hasComponent(Transform.class)) {
                BoxCollider boxCollider = entity.getComponent(BoxCollider.class);
                CircleCollider circleCollider = entity.getComponent(CircleCollider.class);
                Transform transform = entity.getComponent(Transform.class);

                gc.save();
                gc.setTransform(getAffineTransform(transform));
                gc.setStroke(Color.MAGENTA);

                if (boxCollider != null) {
                    double halfWidth = boxCollider.getWidth(transform) / 2.0;
                    double halfHeight = boxCollider.getHeight(transform) / 2.0;
  
                    gc.setLineWidth(1.0);
                    gc.strokeRect(-halfWidth, -halfHeight, boxCollider.getWidth(transform),
                    boxCollider.getHeight(transform));

                } else if (circleCollider != null) {
                    double radius = circleCollider.getRadius(transform);
                    gc.setLineWidth(1.0);
                    gc.strokeOval(-radius, -radius, radius * 2, radius * 2);
                }
                
                gc.strokeText(".", 0, 0);
                gc.restore();
            }
        }
    }

    public javafx.scene.transform.Affine getAffineTransform(Transform transform) {
        javafx.scene.transform.Affine affine = new javafx.scene.transform.Affine();

        //Important order: translate -> rotate -> scale
        affine.appendTranslation(transform.getX(), transform.getY());

        if (transform.getRotation() != 0) {
            affine.appendRotation(Math.toDegrees(transform.getRotation()));
        }

        return affine;
    }

}
