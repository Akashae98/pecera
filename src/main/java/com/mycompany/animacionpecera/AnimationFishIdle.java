/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author carol
 */
public class AnimationFishIdle extends Animation {
    
    protected boolean hasFishFin;
    private static final Random random = new Random(); //Instance of random
    
   public AnimationFishIdle(double x, double y) {
        super(x, y);
        this.hasFishFin = random.nextBoolean();
    }
 
    //Method for drawing
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        int baseFishWidth = 36;
        int baseFishHeight = 22;
        double fishWidth = baseFishWidth * size;
        double fishHeight = baseFishHeight * size;

        // Body
        gc.fillOval(x - 4 * size, y - 9 * size, fishWidth, fishHeight);

        // Tail
        double[] tailX = {
            x + fishWidth - 6 * size,
            x + fishWidth + 9 * size,
            x + fishWidth - 6 * size
        };
        double[] tailY = {
            y,
            y + 10 * size,
            y + 20 * size
        };
        gc.fillPolygon(tailX, tailY, 3);
         if (hasFishFin) {
            drawFishFin(gc, color);
        } else {
            drawScales(gc, color);
        }

        drawEye(gc);
    }
        
    protected void drawFishFin(GraphicsContext gc, Color color) {
        gc.setStroke(color.darker());
        gc.setLineWidth(2 * size);
        gc.strokeLine(x + 10 * size, y - 8 * size, x + 20 * size, y - 17 * size);
    }

    protected void drawScales(GraphicsContext gc, Color color) {
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

    protected void drawEye(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillOval(x + 4 * size, y - 2 * size, 5 * size, 5 * size);
    }
    
}