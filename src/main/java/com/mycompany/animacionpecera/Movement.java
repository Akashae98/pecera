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
   public double[] rebound(double x, double y, double size, double currentDx, double currentDy) {
    //array that returns two variables
    double[] newDirection = {currentDx, currentDy};
    if (x < 0 || x+35*size > width) {
        newDirection[0] *= -1;
    }
    if (y < 0 || y+22*size > height) {
        newDirection[1] *= -1;
    }  
    return newDirection;
}
    public double moviAscend(double y, double radio) {    
     // if position + ratio exceeds the top...
        if (y + radio < 0) { 
            y = height + Math.random() * 50;
        // the bubble goes to the bottom + random numbeer.
        } 
        return y;
    }
    
}
