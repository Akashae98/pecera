/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class manages a list of fishes.
 */
public class FishTank {

    public static final int CANVAS_WIDTH = 600;
    public static final int CANVAS_HEIGH = 400;
    private final ArrayList<Fish> fishesList;
    private static final Random random = new Random();

    // Constructor: intitialize array list
    public FishTank() {
        this.fishesList = new ArrayList<>();
    }

    public static Position getRandomPoint() { //to obtain a position inside canvas
        double x = random.nextDouble() * CANVAS_WIDTH;
        double y = random.nextDouble() * CANVAS_HEIGH;
        return new Position(x, y);
    }

    public static Random getRandom() { //for methods nextBoolean, nextDouble, etc
        return random;
    }

    // Adds a fish in the array and creates a fish in a position
    public void addFish(Position position) {
        RandomColor randomColor = new RandomColor();
        fishesList.add(new Fish(position, 0.5 + random.nextDouble(), randomColor.getColor()));
    }

    // To animate fishes first we change its position and then we draw
    public void animate(GraphicsContext gc, int width, int height) {
        // we use this method in every frame of the animation timer

        //for each fish in the list we may change the position and draw
        for (Fish fish : fishesList) {
            fish.move(width, height);
            fish.draw(gc);
        }
    }

}
