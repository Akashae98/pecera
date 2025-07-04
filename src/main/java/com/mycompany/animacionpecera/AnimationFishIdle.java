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
public class AnimationFishIdle extends Animation {

    private final boolean hasFishFin;
    private Color color;

    public AnimationFishIdle(double size, boolean hasFishFin, Color color) {
        super(size);
        this.hasFishFin = hasFishFin;
        this.color = color;
    }

    /*Note: the fish's actual position refers to a center-left point in the object.
    We need to calculate the boundingbox starting from that position.
     */
    @Override
    public BoundingBox getBoundingBox(Position position) {
        //the base of the bodyfish 
        double bodyWidth = 36 * size;
        //the height of the bodyfish
        double bodyHeight = 20 * size;
        //the height of the fishtail
        double tail_displacement = 10 * size;
        //displacement to left
        double left_displacement = 5 * size;

        Position topLeft = new Position(
                position.x - (left_displacement),
                position.y - (bodyHeight / 2)
        );

        Position topRight = new Position(
                position.x + bodyWidth + tail_displacement,
                position.y - (bodyHeight / 2)
        );

        Position bottomRight = new Position(
                position.x + bodyWidth + tail_displacement,
                position.y + (bodyHeight / 2) + tail_displacement
        );

        Position bottomLeft = new Position(
                position.x - (left_displacement),
                position.y + (bodyHeight / 2) + tail_displacement
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);

    }

    //Method for drawing
    @Override
    public void draw(GraphicsContext gc, Position position) {
        gc.setFill(color);
        int baseFishWidth = 36;
        int baseFishHeight = 22;
        double fishWidth = baseFishWidth * size;
        double fishHeight = baseFishHeight * size;

        // Body
        gc.fillOval(position.x - 4 * size, position.y - 9 * size, fishWidth, fishHeight);

        // Tail
        double[] tailX = {
            position.x + fishWidth - 6 * size,
            position.x + fishWidth + 9 * size,
            position.x + fishWidth - 6 * size
        };
        double[] tailY = {
            position.y,
            position.y + 10 * size,
            position.y + 20 * size
        };
        gc.fillPolygon(tailX, tailY, 3);
        if (hasFishFin) {
            drawFishFin(gc, position.x, position.y, color);
        } else {
            drawScales(gc, position.x, position.y, color);
        }

        drawEye(gc, position.x, position.y);
        BoundingBox boundingBox = getBoundingBox(position);
        drawBoundingBox(gc, boundingBox, this.color);

    }

    protected void drawFishFin(GraphicsContext gc, double x, double y, Color color) {
        gc.setStroke(color.darker());
        gc.setLineWidth(2 * size);
        gc.strokeLine(x + 10 * size, y - 8 * size, x + 20 * size, y - 17 * size);
    }

    protected void drawScales(GraphicsContext gc, double x, double y, Color color) {
        Color bright = color.brighter();
        Color brighterTransparent = new Color(bright.getRed(), bright.getGreen(), bright.getBlue(), 0.8);
        gc.setFill(brighterTransparent);

        double scaleWidth = 6 * size * 0.4;
        double scaleHeight = 3 * size * 0.5;
        int scalesPerRow = 6;
        int rowCount = 3;

        double startX = x + 2 * size;
        double startY = y - 3 * size;
        double spacing = 5 * size;
        double rowSpacing = 4 * size;

        for (int row = 0; row < rowCount; row++) {
            for (int i = 0; i < scalesPerRow; i++) {
                double top = startX + (i * spacing);
                double left = startY + (row * rowSpacing);
                gc.fillOval(top, left, scaleWidth, scaleHeight);
            }
        }
    }

    protected void drawEye(GraphicsContext gc, double x, double y) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x + 4 * size, y - 2 * size, 5 * size, 5 * size);
    }

}
