/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author ivani
 */
public class Bubble {

    double x, y, radio, speed;

    private Movement movement;

    public Bubble(double x, double y) {
        this.x = x;
        this.y = y;
        this.radio = 3 + Math.random() * 3;/*bubble ratio using Math.random for variability*/
        this.speed = 0.5 + Math.random();
        /*speed of the movement using variability with random.*/
        this.movement = new Movement();
    }

    public void move() {
        //the position of y decreases to the top
        y -= speed;
        //the position of y returns to the bottom
        this.y = movement.moviAscend(y, radio);
        
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(x, y, radio, radio);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(x, y, radio, radio);//fills with color
    }

}
