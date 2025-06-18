/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class let us create fishes with position, velocity and random colors,
 * etc. Fishes can be created with a fishfin or with scales depending on whether
 * the boolean hasFishFin its true or false.
 *
 */
public class Fish {

    private double x, y;
    private double dx, dy;    //Direction for movement
    private Movement movement;
    private final Animation anim;
    private ColorRandom color = new ColorRandom();

    public Fish(double x, double y, double size) {
        this.x = x;
        this.y = y;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        this.anim = new AnimationFishIdle(size,FishTank.getRandomPoint().nextBoolean(), this.color);
     
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
        anim.draw(gc, x, y);
    }

}
