package Modelo;

import Controlador.Controlador;
import java.util.Scanner;

public class Main{
    private static final Controlador controlador = new Controlador();
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        ListaDobleCircular<Pastor> lista = controlador.getLista();
        Pila<Pastor> eliminados = controlador.getEliminados();
        System.out.println("¿Cuántos pastores quieres crear? (Recomendado: 10)");
        int n = sc.nextInt();
        controlador.llenar(n);
        System.out.println(lista+"\n");
        Pastor jugadorActual = controlador.getMasRico();
        while(lista.getSize()!=1){
            Pastor masPobre = controlador.getMasPobre();
            System.out.println("El jugador actual es: "+jugadorActual.getNombre()+"\n");
            eliminados.push(masPobre);
            lista.buscarEliminar(masPobre);
            System.out.println(jugadorActual.getNombre()+" decidió eliminar a: "+masPobre.getNombre()+"\n");
        }
        System.out.println("Los pastores han terminado el juego. \n Ganó: "+jugadorActual.getNombre());
        System.err.println("Eliminados, en orden del ultimo al primero: \n"+eliminados);
    }
}