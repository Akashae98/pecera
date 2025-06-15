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
public class Fish extends AnimationFishIdle {
   
    private double dx, dy;    //Direction for movement
    private final Color color;
    private static final Random random = new Random(); //Instance of random
    private Movement movement;
    private boolean isCoral;
 

    public Fish(double x, double y) {
        super(x, y, 
             0.5 + random.nextDouble(),//for size 
             random.nextBoolean());//decides fishfin random true or false 
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        this.color = colorGenerator();
        this.isCoral = CoralFish();
    }
    // Method for generating blue, pink, purple or default: coral

    private Color colorGenerator() {
        int selector = random.nextInt(4);//selects between 0, 1 o 2

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

    public final boolean CoralFish() {
        return this.isCoral= this.color.equals(Color.CORAL);
    }

    //Method of movement
    public void move(int width, int height) {
        x += dx; // horizontal movement
        y += dy; // vertical move
        double[] newDirect = movement.rebound(x, y, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];
    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        if (isCoral){
            drawCoralFish(gc);
        }
        drawNormalFish(gc, this.color);
    }
    
}
