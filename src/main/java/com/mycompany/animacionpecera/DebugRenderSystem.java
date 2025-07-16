package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.colliders.BoxCollider;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;


public class DebugRenderSystem extends GameSystem {
    private final GraphicsContext gc;
    private final double width;
    private final double height;


    public DebugRenderSystem(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
        this.width = canvas.getWidth();
        this.height = canvas.getWidth();

    }

    @Override
    public void update(List<GameEntity> entities, double deltaTime) {
        for (GameEntity e : entities) {
            if (e.hasComponent(PositionComponent.class)) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                gc.fillText("*", pos.position.x(), pos.position.y());

                if (e.hasComponent(BoxCollider.class)) {
                    BoxCollider collider = e.getComponent(BoxCollider.class);
                    BoundingBox boundingBox = new BoundingBox(pos.position, collider.getWidth(), collider.getHeight());
                    gc.setStroke(Color.MAGENTA);
                    gc.setLineWidth(1.0);

                    gc.strokeLine(
                            boundingBox.getTopLeft().x(), boundingBox.getTopLeft().y(),
                            boundingBox.getTopRight().x(), boundingBox.getTopRight().y()
                    );
                    gc.strokeLine(
                            boundingBox.getTopRight().x(), boundingBox.getTopRight().y(),
                            boundingBox.getBottomRight().x(), boundingBox.getBottomRight().y()
                    );
                    gc.strokeLine(
                            boundingBox.getBottomRight().x(), boundingBox.getBottomRight().y(),
                            boundingBox.getBottomLeft().x(), boundingBox.getBottomLeft().y()
                    );
                    gc.strokeLine(
                            boundingBox.getBottomLeft().x(), boundingBox.getBottomLeft().y(),
                            boundingBox.getTopLeft().x(), boundingBox.getTopLeft().y()
                    );
                }
            }
        }
    }


}
