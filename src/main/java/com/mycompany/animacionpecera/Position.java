package com.mycompany.animacionpecera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author User
 */
public record Position(double x, double y) {

    //to add or rest in a position
    public Position displacement(double dx, double dy) {
        return new Position(this.x + dx, this.y + dy);
    }
    public Position displacement(Direction direction) {
        return new Position(this.x + direction.dx(), this.y + direction.dy());
    }
}
