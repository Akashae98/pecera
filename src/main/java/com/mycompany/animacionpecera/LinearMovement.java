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

    public LinearMovement(Direction direction) {
        super(direction);

    }

    @Override
    public Position nextPosition(Position current, BoundingBox box) {
        double newY = current.y + direction.dy();
        // if bubble exceeds the top, then goes to the bottom + random numbeer.
        if (newY + box.getTopLeft().x < 0) {
            newY = height + Math.random() * 50;
        }
        return new Position(current.x, newY);
    }

}
