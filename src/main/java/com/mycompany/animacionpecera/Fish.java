/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class let us create fishes with position, velocity and random colors,
 * etc. Fishes can be created with a fishfin or with scales depending on whether
 * the boolean hasFishFin its true or false.
 *
 */
public class Fish {
    private double x, y;
    private double dx, dy;    //Direction for movement
    private final Movement movement;
    private final Animation animation;
    private Color color;

    public Fish(double x, double y) {
        this.x= x;
        this.y= y;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        //we use a temporary instance to choose betwen coralfish  or fishidle
        AnimationFishIdle temp = new AnimationFishIdle(x,y);
        Color color = temp.getColor();
        if (color.equals(Color.CORAL)) {
        this.animation = new AnimationCoralFish(x, y);
        } else {
        this.animation = new AnimationFishIdle(x, y);
        }
        this.animation.setPosition(x, y);
    }
    

    //Method of movement
    public void move(int width, int height) {
        x += dx; // horizontal movement
        y += dy; // vertical move
        double[] newDirect = movement.rebound(x, y, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];
        
        animation.setPosition(x, y);
    }

    //Method for drawing overrided
    public void draw(GraphicsContext gc) {
        animation.draw(gc);
    }
    
}
