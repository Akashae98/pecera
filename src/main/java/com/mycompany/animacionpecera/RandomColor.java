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
    private static final Random random = MainScene.random;

    public RandomColor() {
        this.color = colorGenerator();
    }

    public Color getColor() {
        return color;
    }

    private Color colorGenerator() {
        int selector = random.nextInt(6);

        switch (selector) {
            case 0:
                return generateTurquoise();

            case 1:
                return generateHotPink();

            case 2:
                return generateLavender();

            case 3:
                return generatePastelPink();

            case 4:
                return generateCoral();

            default:
                return generateCoral();
        }
    }

    private Color generateTurquoise() {
        return Color.hsb(
                170 + random.nextInt(30), // Hue: 170-200°
                0.7 + random.nextDouble() * 0.2, // Saturación: 0.7-0.9
                0.8 + random.nextDouble() * 0.15 // Brillo: 0.8-0.95
        );
    }

    private Color generateHotPink() {
        return Color.hsb(
                320 + random.nextInt(20), // Hue: 320-340°
                0.85 + random.nextDouble() * 0.15, // Saturación: 0.85-1.0
                0.9 + random.nextDouble() * 0.1 // Brillo: 0.9-1.0
        );
    }

    private Color generateLavender() {
        return Color.hsb(
                270 + random.nextInt(20), // Hue: 270-290°
                0.5 + random.nextDouble() * 0.25, // Saturación: 0.5-0.75
                0.85 + random.nextDouble() * 0.15 // Brillo: 0.85-1.0
        );
    }

    private Color generatePastelPink() {
        int hue = (340 + random.nextInt(40)) % 360; // → 340-359 o 0-19
        return Color.hsb(
                hue,
                0.3 + random.nextDouble() * 0.2, // Saturación: 0.3-0.5
                0.95 + random.nextDouble() * 0.05 // Brillo: 0.95-1.0
        );
    }

    private Color generateCoral() {
        return Color.hsb(
                10 + random.nextInt(15), // Hue: 10-25°
                0.8 + random.nextDouble() * 0.15, // Saturación: 0.8-0.95
                0.9 + random.nextDouble() * 0.08 // Brillo: 0.9-0.98
        );
    }

}
