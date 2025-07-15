/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

/**
 *
 * @author carol
 */
public class LinearMovement extends Movement {

    private Direction direction;

    public LinearMovement(Direction direction) {
        this.direction = direction;

    }

    @Override
    public Position nextPosition(SceneObject current) {
        Position currentPos = current.getPosition();
        double deltaTime = current.deltaTime;
        direction.multiply(deltaTime);
        return currentPos.displacement(direction);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
