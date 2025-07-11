/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 * Principal Class wich creates the window and the canvas to draw the animation.
 * Controls the animation of fishes and bubbles. You can add fishes with a click
 * on the mousse.
 */
public class MainScene extends Application {

    private FishTank fishTank;// Object controlling fishes
    private GraphicsContext gc; //Graphic context to draw in the canvas
    private final List<Bubble> bubbleList = new ArrayList<>(); // List of bubbles

    @Override
    public void start(Stage stage) {
        int CANVAS_WIDTH = FishTank.CANVAS_WIDTH;
        int CANVAS_HEIGH = FishTank.CANVAS_HEIGH;
        // Canvas habilitates to draw
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGH);
        BoundingBox canvasBox = new BoundingBox(new Position(0, 0), new Position(0, CANVAS_WIDTH),
                new Position(CANVAS_WIDTH, CANVAS_HEIGH), new Position(0, CANVAS_HEIGH));
        gc = canvas.getGraphicsContext2D(); //creates graphicContext in the Canvas
        fishTank = new FishTank();

        // At initiate Adds 5 fishes in random places 
        for (int i = 0; i < 5; i++) {
            Position position = FishTank.getRandomPoint();
            fishTank.addFish(position);
        }
        //bubbles
        for (int i = 0; i < 40; i++) {
            double size = 3 + Math.random() * 3;
            double speed = 0.6 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed); // the y decreases to the top
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);

            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
        }

        for (int i = 0; i < 35; i++) {
            double size = 8 + Math.random() * 3;
            double speed = 0.4 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed); // the y decreases to the top
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
        }
        for (int i = 0; i < 20; i++) {
            double size = 13 + Math.random() * 3;
            double speed = 0.2 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed); // the y decreases to the top
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);

            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
        }

        // Creates MainScene
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Gradient background simulates water 
                LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.rgb(70, 130, 180)), //Darkerblue
                        new Stop(1, Color.rgb(127, 240, 220))); //Lighter blue
                gc.setFill(fondo);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                //draws/animates bubbles
                for (Bubble b : bubbleList) {
                    b.move();
                    b.draw(gc);
                }
                //draws/animates fishes
                fishTank.animate(gc);
            }
        }.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            Position position = new Position(e.getX(), e.getY());
            fishTank.addFish(position);
        });
        // Shows the canvas in a window
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }
}
