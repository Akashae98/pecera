/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author carol
 */
public abstract class Animation {

    protected double size;

    protected Animation(double size) {
        this.size = size;
    }

    // Abstract methods for each subclass
    public abstract void draw(GraphicsContext gc, Position position);

    public abstract BoundingBox getBoundingBox(Position position);

    public void update(GraphicsContext gc, Position position, boolean showBox) {
        draw(gc, position);
        if (showBox) {// if true draws boundingbox
            drawBoundingBox(gc, position);
        }
    }

    protected void drawBoundingBox(GraphicsContext gc, Position position) {

        BoundingBox boundingBox = getBoundingBox(position);
        gc.setStroke(Color.MAGENTA);
        gc.setLineWidth(1.0);

        gc.strokeLine(
                boundingBox.getTopLeft().x(), boundingBox.getTopLeft().y(),
                boundingBox.getTopRight().x(), boundingBox.getTopRight().y()
        );
        gc.strokeLine(
                boundingBox.getTopRight().x(), boundingBox.getTopRight().y(),
                boundingBox.getBottomRight().x(), boundingBox.getBottomRight().y()
        );
        gc.strokeLine(
                boundingBox.getBottomRight().x(), boundingBox.getBottomRight().y(),
                boundingBox.getBottomLeft().x(), boundingBox.getBottomLeft().y()
        );
        gc.strokeLine(
                boundingBox.getBottomLeft().x(), boundingBox.getBottomLeft().y(),
                boundingBox.getTopLeft().x(), boundingBox.getTopLeft().y()
        );
        gc.strokeText(".", position.x(), position.y());
    }

}
