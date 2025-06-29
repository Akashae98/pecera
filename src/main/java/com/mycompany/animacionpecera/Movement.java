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

        if (box.getTopLeft().x < 0) {
            //changes to get always a positive direction in x axys
            newDirection[0] = (currentDy * currentDy) / (currentDy * currentDy);
        }
        if (box.getBottomRight().x > width) {
            //changes to negtaive direction in x axys
            newDirection[0] = -(currentDy * currentDy) / (currentDy * currentDy);

        }
        if (box.getTopLeft().y < 0) {

            newDirection[1] = (currentDy * currentDy) / (currentDy * currentDy);
        }
        if (box.getBottomRight().y > height) {

            newDirection[1] = -(currentDy * currentDy) / (currentDy * currentDy);
        }

        return newDirection;
    }
    /*an object could be created outside the canvas, this method controlls that this doesn't occurs
    teletransporting the object inside.
    */
    public Position teletransport(BoundingBox box, Position position) {
        double fishWidth = Math.abs(box.getTopRight().x - box.getTopLeft().x);
        double fishHeight = Math.abs(box.getBottomLeft().y - box.getTopLeft().y);
        
        double x = position.getX();
        double y = position.getY();
        
        if (box.getTopLeft().x < 0 || box.getBottomRight().x > width) {
            if (box.getTopLeft().x < -1) {
                x = fishWidth / 3;  // Teletransports
            }
            else if (box.getBottomRight().x > width + 1) {
                x = width - fishWidth;
            }
        }
        if (box.getTopLeft().y < 0 || box.getBottomRight().y > height) {
            if (box.getTopLeft().y < -1) {
                y = fishHeight;
            }
            else if (box.getBottomRight().y > height + 1) {
                y = height - fishHeight;
            }
        }
        
        return new Position (x,y);
    }

    public double moviAscend(Position pos, double radio) {
        double dy = pos.getY();
        // if position + ratio exceeds the top...
        if (pos.getY() + radio < 0) {
            dy = height + Math.random() * 50;
            // the bubble goes to the bottom + random numbeer.
        }
        return dy;
    }

}
