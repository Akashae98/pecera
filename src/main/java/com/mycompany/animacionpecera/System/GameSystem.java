/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera.System;

import com.mycompany.animacionpecera.Entity;
import java.util.List;

/**
 *
 * @author carol
 */
public abstract class GameSystem {
    
    public abstract void update (List<Entity> entities, double deltaTime);
   
    
}
