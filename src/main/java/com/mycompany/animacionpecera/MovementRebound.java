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

    public MovementRebound() {
        super(new Direction(Math.random() * 2 - 1, Math.random() * 2 - 1));
    }

    /*this method changes direction before object passes the limits, 
    always to the opposite direction*/
    @Override
    public Position nextPosition(Position current, BoundingBox boundingbox) {

        Direction newDirection = rebound(boundingbox, direction);
        direction = newDirection;
        return current.displacement(newDirection.dx(), newDirection.dy());
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
