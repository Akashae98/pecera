/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
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
}
