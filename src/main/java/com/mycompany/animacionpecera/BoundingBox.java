/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public class BoundingBox {

    private Position position; // posición real del pez
    private double baseWidth;
    private double baseHeight;
    private double size;

    private double offsetX; // margen izquierdo
    private double offsetY; // margen superior

    public BoundingBox(Position position, double baseWidth, double baseHeight, double size, double offsetX, double offsetY) {
        this.position = position;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.size = size;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public double getX() {
        return position.x + offsetX * size;
    }

    public double getY() {
        return position.y + offsetY * size;
    }

    public double getWidth() {
        return baseWidth * size;
    }

    public double getHeight() {
        return baseHeight * size;
    }

    public double getLeft() {
        return getX();
    }

    public double getRight() {
        return getX() + getWidth();
    }

   public double getTop() {
        return position.y - offsetY * size;  
    }

    public double getBottom() {
        return position.y + baseHeight * size; 
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }
}
