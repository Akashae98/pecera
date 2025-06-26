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

        double Width = 10 * size;
        double Height = 10 * size;

        double left = position.x;
        double right = position.x + Width;
        double top = position.y - (Height / 2);
        double bottom = position.y + (Height / 2);

        return new BoundingBox(left, top, right, bottom);
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(x, y, this.size, size);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(x, y, size, size);//fills with color
    }

}
