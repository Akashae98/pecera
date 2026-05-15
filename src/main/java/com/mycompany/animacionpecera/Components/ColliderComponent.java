/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.Components;

/**
 *
 * @author carol
 */
public abstract class ColliderComponent extends Component {

    public abstract double getLeft(Transform transform);

    public abstract double getRight(Transform transform);

    public abstract double getTop(Transform transform);

    public abstract double getBottom(Transform transform);

    //Collisions with colliders...
    public boolean intersects(ColliderComponent other, Transform thisTransform, Transform otherTransform) {
        return this.getRight(thisTransform) > other.getLeft(otherTransform)
                && this.getLeft(thisTransform) < other.getRight(otherTransform)
                && this.getBottom(thisTransform) > other.getTop(otherTransform)
                && this.getTop(thisTransform) < other.getBottom(otherTransform);
    }


}
