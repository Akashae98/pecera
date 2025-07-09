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

    //limits of the aquarium
    protected int width = FishTank.CANVAS_WIDTH;
    protected int height = FishTank.CANVAS_HEIGH;

    public abstract Position nextPosition(SceneObject current);
    
    //esto está de momento aquí:
    public Position teletransport(BoundingBox box, Position position) {
        double fishWidth = (box.getTopRight().x - box.getTopLeft().x);
        double fishHeight = (box.getBottomLeft().y - box.getTopLeft().y);

        double x = position.x;
        double y = position.y;

        if (box.getTopLeft().x < -1) {
            x = fishWidth / 3;  // Teletransports
        } else if (box.getBottomRight().x > width + 1) {
            x = width - fishWidth;
        }

        if (box.getTopLeft().y < -1) {
            y = fishHeight;
        } else if (box.getBottomRight().y > height + 1) {
            y = height - fishHeight;
        }

        return new Position(x, y);
    }

}
