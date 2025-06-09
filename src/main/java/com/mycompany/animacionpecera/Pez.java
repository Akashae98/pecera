/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.animacionpecera;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 Esta clase va a permitir crear objetos pez con posición, velocidad y color random.
 * Los metodos va a controlar su movimiento dentro de los límites de la pecera. 
 * Permite dibujarlos con color aleatorio, y en funcion de random.nextboolean esocgemos
 * si el pez tiene una aleta adicional o escamas dentro del metodo dibujar.
 * 
 */
public class Pez {
    private double x, y;      // Posición del pez
    private double dx, dy;    // Dirección del pez (velocidad)
    private final Color color;// Color del pez
    private static final Random random = new Random(); //Generador aleatorio
    private final boolean tieneAleta; //define si pez tiene aleta o escamas

    public Pez(double x, double y) {
        this.x = x; //posicion
        this.y = y;
        this.dx = Math.random() * 2 - 1; // Movimiento aleatorio entre -1 y 1 en eje x
        this.dy = Math.random() * 2 - 1;// Movimiento aleatorio entre -1 y 1 en eje y
        this.color = generarColor(); // Se asigna un color aleatorio 
        this.tieneAleta = random.nextBoolean(); // decide si pez tiene aleta dorsal o no 
        
    }
 // Método para generar colores azules, rosas, lilas o default: coral
    private Color generarColor() {
        Random rand = new Random();
        int selector = rand.nextInt(3);// Selecciona entre 0, 1 o 2
        
        switch (selector) {
       case 0: // Tonos azulados
                return Color.rgb(
                    50 + rand.nextInt(100),   // Rojo: 50–149
                    150 + rand.nextInt(70),   // Verde: 150–219
                    180 + rand.nextInt(75)    // Azul: 180–254
                );
            case 1: // Tonos rosados
                return Color.rgb(
                    200 + rand.nextInt(55),   // Rojo: 200–254
                    100 + rand.nextInt(80),   // Verde: 100–179
                    140 + rand.nextInt(60)    // Azul: 140–199
                );
            case 2: // Tonos lilas
                return Color.rgb(
                    150 + rand.nextInt(50),   // Rojo: 150–199
                    120 + rand.nextInt(60),   // Verde: 120–179
                    180 + rand.nextInt(40)    // Azul: 180–219
                );
        default:
            return Color.CORAL;
        }
    }

    // Método mover el pez en los limites de la pecera
    public void mover(int width, int height) {
        x += dx; // Avanza en horizontal
        y += dy; // Avanza en vertical

        // Rebote en los bordes: si se sale del canvas, invierte la dirección
        //multiplicando * -1 
        if (x < 0 || x > (width)) {
            dx *= -1;
        }
        if (y < 0 || y > height) {
            dy *= -1;
        }
    }


    // Método 2: Dibujar el pez (triángulo)
    public void dibujar(GraphicsContext gc) {
        gc.setFill(color);
        int ancho = 36;
        int alto = 22;
        
        // Cuerpo (óvalo alargado)
        gc.fillOval(x - 4, y - 9, ancho, alto); 

        // Cola (triángulo)
        double[] colaX = {x + 30, x + 45, x + 30};
        double[] colaY = {y, y + 10, y + 20};
        gc.fillPolygon(colaX, colaY, 3);

        // Ojo (punto blanco)
        gc.setFill(Color.WHITE);
        gc.fillOval(x + 4, y - 2, 5, 5);//posicion del ojo
        
        //Escamas o aleta, si tiene aleta dorsal se dibuja una línea superior
        if (tieneAleta){
            // Aleta dorsal 
            gc.setStroke(color.darker());//del color del pez mas ocuro
            gc.setLineWidth(2); // Grosor 
            gc.strokeLine(x + 10, y - 8, x + 20, y - 17);//linea
        }
        else{
             // Si no tiene aleta, se dibujan escamas (óvalos pequeños)
            gc.setFill(color.brighter()); // Color más claro
            double escalaEscama = 0.7;    // Ajuste de tamaño de escama
            int filasEscamas = 3;         // Número de filas de escamas
            
             // Bucle para dibujar filas de escamas a lo largo del cuerpo
            for (int fila = 0; fila < filasEscamas; fila++) {
                for (double i = 1; i < (ancho-6); i += (ancho-6) / 6) { // 6 escamas por fila
                    double escamaX = x + i;
                    double escamaY = y - (alto-2)/5 + (fila * ((alto-2) / filasEscamas));
                    gc.fillOval(escamaX, escamaY, 6 * escalaEscama, 3 * escalaEscama);
                }
            }
        }
    }
}