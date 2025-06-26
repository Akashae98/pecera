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

        double baseWidth = size;
        double baseHeight = size;
        double heightFishTail = 0;

        return new BoundingBox(position, baseWidth, baseHeight, heightFishTail, size);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(x, y, this.size, size);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(x, y, size, size);//fills with color
    }

}
