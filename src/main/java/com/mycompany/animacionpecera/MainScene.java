/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.Components.*;
import com.mycompany.animacionpecera.System.RenderSystem;
import com.mycompany.animacionpecera.System.MovementSystem;
import com.mycompany.animacionpecera.System.CanvasBounceSystem;
import com.mycompany.animacionpecera.System.DebugCollisionRender;
import com.mycompany.animacionpecera.System.GameSystem;
import com.mycompany.animacionpecera.System.VelocitySystem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Principal Class which creates the window and the canvas to draw the animation.
 * Controls the animation of fishes and bubbles. You can add fishes with a click
 * on the mousse.
 */
public class MainScene extends Application {

    public static final int canvasWidth = 2200;
    public static final int canvasHeight = 1200;
    public static final Random random = new Random();

    List<Entity> entities = new ArrayList<>();
    List<GameSystem> systems = new ArrayList<>();

    //GameLoop instance
    private GameLoop gameLoop;

    // Principal method to throw the application
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Canvas habilitates to draw
        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        // At initiate add entities
        final int initialFishes = 22;
        initialize(initialFishes);

        CanvasBounceSystem canvasSystem = new CanvasBounceSystem(canvasWidth, canvasHeight);
        MovementSystem movement = new MovementSystem();
        RenderSystem render = new RenderSystem(canvas);
        VelocitySystem vel = new VelocitySystem();
        DebugCollisionRender debug = new DebugCollisionRender(canvas);

        //add systems to the list
        systems.add(movement);
        systems.add(render);
        systems.add(canvasSystem);
        systems.add(vel);
        systems.add(debug);

        //Creates the game loop
        gameLoop = new GameLoop(canvas, entities, systems);

        //buttons
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
            boolean newShowBox = !gameLoop.isShowBox();
            gameLoop.setShowBox(newShowBox);
            toggleBoxButton.setText(newShowBox ? "Hide Boxes" : "Show Boxes");
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
            boolean runningState = !gameLoop.isRunning();
            gameLoop.setRunning(runningState);
            playPauseButton.setText(runningState ? "Pause" : "Play");
        });

        //Start the game loop
        gameLoop.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e
                -> {
            Position position = new Position(e.getX(), e.getY());
            //addCoralECS(position);
            addMulticolorFish(position);
        }
        );

        // Horizontal layout contains the buttons
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(toggleBoxButton, playPauseButton);
        buttonLayout.setSpacing(
                10);

        //vertical layout adds the buttons and then the canvas or viceversa
        VBox Layout = new VBox();
        Layout.getChildren().addAll(buttonLayout, canvas);

        // Shows the canvas in a window
        stage.setScene(new Scene(Layout));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

    private void initialize(int fishes) {
        // At initiate adds x fishes in random places
        for (int i = 0; i < fishes; i++) {
            Position position = getRandomPoint();
            addCoralECS(position);
            addMulticolorFish(position);
        }

        // little bubbles
        for (int i = 0; i < 50; i++) {
            addBubbleECS(0.15 + Math.random() * 0.1, 140 + Math.random());
        }

        // medium bubbles
        for (int i = 0; i < 45; i++) {
            addBubbleECS(0.25 + Math.random() * 0.2, 100 + Math.random());
        }

        //big
        for (int i = 0; i < 20; i++) {
            addBubbleECS(0.4 + Math.random() * 0.3, 80 + Math.random());
        }
    }

    public void addBubbleECS(double size, double speed) {
        Position pos = getRandomPoint();
        Entity bubble = new Entity();
        bubble.add(new Bubble());
        Transform transform = new Transform(pos.x(), pos.y(),0, size, size);
        bubble.add(transform);
        String imageKey = "bubble";
        if (!ImageManager.getInstance().hasImage(imageKey)) {
            ImageManager.getInstance().loadImage(imageKey, "/Images/bubbleColor.png");
        }

        SpriteComponent sprite = new SpriteComponent("bubble");
        bubble.add(sprite);

        double width = ImageManager.getInstance().getWidth("bubble");
        bubble.add(new CircleCollider(width));
        bubble.add(new VelocityComponent(0, -speed));

        entities.add(bubble);
    }

    public void addCoralECS(Position pos) {
        Entity fish = new Entity();
        double scale = 0.3 + random.nextDouble(0.5);
        Transform transform = new Transform(pos.x(), pos.y(),0, scale, scale);
        fish.add(transform);

        String imageKey = "coralfish";
        if (!ImageManager.getInstance().hasImage(imageKey)) {
            ImageManager.getInstance().loadImage(imageKey, "/Images/sketchPezCoral.png");
        }

        SpriteComponent sprite = new SpriteComponent("coralfish");
        fish.add(sprite);
        double width = ImageManager.getInstance().getWidth("coralfish");
        double height = ImageManager.getInstance().getHeight("coralfish");
        physics(fish, width, height);
    }

    public void addMulticolorFish(Position pos) {
        Entity fish = new Entity();
        double scale = 0.3 + random.nextDouble(0.5);
        Transform transform = new Transform(pos.x(), pos.y(), 0, scale, scale);
        fish.add(transform);

        if (!ImageManager.getInstance().hasImage("multicolorfish")) {
            ImageManager.getInstance().loadImage("multicolorfish", "/Images/pink_fish.png");
        }
        if (!ImageManager.getInstance().hasImage("redfish")) {
            ImageManager.getInstance().loadImage("redfish", "/Images/red_fish.png");
        }

        Color color = new RandomColor().getColor();
        String imageKey;
        ColorSprite multi = new ColorSprite(color, random.nextDouble());
        if (multi.getHueType().equals("TURQUOISE") || multi.getHueType().equals("PINK") || multi.getHueType().equals("PURPLE")) {
            imageKey = "multicolorfish";
        } else {
            imageKey = "redfish";
        }
        fish.add(multi);
        SpriteComponent sprite = new SpriteComponent(imageKey);
        fish.add(sprite);
        double width = ImageManager.getInstance().getWidth(imageKey);
        double height = ImageManager.getInstance().getHeight(imageKey);
        physics(fish, width, height);
    }

    private void physics(Entity fish, double width, double height) {
        fish.add(new BoxCollider(width, height));
        double velx = Math.random() * 80 - 40;
        double vely = Math.random() * 80 - 40;
        fish.add(new VelocityComponent(velx, vely));

        entities.add(fish);
    }

    //to obtain a position inside canvas
    public static Position getRandomPoint() {
        double x = random.nextDouble() * (canvasWidth - 40);
        double y = random.nextDouble() * (canvasHeight - 40);
        return new Position(x, y);
    }
}
