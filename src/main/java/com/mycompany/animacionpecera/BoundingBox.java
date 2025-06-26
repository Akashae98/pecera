/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/*
 * BoundingBox represents the area occupied by a fish.
 * It calculates the fish's real position and dimensions
 * based on its size and position.
*/
public class BoundingBox {

    private double left;
    private double top;
    private double right;
    private double bottom;

    public BoundingBox(double left, double top, double right, double bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public double getLeft() {
        return left;
    }

    public double getTop() {
        return top;
    }

    public double getRight() {
        return right;
    }

    public double getBottom() {
        return bottom;
    }

    public boolean isInside(Position point) {
        return point.x >= left && point.x <= right &&
               point.y >= top && point.y <= bottom;
    }
}
