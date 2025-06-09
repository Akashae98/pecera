/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.animacionpecera;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

/**
 Clase principal que crea la ventana y el lienzo donde se dibuja la pecera.
 Controla la animación de los peces y las burbujas, y permite añadir peces 
 con clics del ratón.
 */
public class AnimacionPecera extends Application {
    private Pecera pecera;// Objeto que gestiona los peces
    private GraphicsContext gc; // Contexto gráfico para dibujar en el Canvas
    private List<Burbuja> burbujas = new ArrayList<>(); /* Lista de burbujas */


    @Override
    public void start(Stage stage) {
        // Creamos un Canvas de 600x400 píxeles para dibujar en la pecera
        Canvas canvas = new Canvas(600, 400);
        gc = canvas.getGraphicsContext2D(); //crea el contexto gráfico del Canvas
        pecera = new Pecera(); //instanciamos pecera
        
        // Agregamos 5 peces al inicio en zonas aleatorias
        for (int i = 0; i < 5; i++) {
            pecera.agregarPez(
                Math.random() * canvas.getWidth(),
                Math.random() * canvas.getHeight()
            );
        }
        /* Creamos 30 burbujas aleatorias, las burbujas se crean al fondo de la pecera
        pasandole el height al parametro de la y.*/
        //Math.random genera un numero entre 0.0 sin llegar a 1.0.
        for (int i = 0; i < 30; i++) {
        burbujas.add(new Burbuja(Math.random() * canvas.getWidth(), 
                             Math.random() * canvas.getHeight()));
        }
        // Creamos animación
        new AnimationTimer() {
            @Override
            public void handle(long now) {
           // Gradiente del fondo que simula el agua 
            LinearGradient fondo = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(70, 130, 180)),   // Azul mas oscuro (fondo)
            new Stop(1, Color.rgb(127, 240, 220))); // Azul claro 
            gc.setFill(fondo);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            
            // Dibujamos burbujas
            for (Burbuja b : burbujas) {
                b.mover();
                b.dibujar(gc);
            }
            //animación de peces, el metodo los dibuja y los mueve.
            pecera.animar(gc, (int)canvas.getWidth(), (int)canvas.getHeight());
            }
        }.start();

        // Interacción con usuario: agregamos pez con un clic
        canvas.setOnMouseClicked(e -> {
            pecera.agregarPez(e.getX(), e.getY());
        });
        // Mostramos el Canvas en una ventana
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Acuario JavaFX");
        stage.show();
    }

  // Método principal para lanzar la aplicación
    public static void main(String[] args) {
       launch(args);
    }
}
