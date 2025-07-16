package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.colliders.BoxCollider;
import com.mycompany.animacionpecera.colliders.Collider;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;


public class BorderCollisionSystem extends GameSystem {
    private final double width;
    private final double height;


    public BorderCollisionSystem(double width, double height) {

        this.width = width;
        this.height = height;
    }

    @Override
    public void update(List<GameEntity> entities, double deltaTime) {
        BoundingBox worldBox = new BoundingBox(new Position(0,0), width, height);
        for (GameEntity entity : entities) {
            if (!entity.hasComponent(PositionComponent.class)){
                continue;
            }
            if (!entity.hasComponent(BoxCollider.class)){
                continue;
            }
            PositionComponent position = entity.getComponent(PositionComponent.class);
            BoxCollider boxCollider = entity.getComponent(BoxCollider.class);

            BoundingBox boundingBox = new BoundingBox(position.position, boxCollider.getWidth(), boxCollider.getHeight());

            boolean isNotInside = false;
            for (Position point : boundingBox.getPoints() ){
                if (!worldBox.isInside(point)){
                    isNotInside = true;
                    break;
                }
            }

            if (isNotInside){
                if (entity.hasComponent(Fish.class) && entity.hasComponent(VelocityComponent.class)){
                    VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
                    if ((boundingBox.getTopLeft().x() < worldBox.getTopLeft().x() && velocity.direction.dx() < 0)
                            || (boundingBox.getBottomRight().x() > worldBox.getBottomRight().x() && velocity.direction.dx() > 0)) {
                        velocity.direction = velocity.direction.invertX();
                    }

                    if ((boundingBox.getTopLeft().y() < worldBox.getTopLeft().y() && velocity.direction.dy() < 0)
                            || (boundingBox.getBottomRight().y() > worldBox.getBottomRight().y() && velocity.direction.dy() > 0)) {
                        velocity.direction = velocity.direction.invertY();

                    }
                }
            }

        }

    }


}
