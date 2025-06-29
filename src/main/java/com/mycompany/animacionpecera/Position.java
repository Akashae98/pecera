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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    //to add or rest in a postion
    public Position displacement(double dx, double dy) {
        return new Position(this.x + dx, this.y + dy);
    }
}
