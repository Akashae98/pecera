/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author carol
 */
public class AnimationFishIdle extends Animation {

    private final Color color;
    private final Image image;
    private String imageName;
    Random random = new Random();
    double hueRandom;

    public AnimationFishIdle(double size, Color color) {
        super(size);
        this.color = color;
        this.image = loadFishImageByColor(color);
        this.hueRandom = random.nextDouble();
    }

    private Image loadFishImageByColor(Color color) {
        String imagePath = "/Images/";

        double hue = color.getHue(); // de 0 a 360
        //we use the pink image base to color on using the hue
        if (isPinkHue(hue)|| isTurquoiseHue(hue) || isPurpleHue(hue)) {
            imageName = "pink_fish.png";
        } else {
            imageName = "red_fish.png";
        }

        try {
            return new Image(getClass().getResourceAsStream(imagePath + imageName));
        } catch (Exception e) {
            System.err.println("We didn`t find " + imageName + ", using grey image...");
            return new Image(getClass().getResourceAsStream(imagePath + "gray_fish.png"));
        }
    }

    private boolean isPinkHue(double hue) {
        return hue >= 170 && hue <= 200;
    }

    private boolean isTurquoiseHue(double hue) {
        return hue >= 330 || hue <= 20;
    }

    private boolean isPurpleHue(double hue) {
        return hue >= 260 && hue < 330;
    }

    private double getWidth() {
        return image.getWidth() * size;
    }

    private double getHeight() {
        return image.getHeight() * size;
    }

    @Override
    public void draw(GraphicsContext gc, Position pos) {
        gc.save();
        gc.setEffect(null);

        double hue = color.getHue();
        double normalizedHue = (hue - 180) / 180.0; // 0–360 → -1 a 1

        ColorAdjust colorAdjust = new ColorAdjust();

        if (isTurquoiseHue(hue)) {
            // turns the pink image to blue colors
            colorAdjust.setHue(normalizedHue);
            colorAdjust.setSaturation(0.5);
            colorAdjust.setBrightness(0.1);

        } else if (isPinkHue(hue)) {

            colorAdjust.setHue(0 + (hueRandom) * 0.3 - 0.1);//ajusted pink
            colorAdjust.setSaturation(0.45);
            colorAdjust.setBrightness(0.35);

        } else if (isPurpleHue(hue)) {
            colorAdjust.setHue(0 - (hueRandom * 0.5));//ajusted purples anb blues
            colorAdjust.setSaturation(0.40);
            colorAdjust.setBrightness(0.25);

        } else {
            // Coral to golden colors
            colorAdjust.setHue(hueRandom * 0.12);
            colorAdjust.setSaturation(0.60);
            colorAdjust.setBrightness(0.12);

        }

        Bloom bloom = new Bloom(0.2);

        Blend blend1 = new Blend();
        blend1.setTopInput(colorAdjust);

        Blend finalBlend = new Blend();
        finalBlend.setTopInput(blend1);
        finalBlend.setBottomInput(bloom);

        gc.setEffect(finalBlend);

        gc.drawImage(image, pos.x() - getWidth() / 2, pos.y() - getHeight() / 2,
                getWidth(), getHeight());

        gc.restore();
    }

    /*Note: the fish's actual position refers to a center-left point in the object.
    We need to calculate the boundingbox starting from that position.
     */
    @Override
    public BoundingBox getBoundingBox(Position position) {

        Position topLeft = new Position(
                position.x() - getWidth() / 2,
                position.y() - getHeight() / 2
        );

        Position topRight = new Position(
                position.x() + getWidth() / 2,
                position.y() - getHeight() / 2
        );

        Position bottomRight = new Position(
                position.x() + getWidth() / 2,
                position.y() + getHeight() / 2
        );

        Position bottomLeft = new Position(
                position.x() - getWidth() / 2,
                position.y() + getHeight() / 2
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }
}
