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
 * Borra Fishtank y mueve todo el codigo relevante que contiene a MainScene.
 * Todas las entidades tienen que estar juntas. Ademas, haz que MainScene tenga
 * una lista de SceneObject, no de de burbujas o peces. Haz dos bucles, uno que
 * actualize todas las posiciones de todos los objectos y luego otr bucle que
 * actualize todas las imagenes. La parte logica y la parte de renderizado
 * estara separada.
 *
 * loop { for object in objects{ updatePosition() } for object in objects{
 * draw() } }
 */
public class MainScene extends Application {

    public static final int CANVAS_WIDTH = 1520;
    public static final int CANVAS_HEIGH = 780;
    public static final Random random = new Random();

    private GraphicsContext gc; //Graphic context to draw in the canvas
    private final List<SceneObject> sceneList = new ArrayList<>(); // List of bubbles
    private boolean showBox;

    @Override
    public void start(Stage stage) {

        // Canvas habilitates to draw
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGH);
        BoundingBox canvasBox = new BoundingBox(new Position(0, 0), new Position(CANVAS_WIDTH, 0),
                new Position(CANVAS_WIDTH, CANVAS_HEIGH), new Position(0, CANVAS_HEIGH));
        gc = canvas.getGraphicsContext2D(); //creates graphicContext in the Canvas

        // At initiate adds 5 fishes in random places 
        for (int i = 0; i < 5; i++) {
            Position position = getRandomPoint();
            addFish(position);
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

                 // Lógica
                for (SceneObject object : sceneList) {
                    object.move();
                }

                // Renderizado
                for (SceneObject object : sceneList) {
                    object.draw(gc, showBox);
                }
            }
        }.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            Position position = new Position(e.getX(), e.getY());
            addFish(position);
        });
        VBox layout = new VBox();
        layout.getChildren().addAll(toggleBoxButton, canvas);
        // Shows the canvas in a window
        stage.setScene(new Scene(layout));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    private void addBubble(double size, double speed, BoundingBox canvasBox) {
        Position pos = getRandomPoint();
        Direction direction = new Direction(0, -speed); 
        Animation animation = new AnimationBubbleIdle(size);
        Movement movement = new LinearMovement(direction);
        Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
        sceneList.add(new Bubble(size, pos, animation, loop));
    }

    // Adds a fish in the array and creates a type of fish 
    public void addFish(Position position) {
        RandomColor randomColor = new RandomColor();
        Animation anim = new AnimationFishIdle(0.5 + random.nextDouble(1),
                random.nextBoolean(), randomColor.getColor());
        Animation anim_coral = new AnimationCoralFish(0.3 + random.nextDouble(0.5));

        BoundingBox canvas = new BoundingBox(new Position(0, 0), new Position(0, CANVAS_WIDTH),
                new Position(CANVAS_WIDTH, CANVAS_HEIGH), new Position(0, CANVAS_HEIGH));

        double dx = Math.random() * 2 - 1;
        double dy = Math.random() * 2 - 1;

        Direction direction1 = new Direction(dx, dy);
        Movement movement1 = new LoopOutOfBoundsMovement(new LinearMovement(direction1), canvas);
        sceneList.add(new Fish(position, movement1, anim));

        Direction direction2 = new Direction(dx + 0.44, dy + 0.44);
        Movement movement2 = new MovementRebound(new LinearMovement(direction2), canvas);
        sceneList.add(new Fish(position, movement2, anim_coral));
    }

    public static Position getRandomPoint() { //to obtain a position inside canvas
        double x = random.nextDouble() * (CANVAS_WIDTH - 40);
        double y = random.nextDouble() * (CANVAS_HEIGH - 40);
        return new Position(x, y);
    }

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }
}
