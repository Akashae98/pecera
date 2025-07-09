
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

    public static final int CANVAS_WIDTH = 900;
    public static final int CANVAS_HEIGH = 700;
    private final ArrayList<Fish> fishesList;
    private static final Random random = new Random();

    // Constructor: intitialize array list
    public FishTank() {
        this.fishesList = new ArrayList<>();
    }

    public static Position getRandomPoint() { //to obtain a position inside canvas
        double x = random.nextDouble() * (CANVAS_WIDTH - 40);
        double y = random.nextDouble() * (CANVAS_HEIGH - 40);
        return new Position(x, y);
    }

    public static Random getRandom() { //for methods nextBoolean, nextDouble, etc
        return random;
    }

    // Adds a fish in the array and creates a type of fish 
    public void addFish(Position position) {
        RandomColor randomColor = new RandomColor();
        Animation anim = new AnimationFishIdle(0.5 + random.nextDouble(1),
                FishTank.getRandom().nextBoolean(), randomColor.getColor());
        Animation anim_coral = new AnimationCoralFish(0.3 + random.nextDouble(0.5));
        
         //pez burbuja
        LinearMovement lineal = new LinearMovement(new Direction(0, -0.6 + Math.random()));
        Movement loop = new LoopOutOfBoundsMovement(lineal, anim.getBoundingBox(position));
        fishesList.add(new Fish(position, loop, anim));
        
        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;
        Direction direction = new Direction(dx, dy);
        Direction direction2 = new Direction(dx * Math.random(), dy * Math.random());
        LinearMovement lineal1 = new LinearMovement(direction);
        LinearMovement lineal2 = new LinearMovement(direction2);
        MovementRebound rebound = new MovementRebound(lineal1, anim.getBoundingBox(position));
        MovementRebound rebound2 = new MovementRebound(lineal2, anim.getBoundingBox(position));

        fishesList.add(new Fish(position, rebound, anim));
        fishesList.add(new Fish(position, rebound2, anim_coral));
    }

    // To animate fishes first we change its position and then we draw
    public void animate(GraphicsContext gc) {
        // we use this method in every frame of the animation timer
        //for each fish in the list we may change the position and draw
        for (Fish fish : fishesList) {
            fish.move();
            fish.draw(gc);
        }
    }

}
