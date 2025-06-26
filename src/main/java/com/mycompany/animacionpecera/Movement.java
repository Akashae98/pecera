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

    //this method changes direction before object passes the limits

    public double[] rebound(BoundingBox box, double currentDx, double currentDy) {
        //array that returns two variables
        double[] newDirection = {currentDx, currentDy};

        if (box.getLeft() < 0 || box.getRight() > width) {
            newDirection[0] *= -1;
        }
        if (box.getTop() < 0 || box.getBottom() > height) {
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
