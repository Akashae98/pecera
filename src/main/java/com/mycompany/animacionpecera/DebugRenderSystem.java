package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.colliders.BoxCollider;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        if (!GameState.getInstance().isDebugEnabled()) {
            return;
        }
        for (GameEntity e : entities) {
            if (e.hasComponent(PositionComponent.class)) {
                PositionComponent pos = e.getComponent(PositionComponent.class);
                gc.fillText("*", pos.position.x(), pos.position.y());

                if (e.hasComponent(BoxCollider.class)) {
                    BoxCollider collider = e.getComponent(BoxCollider.class);
                    gc.save();
                    gc.setStroke(Color.MAGENTA);
                    gc.setLineWidth(1.0);
                    gc.strokeRect(pos.position.x(), pos.position.y(), collider.getWidth(),  collider.getHeight());
                    gc.restore();


                }
            }
        }
    }


}
