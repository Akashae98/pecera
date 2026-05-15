/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.Components;

/**
 *
 * @author User
 */
public class CircleCollider extends ColliderComponent {

    private double diameter;

    public CircleCollider(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius(Transform transform) {
        return (diameter * transform.getScaleX()) / 2; // Scaled radius
    }

    @Override
    public double getLeft(Transform transform) {
        return transform.getX() - getRadius(transform);
    }

    @Override
    public double getRight(Transform transform) {
        return transform.getX() + getRadius(transform);
    }

    @Override
    public double getTop(Transform transform) {
        return transform.getY() - getRadius(transform);
    }

    @Override
    public double getBottom(Transform transform) {
        return transform.getY() + getRadius(transform);
    }

}
