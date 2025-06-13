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
    int CANVAS_WIDTH = 600;
    int CANVAS_HEIGHT = 400;
    private Movement m;

    public Bubble(double x, double y) {
        this.x = x;
        this.y = y;
        this.radio = 3 + Math.random() * 3;/*bubble ratio using Math.random for variability*/
        this.speed = 0.5 + Math.random();
        /*speed of the movement using variability with random.*/
        this.m = new Movement();
    }

    public void mover() {
        //the position of y decreases
        y -= speed;
        //the position of y returns to the bottom
        this.y = m.moviAscend(y, radio);
        
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //white color semitransparent
        gc.fillOval(x, y, radio, radio);//fills with color the inside of bubble
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));//white color for the bubble edge
        gc.strokeOval(x, y, radio, radio);//fills with color
    }

}
