package com.mycompany.animacionpecera;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.util.List;


public class RenderSystem extends GameSystem {
    private final GraphicsContext gc;
    private final double width;
    private final double height;


    public RenderSystem(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
        this.width = canvas.getWidth();
        this.height = canvas.getWidth();

    }

    @Override
    public void update(List<GameEntity> entities, double deltaTime) {
        LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, javafx.scene.paint.Color.rgb(127, 240, 220)),
                new Stop(1, Color.rgb(70, 130, 180))); //Lighter blue
        gc.setFill(fondo);
        gc.fillRect(0, 0, width, height);

        for (GameEntity e : entities) {
            if (!e.hasComponent(PositionComponent.class)) {
                return;
            }
            PositionComponent pos = e.getComponent(PositionComponent.class);
            // CHeck has position

            if (e.hasComponent(SpriteComponent.class)) {
                SpriteComponent sprite = e.getComponent(SpriteComponent.class);
                //Load from cache
                Image image = AssetManager.loadImage(sprite.getPath(), false);
                gc.drawImage(image, pos.position.x(), pos.position.y());
            }
        }
    }


}
