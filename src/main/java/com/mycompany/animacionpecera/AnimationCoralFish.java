/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
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

    @Override
    public BoundingBox getBoundingBox(Position position) {

        double width = image.getWidth() * size;
        double height = image.getHeight() * size;
        Position center = position.displacement(width / 2, height / 2);

        Position topLeft = new Position(
                center.x - width / 2,
                center.y - height / 2
        );

        Position topRight = new Position(
                center.x + width / 2,
                center.y - height / 2
        );

        Position bottomRight = new Position(
                center.x + width / 2,
                center.y + height / 2
        );

        Position bottomLeft = new Position(
                center.x - width / 2,
                center.y + height / 2
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void draw(GraphicsContext gc, Position pos) {
        double width = image.getWidth() * size;
        double height = image.getHeight() * size;

        gc.drawImage(image, pos.x, pos.y, width, height);
        BoundingBox boundingBox = getBoundingBox(pos);
        drawBoundingBox(gc, boundingBox, Color.WHITE);

        Position center = pos.displacement(width / 2, height / 2);
        gc.strokeText("o", center.x, center.y);

    }

}
