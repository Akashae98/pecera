/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 Esta clase representa una burbuja que sube por la pecera.
 Controla su posición y dibujo, simulando burbujas pequeñas de aire 
 en movimiento ascendente.
 */
public class Burbuja {
      double x, y, radio, velocidad;
      int ANCHO_PECERA = 600;
      int ALTO_PECERA = 400;

    public Burbuja(double x, double y) {
        this.x = x;
        this.y = y;
        this.radio = 3 + Math.random() * 3;     /*radio de la burbuja utilizando
        Math.random para darle variación.*/
        this.velocidad = 0.5 + Math.random();   /*velocidad de la subida con 
        variabilidad usando random.*/
    }

    public void mover() {
        //la y va decreciemdo empezando desde abajo en 400, asi hacemos que las burbujas suban.
        y -= velocidad;
        if (y + radio < 0) { // si salen por arriba, las burbujas vuelven al fondo
            y = ALTO_PECERA + Math.random() * 50;
        // 400 es el limite de el eje y de abajo de la pecera, le he sumado un numero aleatorio
        //para que algunas burbujas tarden más en subir.
       /* } else{
            x = Math.random() * ANCHO_PECERA; //si le añadimos este else se genera una corriente
       */ }
    }

    public void dibujar(GraphicsContext gc) {
        gc.setFill(Color.rgb(255, 255, 255, 0.3)); //color blanco semitransparente
        gc.fillOval(x, y, radio, radio);//dibujamos interior del ovalo
        gc.setStroke(Color.rgb(255, 255, 255, 0.5));// color del borde más opaco
        gc.strokeOval(x, y, radio, radio);//dibujamos exterior del ovalo
    }

}
