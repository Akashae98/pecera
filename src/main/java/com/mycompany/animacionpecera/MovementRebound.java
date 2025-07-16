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
    public Position nextPosition(SceneObject current, double deltaTime) {
        //get the next position using the current linear movement
        Position nextPos = linealMove.nextPosition(current, deltaTime);
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
        if ((box.topLeft().x() < canvasBox.topLeft().x() && currentDir.dx() < 0)
                || (box.bottomRight().x() > canvasBox.bottomRight().x() && currentDir.dx() > 0)) {
            currentDir = currentDir.invertX();
        }

        if ((box.topLeft().y() < canvasBox.topLeft().y() && currentDir.dy() < 0)
                || (box.bottomRight().y() > canvasBox.bottomRight().y() && currentDir.dy() > 0)) {
            currentDir = currentDir.invertY();
        }

        return currentDir;
    }

}
