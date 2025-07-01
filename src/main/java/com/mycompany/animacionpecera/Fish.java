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

    private double dx, dy;    //Direction for movement
    private Movement movement;
    private final Animation anim;
    private Position position;
    public BoundingBox boundingBox;

    public Fish(Position pos, double size, Color color) {
        this.position = pos;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        this.anim = new AnimationFishIdle(size, FishTank.getRandom().nextBoolean(), color);
        this.boundingBox = anim.getBoundingBox(position);
    }

    //Method of movement
    public void move() {
        // updates postion
        position = position.displacement(dx, dy);
        
        //updates boundingbox position
        boundingBox = anim.getBoundingBox(position);
        
        //handles rebounding and changes direction
        double[] newDirect = movement.rebound(boundingBox, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];

        //relocates a fish inside canvas if needed
        position = movement.teletransport(boundingBox, position);
        boundingBox = anim.getBoundingBox(position);
    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, position);

    }

}
