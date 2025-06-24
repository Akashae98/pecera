/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author carol
 */
public abstract class Animation {
 
    protected double size;

    protected Animation(double size) {
        this.size = size;
    }

    // Abstract method for each subclass
    public abstract void draw(GraphicsContext gc, double x, double y);

    
}
