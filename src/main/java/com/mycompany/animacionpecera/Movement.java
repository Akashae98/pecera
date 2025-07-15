/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public abstract class Movement {
    
    protected double deltaTime; 

    public void update(double deltaTime) {
        this.deltaTime = deltaTime;  
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public abstract Position nextPosition(SceneObject current);

}
