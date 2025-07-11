/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author User
 */
public class AnimationBubbleIdle extends Animation {

    public AnimationBubbleIdle(double size) {
        super(size);
    }

    @Override
    public BoundingBox getBoundingBox(Position position) {

        double width = size;
        double height = size;

        Position topLeft = new Position(
                position.x - width / 2,
                position.y - height / 2
        );

        Position topRight = new Position(
                position.x + width / 2,
                position.y - height / 2
        );

        Position bottomRight = new Position(
                position.x + width / 2,
                position.y + height / 2
        );

        Position bottomLeft = new Position(
                position.x - width / 2,
                position.y + height / 2
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void draw(GraphicsContext gc, Position position) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(position.x - size / 2, position.y - size / 2, size, size);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(position.x - size / 2, position.y - size / 2, size, size);//fills with color
        BoundingBox boundingBox = getBoundingBox(position);
        drawBoundingBox(gc, boundingBox, Color.MAGENTA);
        gc.strokeText(".", position.x, position.y);
    }

}
