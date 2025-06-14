/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 This class manages a list of fishes.
 */
public class FishTank {
    public final static int CANVAS_WIDTH = 600;
    public final static int CANVAS_HEIGH = 400;
    private ArrayList<Fish> fishesList;

    // Constructor: intitialize array list
    public FishTank() {
        this.fishesList = new ArrayList<>();
    }
 
    // Adds a fish in the array and creates a fish in a position
    public void addFish(double x, double y) {
        fishesList.add(new Fish(x, y));
    }

    // To animate fishes first we change its position and then we draw
    public void animate(GraphicsContext gc, int width, int height) {
        // we use this method in every frame of the animation timer

        //for each fish in the list we may change the position and draw
        for (Fish fish : fishesList) {
            fish.move(width, height);   
            fish.dibujar(gc);            
        }
    }
}
