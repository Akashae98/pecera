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

    double size, speed;
    private Movement movement;
    private Animation anim;
    private Position position;

    public Bubble(Position position, double size, double speed) {
        this.position = position;
        this.movement = new Movement();
        this.size = size;
        this.speed = speed;
        this.anim = new AnimationBubbleIdle(size);

    }

    public void move() {
        //the position of y decreases to the top
        this.position = position.displacement(0, -speed);

        //the position of y returns to the bottom
        double newY = movement.moviAscend(position, size);
        if (newY != position.y) { 
            this.position = new Position(position.x, newY);
        }
    }

    //Method for drawing
    public void draw(GraphicsContext gc, boolean showBox) {
        anim.draw(gc, position, showBox);
    }

}
