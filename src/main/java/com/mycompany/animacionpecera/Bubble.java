/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class represents bubbles. 
 * Controls its position, change of posifition and draws simulating movement upwards,
 * and maybe other movements...
 */
public class Bubble {
      double x, y, radio, speed;
      int CANVAS_WIDTH = 600;
      int CANVAS_HEIGHT = 400;

    public Bubble(double x, double y) {
        this.x = x;
        this.y = y;
        this.radio = 3 + Math.random() * 3;/*bubble ratio using Math.random for variability*/
        this.speed = 0.5 + Math.random(); /*speed of the movement using variability with random.*/
    }

    public void mover() {
        //the position of y decreases starting on the bottom,that makes bubbles GO UP.
        y -= speed;
        if (y + radio < 0) { //If bubble passes the top, returns down to canvas height
            y = CANVAS_HEIGHT + Math.random() * 50;
        //remember 400 is the height and the down limit of the aquarium.
        //with random, bubbles dont appear in the same position of y, so the flow its more natural.
       /* } else{
            x = Math.random() * CANVAS_WIDTH; //this adds a watercourse
       */ }
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(x, y, radio, radio);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(x, y, radio, radio);//fills with color
    }

}
