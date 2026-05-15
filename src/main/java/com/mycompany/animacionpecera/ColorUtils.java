package com.mycompany.animacionpecera;

import javafx.scene.effect.ColorAdjust;



public class ColorUtils {

    // Constants for hue type identification
    public static final String HUE_TURQUOISE = "TURQUOISE";
    public static final String HUE_PINK = "PINK";
    public static final String HUE_PURPLE = "PURPLE";
    public static final String HUE_CORAL_GOLDEN = "CORAL_GOLDEN";


    // Determine type of hue color
    public static String getHueType(double hue) {
        if (isTurquoiseHue(hue)) {
            return HUE_TURQUOISE;
        } else if (isPinkHue(hue)) {
            return HUE_PINK;
        } else if (isPurpleHue(hue)) {
            return HUE_PURPLE;
        } else {
            return HUE_CORAL_GOLDEN;
        }
    }

    // Check if hue is in pink range
    public static boolean isPinkHue(double hue) {
        return hue >= 170 && hue <= 200;
    }

    // Check if hue is in turquoise range
    public static boolean isTurquoiseHue(double hue) {
        return hue >= 330 || hue <= 20;
    }

    // Check if hue is in purple range
    public static boolean isPurpleHue(double hue) {
        return hue >= 260 && hue < 330;
    }

    // Create a ColorAdjust effect based on hue type and random values
    public static ColorAdjust createColorEffect(double hue, double randomHue) {
        ColorAdjust colorAdjust = new ColorAdjust();

        if (isTurquoiseHue(hue)) {
            // turns the pink image to blue colors
            double normalizedHue = (hue - 180) / 180.0;
            colorAdjust.setHue(normalizedHue);
            colorAdjust.setSaturation(0.45);
            colorAdjust.setBrightness(0.2);

        } else if (isPinkHue(hue)) {
            // adjusted pink
            colorAdjust.setHue(randomHue * 0.3 - 0.1);
            colorAdjust.setSaturation(0.45);
            colorAdjust.setBrightness(0.35);

        } else if (isPurpleHue(hue)) {
            // adjusted purples and blues
            colorAdjust.setHue(-(randomHue) * 0.5);
            colorAdjust.setSaturation(0.40);
            colorAdjust.setBrightness(0.30);

        } else {
            // Coral to golden colors
            colorAdjust.setHue(randomHue * 0.12);
            colorAdjust.setSaturation(0.55);
            colorAdjust.setBrightness(0.15);
        }
        return colorAdjust;
    }

}

