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
    private BoundingBox canvasBox;

    public LoopOutOfBoundsMovement(Movement Movement, BoundingBox bounds) {
        this.linearMove = Movement;
        this.canvasBox = bounds;
    }

    @Override
    public Position nextPosition(SceneObject current) {
        //movement
        Position next = linearMove.nextPosition(current);
        BoundingBox box = current.getBoundingBox(current.position);

        double newX = next.x;
        double newY = next.y;

        //horizontal teleport to the other side if exceedes the canvas
        if (box.getBottomRight().x < canvasBox.getTopLeft().x) {
            newX = canvasBox.getBottomRight().x;
        } else if (box.getTopLeft().x > canvasBox.getBottomRight().x) {
            newX = canvasBox.getTopLeft().x;
        }
        //vertical teleport to the other side
        if (box.getBottomRight().y < canvasBox.getTopLeft().y) {
            newY = canvasBox.getBottomRight().y;

        } else if (box.getTopLeft().y > canvasBox.getBottomRight().y) {
            newY = canvasBox.getTopLeft().y;
        }

        return new Position(newX, newY);
    }

}
