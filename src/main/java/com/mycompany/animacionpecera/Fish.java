/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;


/**
 * This class let us create fishes with position, velocity and random colors,
 * etc. Fishes can be created with a fishfin or with scales depending on whether
 * the boolean hasFishFin its true or false.
 *
 */
public class Fish {

    private Position position;
    private Movement movement;
    private final Animation anim;
    public BoundingBox boundingBox;

    public Fish(Position pos, Animation animationType, Movement movementType) {
        this.position = pos;
        this.movement = movementType;
        this.anim = animationType;
        this.boundingBox = anim.getBoundingBox(position);
    }

    //Method of movement
    public void move() {
        //updates boundingbox position
        boundingBox = anim.getBoundingBox(position);

        //handles rebounding and changes direction
        position = this.movement.nextPosition(position, boundingBox);

    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, position);

    }

}
