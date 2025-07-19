/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author carol
 */
public class AnimationFishIdle extends Animation {

    private Color color;
    private Image image;
    private ColorAdjust colorAdjust; // Efecto para cambiar el color

    public AnimationFishIdle(double size, Color color) {
        super(size);
        this.color = color;
        this.image = new Image(getClass().getResourceAsStream("/images/gray_fish.png"));
        
        this.colorAdjust = new ColorAdjust();
        updateColorEffect(); // Aplicar el color inicial
    }

    private void updateColorEffect() {
        // Convertir el Color a valores HSV para el tono (hue)
        double hue = calculateHueFromColor(this.color);
        colorAdjust.setHue(hue);
        colorAdjust.setSaturation(0.9); 
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
            if (max == r) hue = (g - b) / delta + (g < b ? 6 : 0);
            else if (max == g) hue = (b - r) / delta + 2;
            else hue = (r - g) / delta + 4;
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
        // Guardar el estado actual del GraphicsContext
        gc.save();
        gc.scale(size, size);
        gc.setEffect(colorAdjust);

        gc.drawImage(image, pos.x() - getWidth() / 2, pos.y() - getHeight() / 2,
                getWidth(), getHeight());
        
        // Restaurar el estado del GraphicsContext (para no afectar otros dibujos)
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