/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public class LoopOutOfBoundsMovement extends Movement {

    private Movement linearMove;
    private BoundingBox bounding;

    public LoopOutOfBoundsMovement(Movement Movement, BoundingBox bounds) {
        this.linearMove = Movement;
        this.bounding = bounds;
    }

    @Override
    public Position nextPosition(SceneObject current) {
        //movement
        Position next = linearMove.nextPosition(current);
        BoundingBox box = current.getBoundingBox(current.position);

        // if bubble exceeds the top, then goes to the bottom + random numbeer.
        if (box.getBottomRight().y < 0) {
            double newY = height + Math.random() * 50;
            return new Position(next.x, newY);
        }

        return next;
    }

}
