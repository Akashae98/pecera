/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class let us create fishes with position, velocity and random colors,
 * etc. Fishes can be created with a fishfin or with scales depending on whether
 * the boolean hasFishFin its true or false.
 *
 */
public class Fish {

    private double x, y;      // Position 
    private double dx, dy;    //Direction for movement
    private final Color color;
    private static final Random random = new Random(); //Instance of random
    private final boolean hasFishFin; //defines if it has a fish fin.
    private final double size;
    private Movement m;

    public Fish(double x, double y) {
        this.x = x;
        this.y = y;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.color = colorGenerator(); //Assigns a color
        this.hasFishFin = random.nextBoolean(); //decides by random true or false 
        this.size = 0.5 + random.nextDouble();
              this.m = new Movement();

    }
    // Method for generating blue, pink, purple or default: coral

    private Color colorGenerator() {
        int selector = random.nextInt(3);//selects between 0, 1 o 2

        switch (selector) {
            case 0: //Blue colors
                return Color.rgb( //portions of the colors:
                        50 + random.nextInt(100), // red: 50–149
                        150 + random.nextInt(70), // green: 150–219
                        180 + random.nextInt(75) // blue: 180–254
                );
            case 1: //Pink colors
                return Color.rgb(
                        200 + random.nextInt(55), // red: 200–254
                        100 + random.nextInt(80), // green: 100–179
                        140 + random.nextInt(60) // blue: 140–199
                );
            case 2: //Purple colors
                return Color.rgb(
                        150 + random.nextInt(50), // red: 150–199
                        120 + random.nextInt(60), // green: 120–179
                        180 + random.nextInt(40) // blue: 180–219
                );
            default:
                return Color.CORAL;
        }
    }

    //Method of movement
    public void move(int width, int height) {
        x += dx; // horizontal movement
        y += dy; // vertical move
        double[] newDirect = m.rebound(x, y, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];
    }

    //Method for drawing
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        int baseFishWidth = 36;
        int baseFishHeight = 22;
        double fishWidth = baseFishWidth * size;
        double fishHeight = baseFishHeight * size;

        // Body
        gc.fillOval(x - 4 * size, y - 9 * size, fishWidth, fishHeight);

        // Tail
        double[] colaX = {
            x + fishWidth - 6 * size,
            x + fishWidth + 9 * size,
            x + fishWidth - 6 * size
        };
        double[] colaY = {
            y,
            y + 10 * size,
            y + 20 * size
        };
        gc.fillPolygon(colaX, colaY, 3);

        //Fishtail or scales
        if (hasFishFin) {
            //for true, we draw dorsal fin..
            gc.setStroke(color.darker());//darker color of the body
            gc.setLineWidth(2 * size); //Line with a width
            gc.strokeLine(x + 10 * size, y - 8 * size, x + 20 * size, y - 17 * size);
            //position of the line
        } else {
            // if not we draw little scales
            Color bright = color.brighter();
            Color brighterTransparent = new Color(//color for the scales
                    bright.getRed(),
                    bright.getGreen(),
                    bright.getBlue(),
                    0.8 // Opacity (0.0 = totally translucid)
            );
            gc.setFill(brighterTransparent);

            double scaleWidth = 6 * size * 0.4;   //ajusted width and height
            double scaleHeight = 3 * size * 0.5;  
            int scalesPerRow = 6;    //6 scales in the body per row
            int rowCount = 3;        //number of rows in the body

            /*inittially position where starts the drawing of scales in the fish
            in the top left side*/
            double startX = x + 2 * size;  //horiz. 
            double startY = y - 3 * size;  //vertical 
            //margin between scales in the body ajusted to the size
            double spacing = 5 * size;  //horiz.
            double rowSpacing = 4 * size;  //vertical
            
             //Loop to draw the scales
            for (int row = 0; row < rowCount; row++) {
                for (int i = 0; i < scalesPerRow; i++) {
                    // position of every scale
                    double top = startX + (i * spacing);
                    double left = startY + (row * rowSpacing);
                    gc.fillOval(top, left, scaleWidth, scaleHeight);
                }
            }
        }
         // Eye
            gc.setFill(Color.WHITE);
            gc.fillOval(x + 4 * size, y - 2 * size, 5 * size, 5 * size);
    }
}
