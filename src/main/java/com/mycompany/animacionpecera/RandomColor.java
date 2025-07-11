/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.Random;
import javafx.scene.paint.Color;
/**
 *
 * @author User
 */
public class RandomColor {
    
    public Color color;
    private static final Random random = FishTank.getRandom();

    public RandomColor() {
        this.color = colorGenerator();
    }
    
    public Color getColor() {
        return color;
    }
    
    private Color colorGenerator() {
        int selector = random.nextInt(4);//selects between 0, 1 o 2

        switch (selector) {
            case 0: //Blue colors
                return Color.rgb( //portions of the colors:
                        50 + random.nextInt(100), // red: 50–149
                        150 + random.nextInt(70), // green: 150–219
                        180 + random.nextInt(75) // blue: 180–254
                );
            case 1: //Pink colors
                return Color.rgb(
                        200 + random.nextInt(55), // red: 200–254
                        100 + random.nextInt(80), // green: 100–179
                        140 + random.nextInt(60) // blue: 140–199
                );
            case 2: //Purple colors
                return Color.rgb(
                        150 + random.nextInt(50), // red: 150–199
                        120 + random.nextInt(60), // green: 120–179
                        180 + random.nextInt(40) // blue: 180–219
                );
            default:
                return Color.CORAL;
        }
    } 
   
}
