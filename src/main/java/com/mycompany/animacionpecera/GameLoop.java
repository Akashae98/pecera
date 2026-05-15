/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.System.DebugCollisionRender;
import com.mycompany.animacionpecera.System.GameSystem;
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
    List<Entity> entities;
    List<GameSystem> systems;
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

    //Constructor
    public GameLoop(Canvas canvas, List<Entity> entities, List<GameSystem> systems) {
        this.gc = canvas.getGraphicsContext2D();
        this.canvas = canvas;
        this.entities = entities;
        this.systems = systems;
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

        // Updates fps stats
        updateFpsStats(deltaTime);

        // UI (FPS)
        displayFPS();

        // Cap FPS to 60 (max 16.67 ms/frame)
        capFrameRate(now);

        //ECS
        for (GameSystem system : systems) {
            system.update(entities, deltaTime);
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
    public void setShowBox(boolean show) {
        this.showBox = show;
        for (GameSystem system : systems) {
            if (system instanceof DebugCollisionRender debugRender) {
                debugRender.setShowBox(show);
            }
        }
    }

    public boolean isShowBox() {
        return showBox;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

}
