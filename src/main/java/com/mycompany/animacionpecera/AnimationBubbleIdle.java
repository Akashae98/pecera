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

        double Width = 1 * size;
        double Height = 0.5 * size;
        double extra_bottom = 0.25 * size;

        Position topLeft = new Position(
                position.x,
                position.y - (Height / 2) + extra_bottom
        );

        Position topRight = new Position(
                position.x + Width,
                position.y - (Height / 2) + extra_bottom
        );

        Position bottomRight = new Position(
                position.x + Width,
                position.y + (Height / 2) + Height + extra_bottom
        );

        Position bottomLeft = new Position(
                position.x,
                position.y + (Height / 2) + Height + extra_bottom
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void draw(GraphicsContext gc, Position position) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(position.x, position.y, this.size, size);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(position.x, position.y, size, size);//fills with color
        BoundingBox boundingBox = getBoundingBox(position);
        drawBoundingBox(gc, boundingBox, Color.WHITE);
        
        gc.strokeText("o", position.x, position.y);
    }

}
