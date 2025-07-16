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
    public Position nextPosition(SceneObject current, double deltaTime) {
        Position currentPos = current.getPosition();
        Direction scaledDirection = new Direction(direction.dx() * deltaTime,
                direction.dy() * deltaTime);
        return currentPos.displacement(scaledDirection);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

}
