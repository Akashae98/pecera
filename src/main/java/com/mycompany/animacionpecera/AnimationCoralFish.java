
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
        double bodyWidth = 90 * size;
        double bodyHeight = 90 * size;
        double tail_displacement = 33 * size;
        double left_displacement = 4 * size;
        double top_displacement = 4 * size;

        Position topLeft = new Position(
                position.x + left_displacement,
                position.y + top_displacement
        );

        Position topRight = new Position(
                position.x + bodyWidth + tail_displacement,
                position.y + top_displacement
        );

        Position bottomRight = new Position(
                position.x + bodyWidth + tail_displacement,
                position.y + bodyHeight + tail_displacement
        );

        Position bottomLeft = new Position(
                position.x + left_displacement,
                position.y + bodyHeight + tail_displacement
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
        gc.strokeText("o", pos.x, pos.y);

    }

}
