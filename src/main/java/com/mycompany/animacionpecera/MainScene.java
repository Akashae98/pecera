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
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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
    private boolean showBox;

    @Override
    public void start(Stage stage) {
        int CANVAS_WIDTH = FishTank.CANVAS_WIDTH;
        int CANVAS_HEIGH = FishTank.CANVAS_HEIGH;
        // Canvas habilitates to draw
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGH);
        BoundingBox canvasBox = new BoundingBox(new Position(0, 0), new Position(CANVAS_WIDTH, 0),
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
            double speed = 150 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed); // the y decreases to the top
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);

            bubbleList.add(new Bubble(size, pos, animation, loop));
        }

        for (int i = 0; i < 35; i++) {
            double size = 8 + Math.random() * 3;
            double speed = 140 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed); // the y decreases more slowly
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
            bubbleList.add(new Bubble(size, pos, animation, loop));
        }
        for (int i = 0; i < 10; i++) {
            double size = 15 + Math.random() * 2;
            double speed = 100 + Math.random();
            Position pos = FishTank.getRandomPoint();
            Direction direction = new Direction(0, -speed);
            Animation animation = new AnimationBubbleIdle(size);
            Movement movement = new LinearMovement(direction);
            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);

            bubbleList.add(new Bubble(size, pos, animation, loop));
        }

        Button toggleBoxButton = new Button("Show Boxes");
        toggleBoxButton.setStyle(
                "-fx-background-color: #e0aee0; "
                + "-fx-text-fill: white; "
                + "-fx-font-weight: bold; "
                + "-fx-background-radius: 10; "
                + "-fx-margin-left: 5; "
                + "-fx-padding: 10 10;"
        );
        toggleBoxButton.setOnAction(e -> {
            showBox = !showBox;
            toggleBoxButton.setText(showBox ? "Hide Boxes" : "Show Boxes");
        });

        // Creates MainScene
        new AnimationTimer() {
            private long lastUpdate = 0;
            private final long frameInterval = 16_666_667;//60 fps

            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now;
                    return;
                }
                
                if (now - lastUpdate < frameInterval) {
                    return;
                }
                //deltatime its seconds between current frame and the last
                double deltaTime = (now - lastUpdate) / 1_000_000_000.0; // nanoseconds per second
                lastUpdate = now;
                
                // Gradient background simulates water 
                LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.rgb(127, 240, 220)),
                        new Stop(1, Color.rgb(70, 130, 180))); //Lighter blue
                gc.setFill(fondo);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                //draws/animates bubbles
                for (Bubble b : bubbleList) {
                    b.move(deltaTime);
                    b.draw(gc, showBox);
                }
                //draws/animates fishes
                fishTank.animate(gc, showBox, deltaTime);
            }
        }.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            Position position = new Position(e.getX(), e.getY());
            fishTank.addFish(position);
        });
        VBox layout = new VBox();
        layout.getChildren().addAll(toggleBoxButton, canvas);

        // Shows the canvas in a window
        stage.setScene(new Scene(layout));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }
}
