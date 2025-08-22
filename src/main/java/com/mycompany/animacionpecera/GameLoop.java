/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carol
 */
public class GameLoop extends AnimationTimer {

    private final GraphicsContext gc;
    private final Canvas canvas;
    private final List<SceneObject> sceneObjectList;
    private boolean showBox;
    private boolean running = true;

    // Timing variables
    private long lastUpdate = 0;
    private final long FRAME_INTERVAL = 16_666_667; // 60 fps
    public static final double FRAME_SKIP_THRESHOLD = 0.5;
    private double elapsedTime = 0;
    private int frames = 0;
    private double fps;
    private double msPerFrame;

    // Constructor
    public GameLoop(GraphicsContext gc, Canvas canvas, List<SceneObject> sceneObjectList) {
        this.gc = gc;
        this.canvas = canvas;
        this.sceneObjectList = sceneObjectList;
    }

    @Override
    public void handle(long now) {
        if (!running) {
            lastUpdate = now;
            return;
        }

        if (lastUpdate == 0) {
            lastUpdate = now;
            return;
        }

        //Deltatime its seconds between current frame and the last
        double deltaTime = (now - lastUpdate) / 1_000_000_000.0; // nanoseconds per second
        lastUpdate = now;

        // clamping delta
        if (deltaTime > FRAME_SKIP_THRESHOLD && DebugUtil.isDebugging()) {
            System.out.println("Skipping frame: " + deltaTime);
            lastUpdate = now;
            return;
        }

        // Logic
        updateGameLogic(deltaTime);

        // Rendering
        renderScene(deltaTime);

        // Updates fps stats
        updateFpsStats(deltaTime);

        // UI (FPS)
        displayFPS();

        // Cap FPS to 60 (max 16.67 ms/frame)
        capFrameRate(now);
    }

    private void updateGameLogic(double deltaTime) {
        for (SceneObject object : sceneObjectList) {
            object.move(deltaTime);
        }
    }

    private void renderScene(double deltaTime) {
        // Gradient background simulates water 
        LinearGradient background = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.rgb(127, 240, 220)),
                new Stop(1, Color.rgb(70, 130, 180)));
        gc.setFill(background);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Rendering
        for (SceneObject object : sceneObjectList) {
            object.draw(gc, showBox, deltaTime);
        }
    }

    private void updateFpsStats(double deltaTime) {
        elapsedTime += deltaTime;
        frames++;

        if (elapsedTime >= 0.5) {
            fps = frames / elapsedTime;
            msPerFrame = (elapsedTime / frames) * 1000;
            elapsedTime = 0;
            frames = 0;
        }
    }

    private void displayFPS() {
        gc.setFill(Color.MAGENTA);
        gc.fillText(String.format("%.2f FPS", fps), 10, 20);
        gc.fillText(String.format("%.3f ms/frame", msPerFrame), 10, 35);
    }

    private void capFrameRate(long now) {
        long elapsed = now - lastUpdate;
        if (elapsed < FRAME_INTERVAL) {
            try {
                Thread.sleep((FRAME_INTERVAL - elapsed) / 1_000_000);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameLoop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Public methods to control animation states
    public void setShowBox(boolean showBox) {
        this.showBox = showBox;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isShowBox() {
        return showBox;
    }

}
