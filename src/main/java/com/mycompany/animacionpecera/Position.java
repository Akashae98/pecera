package com.mycompany.animacionpecera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Records are immutable data classes. Their fields are implicitly final and private.
 * Java automatically generates:
 * - A canonical constructor with all fields
 * - Getter methods
 * - equals(), hashCode(), and toString() methods
 *
 * You can define your own constructor for validation or custom logic!
 */
public record Position(double x, double y) {

    //to add or rest in a position
    public Position displacement(Direction direct) {
        return new Position(this.x + direct.dx(), y + direct.dy());
    }

}
