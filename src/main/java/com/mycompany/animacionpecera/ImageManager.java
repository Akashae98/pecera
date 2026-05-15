/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

/**
 *
 * @author carol
 */
public class ImageManager {

    private final Map<String, Image> images = new HashMap<>();
    private static ImageManager instance;

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public void loadImage(String key, String path) {
        if (!images.containsKey(key)) {
            try {
                Image image = new Image(path);
                images.put(key, image);
            } catch (Exception e) {
                System.out.println("Error loading image: " + path + ", " + e.getMessage());
            }
        }
    }

    public Image getImage(String key) {
        return images.get(key);
    }

    public boolean hasImage(String key) {
        return images.containsKey(key);
    }
    
    public double getWidth (String key){
       return getImage(key).getWidth(); 
    }
    
    public double getHeight (String key){
       return getImage(key).getHeight();
    }
}
