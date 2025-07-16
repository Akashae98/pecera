/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animacionpecera;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
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
    private boolean Running = true;

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

        Button playPauseButton = new Button("Pause");
        playPauseButton.setStyle(
                "-fx-background-color: #aee0ae; "
                + "-fx-text-fill: white; "
                + "-fx-font-weight: bold; "
                + "-fx-background-radius: 10; "
                + "-fx-margin: 5; "
                + "-fx-padding: 10 10;"
        );

        playPauseButton.setOnAction(e -> {
            Running = !Running;
            playPauseButton.setText(Running ? "Pause" : "Play");
        });

        // Creates MainScene
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (Running) {
                    // Gradient background simulates water 
                    LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                            new Stop(0, Color.rgb(127, 240, 220)),
                            new Stop(1, Color.rgb(70, 130, 180))); //Lighter blue
                    gc.setFill(fondo);
                    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                    //draws/animates bubbles
                    for (Bubble b : bubbleList) {
                        b.move();
                        b.draw(gc, showBox);
                    }
                    //draws/animates fishes
                    fishTank.animate(gc, showBox);
                }
            }
        }.start();

        // User interaction: adds fishes with a click
        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            Position position = new Position(e.getX(), e.getY());
            fishTank.addFish(position);
        });
         // Horizontal layout contains the buttons
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(toggleBoxButton, playPauseButton);
        buttonLayout.setSpacing(10);
        
        //vertical layout adds the buttons and then the canvas or vice versa
        VBox Layout = new VBox();
        Layout.getChildren().addAll(canvas, buttonLayout);

        // Shows the canvas in a window
        stage.setScene(new Scene(Layout));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }
}
