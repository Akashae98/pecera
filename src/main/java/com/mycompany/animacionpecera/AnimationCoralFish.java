/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author User
 */
public class AnimationCoralFish extends Animation {

    public AnimationCoralFish(double size) {
        super(size);
    }

    @Override
    public BoundingBox getBoundingBox(Position position) {
        //pendiente
        Position topLeft = new Position(0,0);

        Position topRight = new Position(0,0);

        Position bottomRight = new Position(0,0);

        Position bottomLeft = new Position(0,0);

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void draw(GraphicsContext gc, Position pos) {
        Image image = chargeDraw();
        double ancho = image.getWidth() * size;
        double alto = image.getHeight() * size;
        gc.drawImage(image, pos.x, pos.y, ancho, alto);
    }

    private Image chargeDraw() {
        try {
            Image image = new Image(getClass().getResourceAsStream("/Images/sketchPezCoral.png"));
            return image;
        } catch (Exception e) {
            System.err.println("Error al cargar imagen de pez coral: " + e.getMessage());
        }
        return null;
    }

}
