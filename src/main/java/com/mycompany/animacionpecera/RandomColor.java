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
    int selector = random.nextInt(5);
        
        switch (selector) {
            case 0: // Azul turquesa brillante
                return generateTurquoise();
                
            case 1: // Rosa intenso
                return generateHotPink();
                
            case 2: // Lila/púrpura
                return generateLavender();
                
            case 3: // Rosa pastel
                return generatePastelPink();
                
            case 4: // Coral/anaranjado
                return generateCoral();
                
            default:
                return generateCoral(); // Por seguridad
        }
    }
    // Caso 1: Azules turquesas (hue 170-200°)
    private Color generateTurquoise() {
        return Color.hsb(
            170 + random.nextInt(30),   // Hue: 170-200°
            0.7 + random.nextDouble() * 0.2, // Saturación: 0.7-0.9
            0.8 + random.nextDouble() * 0.15 // Brillo: 0.8-0.95
        );
    }

    // Caso 2: Rosas intensos (hue 330-360°)
    private Color generateHotPink() {
        return Color.hsb(
            330 + random.nextInt(30),   // Hue: 330-360°
            0.8 + random.nextDouble() * 0.15, // Saturación: 0.8-0.95
            0.9 + random.nextDouble() * 0.1  // Brillo: 0.9-1.0
        );
    }

    // Caso 3: Lilas/púrpuras (hue 270-310°)
    private Color generateLavender() {
        return Color.hsb(
            270 + random.nextInt(40),   // Hue: 270-310°
            0.6 + random.nextDouble() * 0.25, // Saturación: 0.6-0.85
            0.85 + random.nextDouble() * 0.1 // Brillo: 0.85-0.95
        );
    }

    // Caso 4: Rosas pastel (hue 340-20°)
    private Color generatePastelPink() {
        return Color.hsb(
            340 + random.nextInt(40),   // Hue: 340-20° (se envuelve)
            0.5 + random.nextDouble() * 0.2, // Saturación: 0.5-0.7
            0.95 + random.nextDouble() * 0.05 // Brillo: 0.95-1.0
        );
    }

    // Caso 5: Coral/anaranjado (hue 10-25°)
    private Color generateCoral() {
        return Color.hsb(
            10 + random.nextInt(15),    // Hue: 10-25°
            0.8 + random.nextDouble() * 0.15, // Saturación: 0.8-0.95
            0.9 + random.nextDouble() * 0.08 // Brillo: 0.9-0.98
        );
    }

}
