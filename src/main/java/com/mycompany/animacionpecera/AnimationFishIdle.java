/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Effect;
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

        double hue = color.getHue(); // de 0 a 360
        // Primero detectamos por RGB para máxima precisión
        if (isTurquoiseByRGB(color)) {
            imageName = "pink_fish.png";
        } else if (isPinkByRGB(color)) {
            imageName = "pink_fish.png";
        } // Luego por HSB para clasificación general
        else {
            if (isPinkHue(hue)) {
                imageName = "pink_fish.png";
            } else if (isPurpleHue(hue)) {
                imageName = "purple_fish.png";
            } else {
                imageName = "red_fish.png";
            }
        }

        try {
            return new Image(getClass().getResourceAsStream(imagePath + imageName));
        } catch (Exception e) {
            System.err.println("We didn`t find " + imageName + ", using grey image...");
            return new Image(getClass().getResourceAsStream(imagePath + "gray_fish.png"));
        }
    }

    private boolean isTurquoiseByRGB(Color color) {
        return (color.getRed() * 255 < 150)
                && // poco rojo
                (color.getGreen() * 255 > 140)
                && // mucho verde
                (color.getBlue() * 255 > 170);    // mucho azul
    }

    private boolean isPinkByRGB(Color color) {
        return (color.getRed() * 255 > 200)
                && // mucho rojo
                (color.getGreen() * 255 < 120)
                && // poco verde
                (color.getBlue() * 255 > 140)
                && (color.getRed() > color.getBlue());    // bastante azul
    }

    private boolean isPinkHue(double hue) {
        return hue >= 330 || hue <= 20; // Rango correcto para rosas
    }

    private boolean isPurpleHue(double hue) {
        return hue >= 260 && hue < 330; // Rango para púrpuras/lilas
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
        ColorAdjust colorAdjust = new ColorAdjust();
        Effect finalEffect = colorAdjust; // Empezamos con el ajuste básico

        if (isPinkByRGB(color)) {
            colorAdjust.setHue(3);
            colorAdjust.setSaturation(0.3);

            Glow glow = new Glow();
            glow.setLevel(0.2);

            Bloom bloom = new Bloom();
            bloom.setThreshold(0.2);

            // Combinación correcta de 3 efectos:
            Blend blend1 = new Blend();
            blend1.setTopInput(colorAdjust);
            blend1.setBottomInput(glow);

            Blend finalBlend = new Blend();
            finalBlend.setTopInput(blend1);
            finalBlend.setBottomInput(bloom);

            gc.setEffect(finalBlend); // Aplicamos la combinación completa
            gc.fillText("c", pos.x(), pos.y());

        } else if (isTurquoiseByRGB(color)) {
            colorAdjust.setHue(hue / 20);
            colorAdjust.setSaturation(0.3);
            colorAdjust.setBrightness(0.1);
            finalEffect = colorAdjust;

        } else {
            
            colorAdjust.setHue(hue/30);
            colorAdjust.setSaturation(0.2);
            finalEffect = colorAdjust;
        }

        gc.setEffect(finalEffect);

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
