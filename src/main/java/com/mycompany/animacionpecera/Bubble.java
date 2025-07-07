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

    private Position position;
    private Movement movement;
    private Animation anim;
    public BoundingBox boundingBox;

    public Bubble(Position position, double size, double speed, Animation animationType, Movement movementType) {
        this.position = position;
        this.movement = movementType;
        this.anim = animationType;
        this.boundingBox = anim.getBoundingBox(position);
    }

    public void move() {
        //in case bubble surprass the top
        position = this.movement.nextPosition(position, this.boundingBox);
    }

    //Method for drawing
    public void draw(GraphicsContext gc) {
        anim.draw(gc, position);
    }

}
