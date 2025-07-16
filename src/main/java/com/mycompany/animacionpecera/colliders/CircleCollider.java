package com.mycompany.animacionpecera.colliders;

import com.mycompany.animacionpecera.Component;

public class CircleCollider extends Component {
    private final double radius;

    public CircleCollider(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
