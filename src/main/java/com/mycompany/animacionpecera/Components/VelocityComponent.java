/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.Components;

/**
 *
 * @author carol
 */
public class VelocityComponent extends Component{
    
    public double velX;
    public double velY;
    
    public VelocityComponent(double velX, double velY) {
        this.velX = velX;
        this.velY = velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }
    
}
