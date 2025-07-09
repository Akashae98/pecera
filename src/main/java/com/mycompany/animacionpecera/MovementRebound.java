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
    private BoundingBox bounding;

    public MovementRebound(LinearMovement linealMove, BoundingBox bounding) {
        this.linealMove = linealMove;
        this.bounding = bounding;
    }

    /*this method changes linear movement before object passes the limits, 
    always to the opposite direction*/
    @Override
    public Position nextPosition(SceneObject current) {
        
        Direction direction = linealMove.getDirection();
        Position actualPos = current.getPosition();
        Position nextPos = actualPos.displacement(direction.dx(), direction.dy());

        BoundingBox nextBox = current.getBoundingBox(nextPos);

        Direction directionRebound = rebound(nextBox, direction);
        linealMove.setDirection(directionRebound);

        return actualPos.displacement(directionRebound.dx(), directionRebound.dy());
    }

    public Direction rebound(BoundingBox box, Direction currentDir) {
        double newDx = currentDir.dx();
        double newDy = currentDir.dy();

        if ((box.getTopLeft().x < 0 && currentDir.dx() < 0)
                || (box.getBottomRight().x > width && currentDir.dx() > 0)) {
            newDx *= -1;
        }

        if ((box.getTopLeft().y < 0 && currentDir.dy() < 0)
                || (box.getBottomRight().y > height && currentDir.dy() > 0)) {
            newDy *= -1;
        }

        return new Direction(newDx, newDy);
    }

}
