/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
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

    public AnimationFishIdle(double size, Color color) {
        super(size);
        this.color = color;
        this.image = loadFishImageByColor(color);
    }

    private Image loadFishImageByColor(Color color) {
        String imagePath = "/Images/";

        // Determina el nombre de la imagen basado en el color
        if (isInRange(color, 50, 149, 150, 219, 180, 254)) { // Azules
            this.imageName = "red_fish.png";
        } else if (isInRange(color, 200, 254, 100, 179, 140, 199)) { // Rosados
            this.imageName = "pink_fish.png";
        } else if (isInRange(color, 150, 199, 120, 179, 180, 219)) { // Morados
            this.imageName = "purple_fish.png";
        } else {
            this.imageName = "red_fish.png"; // Imagen genérica para otros colores
        }

        try {
            return new Image(getClass().getResourceAsStream(imagePath + imageName));
        } catch (Exception e) {
            System.err.println("No se encontró " + imageName + ", usando imagen gris.");
            return new Image(getClass().getResourceAsStream(imagePath + "gray_fish.png"));
        }
    }

    private boolean isInRange(Color color, int rMin, int rMax, int gMin, int gMax, int bMin, int bMax) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return (r >= rMin && r <= rMax) && (g >= gMin && g <= gMax) && (b >= bMin && b <= bMax);
    }

    private double calculateHueFromColor(Color color) {
        // JavaFX no tiene un método directo para obtener el hue, así que lo calculamos
        double r = color.getRed();
        double g = color.getGreen();
        double b = color.getBlue();

        double max = Math.max(r, Math.max(g, b));
        double min = Math.min(r, Math.min(g, b));
        double delta = max - min;

        double hue = 0;
        if (delta != 0) {
            if (max == r) {
                hue = (g - b) / delta + (g < b ? 6 : 0);
            } else if (max == g) {
                hue = (b - r) / delta + 2;
            } else {
                hue = (r - g) / delta + 4;
            }
            hue /= 6;
        }
        return hue - 0.5;
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
