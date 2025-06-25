/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/*
 * BoundingBox represents the area occupied by a fish.
 * It calculates the fish's real position and dimensions
 * based on its size and position.

 * Note: the fish's actual position (0,0) refers to the center-left point of the object.
 * Therefore, the bounding box must account for displacement toward the top, right, and bottom
 * to fully cover the fish's visual area.
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
        return position.x + getWidth();
    }

    // Top boundary, we substract getHeight/2 to get a position on top of the center of the object
    public double getTop() {
        return position.y - (getHeight() / 2);
    }

    // Bottom boundary, we get the bottom adding the height scaled with size
    public double getBottom() {
        return position.y + getHeight();
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
