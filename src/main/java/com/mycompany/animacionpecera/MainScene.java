/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
 *
 */
public class MainScene extends Application {

    public static final int CANVAS_WIDTH = 1520;
    public static final int CANVAS_HEIGH = 780;
    public static final Random random = new Random();

    private GraphicsContext gc; //Graphic context to draw in the canvas
    private final List<SceneObject> sceneList = new ArrayList<>();
    private boolean showBox;
    BoundingBox canvasBox = new BoundingBox(new Position(0, 0), new Position(CANVAS_WIDTH, 0),
            new Position(CANVAS_WIDTH, CANVAS_HEIGH), new Position(0, CANVAS_HEIGH));

    @Override
    public void start(Stage stage) {
        // Canvas habilitates to draw
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGH);
        gc = canvas.getGraphicsContext2D(); //creates graphicContext in the Canvas

        // At initiate adds 10 fishes in random places 
        for (int i = 0; i < 5; i++) {
            Position position = getRandomPoint();
            addFish(position);
            Position position2 = getRandomPoint();
            addCoralFish(position2);
        }

        // little bubbles
        for (int i = 0; i < 40; i++) {
            addBubble(3 + Math.random() * 3, 0.6 + Math.random(), canvasBox);
        }

        // medium
        for (int i = 0; i < 35; i++) {
            addBubble(8 + Math.random() * 3, 0.4 + Math.random(), canvasBox);
        }

        //big
        for (int i = 0; i < 20; i++) {
            addBubble(13 + Math.random() * 3, 0.2 + Math.random(), canvasBox);
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
            @Override
            public void handle(long now) {
                // Gradient background simulates water 
                LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.rgb(127, 240, 220)),
                        new Stop(1, Color.rgb(70, 130, 180))); //Lighter blue
                gc.setFill(fondo);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                // Logic
                for (SceneObject object : sceneList) {
                    object.move();
                }

                // Rendering
                for (SceneObject object : sceneList) {
                    object.draw(gc, showBox);
                }
            }
        }.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            Position position = new Position(e.getX(), e.getY());
            addFish(position);
            addCoralFish(position);
        });
        VBox layout = new VBox();
        layout.getChildren().addAll(toggleBoxButton, canvas);
        // Shows the canvas in a window
        stage.setScene(new Scene(layout));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    //to create bubbles
    private void addBubble(double size, double speed, BoundingBox canvasBox) {
        Position pos = getRandomPoint();
        Direction direction = new Direction(0, -speed);
        Animation animation = new AnimationBubbleIdle(size);
        Movement movement = new LinearMovement(direction);
        Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
        sceneList.add(new Bubble(size, pos, animation, loop));
    }

    // Creates normal fishes 
    public void addFish(Position position) {
        RandomColor randomColor = new RandomColor();
        Animation anim = new AnimationFishIdle(0.5 + random.nextDouble(1),
                random.nextBoolean(), randomColor.getColor());

        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;

        Direction direction = new Direction(dx, dy);
        Movement movement = new LoopOutOfBoundsMovement(new LinearMovement(direction), canvasBox);
        sceneList.add(new Fish(position, movement, anim));

    }

    //Creates coralfish
    public void addCoralFish(Position position) {
        Animation anim_coral = new AnimationCoralFish(0.3 + random.nextDouble(0.5));

        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;

        Direction direction = new Direction(dx, dy);
        Movement movement = new MovementRebound(new LinearMovement(direction), canvasBox);
        sceneList.add(new Fish(position, movement, anim_coral));
    }
    
    //to obtain a position inside canvas
    public static Position getRandomPoint() { 
        double x = random.nextDouble() * (CANVAS_WIDTH - 40);
        double y = random.nextDouble() * (CANVAS_HEIGH - 40);
        return new Position(x, y);
    }

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }
}
