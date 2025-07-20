package com.mycompany.animacionpecera;

import javafx.animation.AnimationTimer;

public class GameLoop extends AnimationTimer {

    private final World world;

    public GameLoop(World world) {
        this.world = world;
    }

    private long lastFrameTime = 0;
    private double deltaTime = 0;
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
        world.update(deltaTime);
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
        int fps = (int) Math.round(1000.0/ deltaTime); // FPS = 1 / tiempo entre frames
        GameState.getInstance().setFps(fps);
        // 7. Actualizar lastFrameTime al FINAL del proceso
        lastFrameTime = now;


    }
};
