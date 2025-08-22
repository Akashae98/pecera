/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class AnimationCoralFish extends Animation {

    private Image image;

    public AnimationCoralFish(double size) {
        super(size);
        this.image = new Image(getClass().getResourceAsStream("/Images/sketchPezCoral.png"));

    }

    private double getWidth() {
        return image.getWidth() * size;
    }

    private double getHeight() {
        return image.getHeight() * size;
    }

    @Override
    public BoundingBox getBoundingBox(Position position) {

        Position topLeft = new Position(
                position.x() - getWidth() / 2,
                position.y() - getHeight() / 2
        );

        Position topRight = new Position(
                position.x() + getWidth() / 2,
                position.y() - getHeight() / 2
        );

        Position bottomRight = new Position(
                position.x() + getWidth() / 2,
                position.y() + getHeight() / 2
        );

        Position bottomLeft = new Position(
                position.x() - getWidth() / 2,
                position.y() + getHeight() / 2
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void draw(GraphicsContext gc, Position pos) {
        gc.save();
        DropShadow glowEffect = new DropShadow();
        glowEffect.setColor(Color.CORAL.deriveColor(0, 1, 1, 0.3));  // Color del brillo
        glowEffect.setRadius(5);  // Tamaño del resplandor
        glowEffect.setSpread(0.7);  // Intensidad
        gc.setEffect(glowEffect);
        gc.drawImage(image, pos.x() - getWidth() / 2, pos.y() - getHeight() / 2,
                getWidth(), getHeight());
        gc.restore();

    }

}
