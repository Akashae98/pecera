/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mycompany.animacionpecera.colliders.BoxCollider;
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

    private GraphicsContext gc; //Graphic context to draw in the canvas
    private final List<Bubble> bubbleList = new ArrayList<>(); // List of bubbles
    private final Text textField = new Text();
    private boolean showBox;
    private boolean runSimulation;
    private final List<GameEntity> gameEntities = new ArrayList<>(); // List of bubbles
    private final List<GameSystem> gameSystems = new ArrayList<>();
    final int CANVAS_WIDTH = 1520;
    final int CANVAS_HEIGH = 880;
    @Override
    public void start(Stage stage) {

        // Canvas habilitates to draw
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGH);
        BoundingBox canvasBox = new BoundingBox(new Position(0, 0), new Position(CANVAS_WIDTH, 0),
                new Position(CANVAS_WIDTH, CANVAS_HEIGH), new Position(0, CANVAS_HEIGH));
        gc = canvas.getGraphicsContext2D(); //creates graphicContext in the Canvas

        World world = new World();

        for (int i = 0; i <= 1000; i += 1) {
            world.addEntity(createFish());
        }
        world.addSystem(new MovementSystem());
        world.addSystem(new BorderCollisionSystem(CANVAS_WIDTH, CANVAS_HEIGH));
        world.addSystem(new RenderSystem(canvas));
        world.addSystem(new DebugRenderSystem(canvas));


        GameLoop gameLoop = new GameLoop(world);
        gameLoop.start();


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
            GameState.getInstance().setDebug(!GameState.getInstance().isDebugEnabled());
            toggleBoxButton.setText(showBox ? "Hide Boxes" : "Show Boxes");
        });


        Button toggleRunButton = new Button("Stop simulation");
        toggleRunButton.setStyle(buttonStyle);
        toggleRunButton.setOnAction(e -> {
            if (runSimulation){
                gameLoop.start();
            }else{
                gameLoop.stop();
            }
            runSimulation = !runSimulation;
            toggleRunButton.setText(runSimulation ? "Start simulation" : "Stop simulation");
        });

        Text fpsLabel = new Text();
        fpsLabel.textProperty().bind(GameState.getInstance().getFps().asString("FPS: %d")); // Binding automático

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

    public GameEntity createFish(){
        GameEntity gameEntity = new GameEntity();
        Random random = new Random();
        gameEntity.addComponent(new PositionComponent(new Position(random.nextDouble(CANVAS_WIDTH), random.nextDouble(CANVAS_HEIGH))));
        gameEntity.addComponent(new VelocityComponent(new Direction(random.nextDouble(-1, 1), random.nextDouble(-1, 1))));
        gameEntity.addComponent(new BoxCollider(120,127));
        gameEntity.addComponent(new SpriteComponent("/Images/sketchPezCoral.png"));
        gameEntity.addComponent(new Fish());
        return gameEntity;
    }
}
