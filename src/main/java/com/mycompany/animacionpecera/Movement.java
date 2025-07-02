/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public class Movement {

    //limits of the aquarium
    int width = FishTank.CANVAS_WIDTH;
    int height = FishTank.CANVAS_HEIGH;

    /*this method changes direction before object passes the limits, 
    always to the opposite direction*/
    public double[] rebound(BoundingBox box, double currentDx, double currentDy) {
        double[] newDirection = {currentDx, currentDy};

        // horizontal
        if ((box.getTopLeft().x < 0 && currentDx < 0)
                || (box.getBottomRight().x > width && currentDx > 0)) {
            newDirection[0] *= -1;
        }

        // vertical
        if ((box.getTopLeft().y < 0 && currentDy < 0)
                || (box.getBottomRight().y > height && currentDy > 0)) {
            newDirection[1] *= -1;
        }

        return newDirection;
    }

    public double moviAscend(Position pos, double radio) {
        double dy = pos.y;
        // if position + ratio exceeds the top...
        if (pos.y + radio < 0) {
            dy = height + Math.random() * 50;
            // the bubble goes to the bottom + random numbeer.
        }
        return dy;
    }

}
