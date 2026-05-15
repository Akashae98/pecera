/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Components.Bubble;
import com.mycompany.animacionpecera.Components.ColorSprite;
import com.mycompany.animacionpecera.Components.SpriteComponent;
import com.mycompany.animacionpecera.Components.Transform;
import com.mycompany.animacionpecera.Entity;
import com.mycompany.animacionpecera.ImageManager;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.*;

import java.util.List;

/**
 *
 * @author carol
 */
public class RenderSystem extends GameSystem {

    private final GraphicsContext gc;
    private final Canvas canvas;
    private final Paint background;
    private double time = 0;

    public RenderSystem(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        //the gradient background simulates water 
        this.background = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(127, 240, 220)),
                new Stop(1, Color.rgb(70, 130, 180)));

    }

    @Override
    public void update(List<Entity> entities, double deltaTime) {
        // Paint the background
        gc.setFill(background);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Paint SOLAR RAYS
        paintSolarRays(deltaTime);

        //paint entities
        for (Entity entity : entities) {
            Transform transform = entity.getComponent(Transform.class);
            SpriteComponent sprite = entity.getComponent(SpriteComponent.class);

            Image image = ImageManager.getInstance().getImage(sprite.imageKey);
            double height = image.getHeight();
            double width = image.getWidth();
            //to manually draw the oval, we need to apply the scale without affine
            double widthDraw = image.getWidth() * transform.getScaleX();

            //a white circle to highlight the bubbles…?
            if (entity.hasComponent(Bubble.class)) {
                gc.setFill(Color.rgb(255, 255, 255, 0.3));
                gc.fillOval(transform.getX() - widthDraw / 2,
                        transform.getY() - widthDraw / 2, widthDraw, widthDraw);
                gc.setStroke(Color.rgb(255, 255, 255, 0.5));
                gc.strokeOval(transform.getX() - widthDraw / 2,
                        transform.getY() - widthDraw / 2, widthDraw, widthDraw);
            }
            if (entity.hasComponent(Transform.class) && entity.hasComponent(SpriteComponent.class)) {
                //save gc state
                gc.save();
                gc.setEffect(null);
                // Apply color effect for ColorSprite
                if (entity.hasComponent(ColorSprite.class)) {
                    ColorSprite colorSprite = entity.getComponent(ColorSprite.class);
                    gc.setEffect(colorSprite.getColorEffect());
                }

                // applies to translate and rotation
                gc.setTransform(getAffineTransform(transform));

                if (sprite.flip) {
                    gc.scale(-1, 1);
                }

                gc.drawImage(image, -width / 2, -height / 2, width, height);

                gc.restore();
            }

        }

    }

    public void cleanCanvas() {
        //clearRect cleans the canvas using transparency and let us draw again
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public javafx.scene.transform.Affine getAffineTransform(Transform transform) {
        javafx.scene.transform.Affine affine = new javafx.scene.transform.Affine();

        //Important order: translate -> rotate -> scale
        affine.appendTranslation(transform.getX(), transform.getY());

        if (transform.getRotation() != 0) {
            affine.appendRotation(Math.toDegrees(transform.getRotation()));
        }

        if (transform.getScaleX() != 1 || transform.getScaleY() != 1) {
            affine.appendScale(transform.getScaleX(), transform.getScaleY());
        }

        return affine;
    }

    public void paintSolarRays(double deltaTime) {
        time += deltaTime; // Increase accumulated time
        gc.save();
        gc.setGlobalAlpha(0.15); //semi-transparency

        int numRays = 13;
        for (int i = 0; i < numRays; i++) {
            // Base position 
            double startX = canvas.getWidth() - (i * 120);

            // Length (varies per ray)
            double rayLength = canvas.getHeight() * (0.5 + 0.5 * (i / (double) numRays));

            // Smooth left drift at the bottom 
            double drift = -100 - i * 20 + Math.sin(time * 0.5 + i) * 30;

            // Base width (straight rays at the top, wider at bottom)
            double baseWidth = 20 + (i * 10);

            // Add shimmer to width (slow breathing effect)
            double shimmer = Math.sin(time * 0.7 + i) * 15;

            double topWidth = baseWidth * 0.4;
            double bottomWidth = baseWidth + shimmer; // slowly changing

            // Polygon points 
            double[] xPoints = {
                startX, // top-left
                startX + topWidth, // top-right
                startX + drift + bottomWidth, // bottom-right
                startX + drift // bottom-left
            };
            double[] yPoints = {
                0, // top
                0, // top
                rayLength, // bottom
                rayLength // bottom
            };

            // Vertical gradient for the ray
            LinearGradient rayGradient = new LinearGradient(
                    0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                    new Stop(0.0, Color.rgb(255, 255, 255, 0.35)), // bright at top
                    new Stop(1.0, Color.TRANSPARENT) // transparency
            );

            // Draw the ray as a rectangle filled with gradient
            gc.setFill(rayGradient);
            gc.fillPolygon(xPoints, yPoints, 4);
        }

        gc.restore();
    }

}
