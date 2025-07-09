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

    // Mueve el objeto y actualiza su posición y boundingBox
    public void move() {
        boundingBox = anim.getBoundingBox(position);
        position = movement.nextPosition(this);
    }

    public void draw(GraphicsContext gc) {
        anim.draw(gc, position);
    }

    public Position getPosition() {
        return position;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

   //if changes position, return the hitbox in the new position
    public BoundingBox getBoundingBoxAt(Position pos) {
        return anim.getBoundingBox(pos);
    }

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

}


