/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author carol
 */
public abstract class SceneObject {

    protected Position position;
    protected Movement movement;
    protected Animation anim;
    protected BoundingBox boundingBox;

    public SceneObject(Position position, Animation animationType, Movement movementType) {
        this.position = position;
        this.anim = animationType;
        this.movement = movementType;
        this.boundingBox = anim.getBoundingBox(position);
    }

    public void move(double deltaTime) {
        boundingBox = anim.getBoundingBox(position);
        position = movement.nextPosition(this, deltaTime);
    }

    public void draw(GraphicsContext gc, boolean showBox, double deltaTime) {
        anim.update(gc, position, showBox);
    }

    public Position getPosition() {
        return position;
    }

    //if changes position, return the hitbox in the new position
    public BoundingBox getBoundingBox(Position pos) {
        return anim.getBoundingBox(pos);
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

}
