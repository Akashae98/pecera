/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public class MovementRebound extends Movement {

    private LinearMovement linealMove;
    private BoundingBox canvasBox;

    public MovementRebound(LinearMovement linealMove, BoundingBox bounding) {
        this.linealMove = linealMove;
        this.canvasBox = bounding;
    }

    /*this method manages linear movement, before the object passes the limits 
    changes direction to the opposite side*/
    @Override
    public Position nextPosition(SceneObject current) {
        //get the next position using the current linear movement
        Position nextPos = linealMove.nextPosition(current);
        BoundingBox nextBox = current.getBoundingBox(nextPos);
        //get current direction and its changed if happens collision
        Direction currentDir = linealMove.getDirection();
        Direction directionRebound = rebound(nextBox, currentDir);
        //updates direction if has collide
        if (!directionRebound.equals(currentDir)) {
            linealMove.setDirection(directionRebound);

        }
        return nextPos;
    }

    private Direction rebound(BoundingBox box, Direction currentDir) {
        double newDx = currentDir.dx();
        double newDy = currentDir.dy();

        if ((box.getTopLeft().x < canvasBox.getTopLeft().x && currentDir.dx() < 0)
                || (box.getBottomRight().x > canvasBox.getBottomRight().x && currentDir.dx() > 0)) {
            newDx *= -1;
        }

        if ((box.getTopLeft().y < canvasBox.getTopLeft().y && currentDir.dy() < 0)
                || (box.getBottomRight().y > canvasBox.getBottomRight().y && currentDir.dy() > 0)) {
            newDy *= -1;
        }

        return new Direction(newDx, newDy);
    }

}
