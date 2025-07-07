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
    public Position nextPosition(Position current, BoundingBox bounding) {

        double[] newDir = rebound(bounding, direction.dx(), direction.dy());

        direction = new Direction(newDir[0], newDir[1]);

        return current.displacement(direction.dx(), direction.dy());
    }

    public double[] rebound(BoundingBox box, double currentDx, double currentDy) {
        double[] newDirection = {currentDx, currentDy};

        if ((box.getTopLeft().x < 0 && currentDx < 0)
                || (box.getBottomRight().x > width && currentDx > 0)) {
            newDirection[0] *= -1;
        }

        if ((box.getTopLeft().y < 0 && currentDy < 0)
                || (box.getBottomRight().y > height && currentDy > 0)) {
            newDirection[1] *= -1;
        }

        return newDirection;
    }

}
