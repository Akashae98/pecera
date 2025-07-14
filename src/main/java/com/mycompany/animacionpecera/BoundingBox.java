/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 * Boundingbox represents a rectangular box defined by four corner positions.
 * Can be used to check if points are contained within the box and to get the
 * boundaries of the box.
 */
public record BoundingBox (Position topLeft, Position topRight,
            Position bottomRight, Position bottomLeft) {

  

    // Checks if a given point is inside this bounding box.
    public boolean isInside(Position point) {
        return point.x() >= topLeft.x() && point.x() <= bottomRight.x()
                && point.y() >= topLeft.y() && point.y() <= bottomRight.y();
    }
}
