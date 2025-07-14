package com.mycompany.animacionpecera;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    private static final Map<String, Image> CACHE = new HashMap<>();

    public static Image loadImage(String path, boolean backgroundLoading) {
        return CACHE.computeIfAbsent(path, p ->
                new Image(p, backgroundLoading) // 'true' para carga en segundo plano
        );
    }
}
