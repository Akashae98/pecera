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

    /*an object could be created outside the canvas, this method controlls that this doesn't occurs
    teletransporting the object inside.
     */
    public Position teletransport(BoundingBox box, Position position) {
        double fishWidth = (box.getTopRight().x - box.getTopLeft().x);
        double fishHeight = (box.getBottomLeft().y - box.getTopLeft().y);

        double x = position.x;
        double y = position.y;

        if (box.getTopLeft().x < -1) {
            x = fishWidth / 3;  // Teletransports
        } else if (box.getBottomRight().x > width + 1) {
            x = width - fishWidth;
        }

        if (box.getTopLeft().y < -1) {
            y = fishHeight;
        } else if (box.getBottomRight().y > height + 1) {
            y = height - fishHeight;
        }

        return new Position(x, y);
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
