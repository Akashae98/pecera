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
    private BoundingBox boundingBox;

    public Fish(Position pos, double size, Color color) {
        this.position = pos;
        this.dx = Math.random() * 2 - 1; //Aleatory movement between -1 and 1 in x-axis
        this.dy = Math.random() * 2 - 1;// Same in y-axis
        this.movement = new Movement();
        this.anim = new AnimationFishIdle(size, FishTank.getRandom().nextBoolean(), color);
        this.boundingBox = anim.getBoundingBox(position);

    }

    //Method of movement
    public void move(int width, int height) {
        position.x += dx; // horizontal movement
        position.y += dy; // vertical move
        boundingBox.setPosition(position);
        double[] newDirect = movement.rebound(boundingBox, dx, dy);
        this.dx = newDirect[0];
        this.dy = newDirect[1];

    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, position.x, position.y);
    }

}
