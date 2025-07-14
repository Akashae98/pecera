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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
    private final Text textField = new Text();
    private boolean showBox;
    private boolean runSimulation;
    private final List<GameEntity> gameEntities = new ArrayList<>(); // List of bubbles
    private final List<GameSystem> gameSystems = new ArrayList<>();

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

        GameEntity gameEntity = new GameEntity();
        gameEntity.addComponent(new PositionComponent(new Position(50, 50)));
        gameEntity.addComponent(new VelocityComponent(new Direction(1, 1)));
        gameEntity.addComponent(new SpriteComponent("/Images/sketchPezCoral.png"));
        gameEntities.add(gameEntity);

        gameSystems.add(new MovementSystem());
        gameSystems.add(new RenderSystem(canvas));


        // At initiate Adds 5 fishes in random places
//        for (int i = 0; i < 5; i++) {
//            Position position = FishTank.getRandomPoint();
//            fishTank.addFish(position);
//        }
//        //bubbles
//        for (int i = 0; i < 40; i++) {
//            double size = 3 + Math.random() * 3;
//            double speed = 0.6 + Math.random();
//            Position pos = FishTank.getRandomPoint();
//            Direction direction = new Direction(0, -speed); // the y decreases to the top
//            Animation animation = new AnimationBubbleIdle(size);
//            Movement movement = new LinearMovement(direction);
//            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
//
//            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
//        }
//
//        for (int i = 0; i < 35; i++) {
//            double size = 8 + Math.random() * 3;
//            double speed = 0.4 + Math.random();
//            Position pos = FishTank.getRandomPoint();
//            Direction direction = new Direction(0, -speed); // the y decreases to the top
//            Animation animation = new AnimationBubbleIdle(size);
//            Movement movement = new LinearMovement(direction);
//            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
//            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
//        }
//        for (int i = 0; i < 20; i++) {
//            double size = 13 + Math.random() * 3;
//            double speed = 0.2 + Math.random();
//            Position pos = FishTank.getRandomPoint();
//            Direction direction = new Direction(0, -speed); // the y decreases to the top
//            Animation animation = new AnimationBubbleIdle(size);
//            Movement movement = new LinearMovement(direction);
//            Movement loop = new LoopOutOfBoundsMovement(movement, canvasBox);
//
//            bubbleList.add(new Bubble(size, speed, pos, animation, loop));
//        }

        Button toggleBoxButton = new Button("Show Boxes");
        String buttonStyle = "-fx-background-color: #e0aee0; "
                + "-fx-text-fill: white; "
                + "-fx-font-weight: bold; "
                + "-fx-background-radius: 10; "
                + "-fx-border-color: #de7cde; "
                + "-fx-border-width: 1; "
                + "-fx-border-radius: 10; "
                + "-fx-margin-left: 50; "
                + "-fx-margin-right: 50; "
                + "-fx-padding: 10 10;";

        toggleBoxButton.setStyle(buttonStyle);
        toggleBoxButton.setOnAction(e -> {
            showBox = !showBox;
            toggleBoxButton.setText(showBox ? "Hide Boxes" : "Show Boxes");
        });

        Text fpsLabel = new Text();
        fpsLabel.setText("0 FPS");

        // Creates MainScene
         AnimationTimer mainLoop = new AnimationTimer() {
             public double getFps() {
                 return fps;
             }

             long lastFrameTime = 0;
             private double deltaTime = 0;
             private double fps = 0;
             private final long objectiveDeltaTime = 16_666_666;

             private static final double TARGET_FRAME_TIME = 1.0 / 60.0; // 60 FPS en segundos
             private static final long NANOS_PER_FRAME = (long)(TARGET_FRAME_TIME * 1_000_000_000);
            @Override
            public void handle(long now) {
                // 1. Calcular deltaTime
                if (lastFrameTime == 0) {
                    lastFrameTime = now;
                    return;
                }
                double deltaTime = (now - lastFrameTime) / 1_000_000.0;

                // Gradient background simulates water
//                LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
//                        new Stop(0, Color.rgb(127, 240, 220)),
//                        new Stop(1, Color.rgb(70, 130, 180))); //Lighter blue
//                gc.setFill(fondo);
//                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
//
//                //draws/animates bubbles
//                for (Bubble b : bubbleList) {
//                    b.move();
//                    b.draw(gc, showBox);
//                    textField.setText(String.valueOf(b.position.y()));
//                }
//                //draws/animates fishes
//                fishTank.animate(gc, showBox);
                for (GameSystem system : gameSystems) {
                    system.update(gameEntities, deltaTime);
                }
                    // 5. Calcular tiempo de frame completo
                long currentFrameEnd = System.nanoTime();
                long frameDuration = currentFrameEnd - now;

                // 6. Control de FPS (antes de terminar el frame)
                long remainingTime = NANOS_PER_FRAME - frameDuration;

//                if (remainingTime > 100_000) { // Solo si queda más de 0.1ms
//                    try {
//                        Thread.sleep(remainingTime / 1_000_000,
//                                (int)(remainingTime % 1_000_000));
//                    } catch (InterruptedException e) {}
//                }

                // 7. Actualizar lastFrameTime al FINAL del proceso
                fps = 1000.0/ deltaTime; // FPS = 1 / tiempo entre frames
                fpsLabel.setText("FPS: " + fps);
                lastFrameTime = now;


            }
        };
        mainLoop.start();

        Button toggleRunButton = new Button("Stop simulation");
        toggleRunButton.setStyle(buttonStyle);
        toggleRunButton.setOnAction(e -> {
            if (runSimulation){
                mainLoop.start();
            }else{
                mainLoop.stop();
            }
            runSimulation = !runSimulation;
            toggleRunButton.setText(runSimulation ? "Start simulation" : "Stop simulation");
        });

        // User interaction: adds fishes with a click
//        canvas.setOnMouseClicked(e -> {
//            Position position = new Position(e.getX(), e.getY());
//            fishTank.addFish(position);
//        });
        VBox layout = new VBox();
        HBox buttonLayout = new HBox();
        HBox dataLayout = new HBox();
        dataLayout.getChildren().addAll(fpsLabel);


        buttonLayout.getChildren().addAll(toggleBoxButton, toggleRunButton);
        layout.getChildren().addAll(buttonLayout, canvas, dataLayout);
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
