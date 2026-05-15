/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import com.mycompany.animacionpecera.Components.Component;
import java.util.HashMap;

/**
 *
 * @author carol
 */
public class Entity {

    //hashmap of components:
    //the key is the type of component and the value the instance.
    private final HashMap<Class<? extends Component>, Component> components;

    public Entity() {
        this.components = new HashMap<>();
    }

    //Add component: with <> the C type has to extend Component
    public <C extends Component> void add(C component) {
        components.put(component.getClass(), component);
    }
    
    //Search component
    public <C extends Component> C getComponent(Class<C> componentClass) {
        if (components.containsKey(componentClass)) {
            return (C) components.get(componentClass); // cast necessary
        }
        return null;
    }
    
    //has component
    public <C extends Component> boolean hasComponent(Class<C> componentClass) {
        return components.containsKey(componentClass);
    }

    // Remove component
    public <C extends Component> void remove(Class<C> componentClass) {
        components.remove(componentClass);
    }

}
