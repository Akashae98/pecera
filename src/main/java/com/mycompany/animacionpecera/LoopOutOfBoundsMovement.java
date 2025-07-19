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

    private Movement linealMove;
    private BoundingBox canvasBox;

    public LoopOutOfBoundsMovement(Movement Movement, BoundingBox bounds) {
        this.linealMove = Movement;
        this.canvasBox = bounds;
    }

    @Override
    public Position nextPosition(SceneObject current, double deltaTime) {
        //get the next position using the current linear movement
        Position next = linealMove.nextPosition(current, deltaTime);
        BoundingBox box = current.getBoundingBox(current.position);

        double newX = next.x();
        double newY = next.y();

        //horizontal teleport to the other side if exceedes the canvas
        if (box.bottomRight().x() < canvasBox.topLeft().x()) {
            newX = canvasBox.bottomRight().x();
        } else if (box.topLeft().x() > canvasBox.bottomRight().x()) {
            newX = canvasBox.topLeft().x();
        }
        //vertical teleport to the other side
        if (box.bottomRight().y() < canvasBox.topLeft().y()) {
            newY = canvasBox.bottomRight().y();

        } else if (box.topLeft().y() > canvasBox.bottomRight().y()) {
            newY = canvasBox.topLeft().y();
        }

        return new Position(newX, newY);
    }

}
