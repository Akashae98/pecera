package com.mycompany.animacionpecera.Components;
import com.mycompany.animacionpecera.ColorUtils;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;


public class ColorSprite extends Component{

    private final String hueType;
    private final ColorAdjust colorEffect;

    public ColorSprite(Color color, double random) {
        double hue = color.getHue();
        this.hueType = ColorUtils.getHueType(hue);
        this.colorEffect = ColorUtils.createColorEffect(hue, random);
    }

    public ColorAdjust getColorEffect() {
        return colorEffect;
    }

    public String getHueType() {
        return hueType;
    }

}
