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
    
    private Image image;

    public AnimationCoralFish(double size) {
        super(size);
    }
    
    @Override
    public BoundingBox getBoundingBox(Position position) {
        //the base of the bodyfish 
        double bodyWidth = 36 * size;
        //the height of the bodyfish
        double bodyHeight = 20 * size;
        //the height of the fishtail
        double tailHeight = 9 * size;

        Position topLeft = new Position(
                position.x,
                position.y - (bodyHeight / 2)
        );

        Position topRight = new Position(
                position.x + bodyWidth,
                position.y - (bodyHeight / 2)
        );

        Position bottomRight = new Position(
                position.x + bodyWidth,
                position.y + (bodyHeight / 2) + tailHeight
        );

        Position bottomLeft = new Position(
                position.x,
                position.y + (bodyHeight / 2) + tailHeight
        );

        return new BoundingBox(topLeft, topRight, bottomRight, bottomLeft);
    }
    @Override
    public void draw(GraphicsContext gc, double x, double y) {
         chargeDraw();
            double ancho = image.getWidth() * size;
            double alto = image.getHeight() * size;
            gc.drawImage(image, x, y, ancho, alto);
    }
    
    
     private void chargeDraw() {
        try {
            this.image = new Image(getClass().getResourceAsStream("/Images/sketchPezCoral.png"));
        } catch (Exception e) {
            System.err.println("Error al cargar imagen de pez coral: " + e.getMessage());
        }
    }
     
     
}
