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

    private double x, y, dx, dy;    //Direction for movement
    private Movement movement;
    private final Animation anim;
    private Position position;
   

    public Fish(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        this.anim = new AnimationFishIdle(size,FishTank.getRandom().nextBoolean(),color);
    
    }

    //Method of movement
    public void move(int width, int height) {
        x += dx; // horizontal movement
        y += dy; // vertical move
        this.position= new Position(x,y);
        double[] newDirect = movement.rebound(position, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];

    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, x, y);
    }

}