/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
  This class let us create fishes with position, velocity and random colors, etc.
 *Fishes can be created with a fishfin or with scales depending on whether the boolean
 *hasFishFin its true or false.
 * 
 */
public class Fish {
    private double x, y;      // Position 
    private double dx, dy;    //Direction for movement
    private final Color color;
    private static final Random random = new Random(); //Instance of random
    private final boolean hasFishFin; //defines if it has a fish fin

    public Fish(double x, double y) {
        this.x = x; 
        this.y = y;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.color = colorGenerator(); //Assigns a color
        this.hasFishFin = random.nextBoolean(); //decides by random true or false 
        
    }
 // Method for generating blue, pink, purple or default: coral
    private Color colorGenerator() {
        Random rand = new Random();
        int selector = rand.nextInt(3);//selects between 0, 1 o 2
        
        switch (selector) {
       case 0: //Blue colors
                return Color.rgb(             //portions of the colors:
                    50 + rand.nextInt(100),   // red: 50–149
                    150 + rand.nextInt(70),   // green: 150–219
                    180 + rand.nextInt(75)    // blue: 180–254
                );
            case 1: //Pink colors
                return Color.rgb(
                    200 + rand.nextInt(55),   // red: 200–254
                    100 + rand.nextInt(80),   // green: 100–179
                    140 + rand.nextInt(60)    // blue: 140–199
                );
            case 2: //Purple colors
                return Color.rgb(
                    150 + rand.nextInt(50),   // red: 150–199
                    120 + rand.nextInt(60),   // green: 120–179
                    180 + rand.nextInt(40)    // blue: 180–219
                );
        default:
            return Color.CORAL;
        }
    }

    //Method of movement
    public void move (int width, int height) {
        x += dx; // horizontal movement
        y += dy; // vertical move

        //In the limits inverts position, multiplying by -1 changes to the contrary direction
        if (x < 0 || x > (width)) {
            dx *= -1;
        }
        if (y < 0 || y > height) {
            dy *= -1;
        }
    }


    //Method for drawing
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        int fishWidth = 36;
        int fishHeight = 22;
        
        //Body
        gc.fillOval(x - 4, y - 9, fishWidth, fishHeight); 

        //Fish tail
        double[] colaX = {x + 30, x + 45, x + 30};
        double[] colaY = {y, y + 10, y + 20};
        gc.fillPolygon(colaX, colaY, 3);

        //Eye
        gc.setFill(Color.WHITE);
        gc.fillOval(x + 4, y - 2, 5, 5);//position 
        
        //Fishtail or scales
        if (hasFishFin){
            //for true, we draw dorsal fin..
            gc.setStroke(color.darker());//darker color of the body
            gc.setLineWidth(2); //Line with a width
            gc.strokeLine(x + 10, y - 8, x + 20, y - 17);//position of the line
        }
        else{
             // if not we draw little scales
            gc.setFill(color.brighter()); 
            double sizeScale = 0.7;   
            int rows = 3; //number of rows in the body
            
             //Loop to draw the scales
            for (int fila = 0; fila < rows; fila++) {
                for (double i = 1; i < (fishWidth-6); i += (fishWidth-6) / 6) { // 6 scales each row
                    double scaleX = x + i;
                    double scaleY = y - (fishHeight-2)/5 + (fila * ((fishHeight-2) / rows));
                    gc.fillOval(scaleX, scaleY, 6 * sizeScale, 3 * sizeScale);
                }
            }
        }
    }
}