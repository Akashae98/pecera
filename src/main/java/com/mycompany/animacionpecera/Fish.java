/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;

/**
 * This class manages the position, movement and animation of different
 * fishes...
 *
 */
public class Fish {

    private Position position;
    private Movement movement;
    private final Animation anim;
    public BoundingBox boundingBox;

    public Fish(Position position, Animation animationType, Movement movementType) {
        this.position = position;
        this.anim = animationType;
        this.movement = movementType;
        this.boundingBox = anim.getBoundingBox(this.position);
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
