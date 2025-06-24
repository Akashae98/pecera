/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author ivani
 */
public class Bubble {

    double x, y, size, speed;
    private Movement movement;
    private Animation anim;
    private Position position;

    public Bubble(double x, double y, double size, double speed) {
        this.x = x;
        this.y = y;
        this.movement = new Movement();
        this.size = size;
        this.speed = speed;
        this.anim = new AnimationBubbleIdle(size);

    }

    public void move() {
        //the position of y decreases to the top
        y -= speed;
        this.position= new Position(x,y);
        //the position of y returns to the bottom
        this.y = movement.moviAscend(position, size);

    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, x, y);
    }

}
