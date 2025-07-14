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

    /*this method changes linear movement before object passes the limits, 
    always to the opposite direction*/
    @Override
    public Position nextPosition(SceneObject current) {

        Direction direction = linealMove.getDirection();
        Position currentPos = current.getPosition();
        Position nextPos = currentPos.displacement(direction);

        BoundingBox nextBox = current.getBoundingBox(nextPos);

        Direction directionRebound = rebound(nextBox, direction);
        linealMove.setDirection(directionRebound);

        return currentPos.displacement(directionRebound);
    }

    private Direction rebound(BoundingBox box, Direction currentDir) {
        double newDx = currentDir.dx();
        double newDy = currentDir.dy();

        if ((box.topLeft().x() < canvasBox.topLeft().x() && currentDir.dx() < 0)
                || (box.bottomRight().x() > canvasBox.bottomRight().x() && currentDir.dx() > 0)) {
            newDx *= -1;
        }

        if ((box.topLeft().y() < canvasBox.topLeft().y() && currentDir.dy() < 0)
                || (box.bottomRight().y() > canvasBox.bottomRight().y() && currentDir.dy() > 0)) {
            newDy *= -1;
        }

        return new Direction(newDx, newDy);
    }

}
