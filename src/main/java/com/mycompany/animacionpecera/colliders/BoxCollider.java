package com.mycompany.animacionpecera.colliders;

import com.mycompany.animacionpecera.Component;

public class BoxCollider extends Component {
    private final double width;
    private final double height;

    public BoxCollider(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
