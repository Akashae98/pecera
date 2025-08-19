/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 * Records are immutable data classes. Their fields are implicitly final and
 * private. Java automatically generates: - A canonical constructor with all
 * fields - Getter methods - equals(), hashCode(), and toString() methods
 *
 * You can define your own constructor for validation or custom logic!
 *
 * @author carol
 */
public record Direction(double dx, double dy) {

    public Direction invertX() {
        return new Direction(-dx, dy);
    }

    public Direction invertY() {
        return new Direction(dx, -dy);
    }

    public Direction multiply(double multiplier) {
        return new Direction(dx * multiplier, dy * multiplier);
    }

}
