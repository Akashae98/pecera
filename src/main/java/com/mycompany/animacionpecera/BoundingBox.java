/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/*
 * BoundingBox represents the area occupied by a fish.
 * It calculates the fish's real position and dimensions
 * based on its size and position including margins.

 Note that the actual position of the fish,its center or its position (0,0)
 its on the center left of the object, so we must add a boundingbox that takes a
 displacement to the top, righ and bottom.
 */
public class BoundingBox {

    private Position position;
    private double baseWidth;      // Base width 
    private double baseHeight;     // Base height
    private double size;

    public BoundingBox(Position position, double baseWidth, double baseHeight,
            double size) {
        this.position = position;
        this.baseWidth = baseWidth;
        this.baseHeight = baseHeight;
        this.size = size;

    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    // Scaled width 
    public double getWidth() {
        return baseWidth * size;
    }

    // Scaled height 
    public double getHeight() {
        return baseHeight * size;
    }

    // Left boundary of the fish (used for collision detection)
    public double getLeft() {
        return position.x;
    }

    // Right boundary 
    public double getRight() {
        return position.x + (getWidth() * size);
    }

    // Top boundary, we substract offsetY to get a position on top of the center of the object
    public double getTop() {
        return position.y - ((getHeight() / 2) * size);
    }

    // Bottom boundary, adding the height scaled with size
    public double getBottom() {
        return position.y + (baseHeight * size);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
