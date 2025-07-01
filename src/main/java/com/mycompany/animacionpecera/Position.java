package com.mycompany.animacionpecera;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author User
 */
public class Position {

    public final double x;
    public final double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //to add or rest in a position
    public Position displacement(double dx, double dy) {
        return new Position(this.x + dx, this.y + dy);
    }
}
