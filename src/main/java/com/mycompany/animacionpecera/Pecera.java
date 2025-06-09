/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 Esta clase gestiona una lista de objetos Pez.
 Permite agregar peces y actualizar su movimiento y dibujo en cada frame de la animación.
 */
public class Pecera {
    // Lista dinámica para almacenar todos los peces que se añadan a la pecera
    private ArrayList<Pez> peces;

    // Constructor: inicializa la lista de peces vacía
    public Pecera() {
        this.peces = new ArrayList<>();
    }

    // Método para agregar un pez nuevo en una posición dada (x, y)
    public void agregarPez(double x, double y) {
        peces.add(new Pez(x, y)); // Crea un nuevo pez y lo añade a la lista
    }

    // Método para animar todos los peces:
    // actualiza la posicion con move y los dibuja en el lienzo generando movimiento
    public void animar(GraphicsContext gc, int width, int height) {
        // Este método se llama en cada frame de la animación

        // Recorre todos los peces existentes
        for (Pez pez : peces) {
            pez.mover(width, height);   // Actualiza la posición del pez
            pez.dibujar(gc);            // Dibuja el pez en su nueva posición
        }
    }
}
