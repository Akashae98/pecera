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
 Principal Class wich creates the window and the canvas to draw the animation.
 Controls the animation of fishes and bubbles. 
 You can add fishes with a click on the mousse. 
 */
public class Animation extends Application {
    private FishTank fishTank;// Object controlling fishes
    private GraphicsContext gc; //Graphic context to draw in the canvas
    private List<Bubble> bubbleList = new ArrayList<>(); // List of bubbles


    @Override
    public void start(Stage stage) {
        // Canvas of 600x400 pixels habilitates to draw
        Canvas canvas = new Canvas(600, 400);
        gc = canvas.getGraphicsContext2D(); //creates graphicContext in the Canvas
        fishTank = new FishTank(); //instance of FishTank
        
        // At initiate Adds 5 fishes in random places 
        for (int i = 0; i < 5; i++) {
            fishTank.addFish(
                Math.random() * canvas.getWidth(),
                Math.random() * canvas.getHeight()
            );
        }
        /* Creates 30 random bubbles at the bottom of the canvas by passing to the y
        the method .getHeight. Math.random generates a number between 0.0 and <1.0 */
        for (int i = 0; i < 30; i++) {
        bubbleList.add(new Bubble(Math.random() * canvas.getWidth(), 
                             Math.random() * canvas.getHeight()));
        }
        // Creates Animation
        new AnimationTimer() {
            @Override
            public void handle(long now) {
           // Gradient background simulates water 
            LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(70, 130, 180)),   //Darkerblue
            new Stop(1, Color.rgb(127, 240, 220))); //Lighter blue
            gc.setFill(fondo);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            
            //draws/animates bubbles
            for (Bubble b : bubbleList) {
                b.mover();
                b.dibujar(gc);
            }
            //draws/animates fishes
            fishTank.animate(gc, (int)canvas.getWidth(), (int)canvas.getHeight());
            }
        }.start();

        // User interaction: adds fishes with a click
        canvas.setOnMouseClicked(e -> {
            fishTank.addFish(e.getX(), e.getY());
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
