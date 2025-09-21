package Modelo;

import Controlador.Controlador;
import java.util.Comparator;
import java.util.Scanner;

public class Main{
    private static final Controlador controlador = new Controlador();
    private static final Scanner sc = new Scanner(System.in);
    private static final Comparator<Pastor> comp = Comparator.comparing(Pastor::getNegocio);
    public static void main(String[] args) {
        ListaDobleCircular<Pastor> lista = controlador.getLista();
        Pila<Pastor> eliminados = controlador.getEliminados();    
        System.out.println("¿Cuántos pastores quieres crear? (Recomendado: 10)");
        int n = sc.nextInt();
        controlador.llenar(n);
        System.out.println(lista+"\n");
        NodoDoble<Pastor> jugadorActual = lista.buscar(controlador.getMasRico().getId());
        while(lista.getSize()!=1){
            
            Pastor masPobre = controlador.getMasPobre();
            Pastor masRico = controlador.getMasRico();
            System.out.println("El jugador actual es: "+jugadorActual.getDato().getNombre()+"\n");
            if(!jugadorActual.getDato().equals(masPobre)){
                switch (controlador.eleccion()) {
                    case 1 -> {
                        eliminados.push(masPobre);
                        lista.buscarEliminar(masPobre);
                        System.out.println(jugadorActual.getDato().getNombre()+" decidió eliminar a: "+masPobre.getNombre()+"\n");
                        NodoDoble<Pastor> jugadorNuevo = lista.aleatorioNodo();
                        if(lista.getSize()>2){
                            while(jugadorNuevo==jugadorActual){
                                System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                jugadorNuevo = lista.aleatorioNodo();
                            }
                            jugadorActual = jugadorNuevo;
                        }
                    }
                    case 2 -> {
                        NodoDoble<Pastor> jugadorNuevo;
                        if(eliminados.vacia()){
                            System.out.println(jugadorActual.getDato().getNombre()+" intentó devolver a un jugador eliminado inexistente \n");
                            jugadorNuevo = lista.aleatorioNodo();
                            if(lista.getSize()>2){
                                while(jugadorNuevo==jugadorActual){
                                    System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                    jugadorNuevo = lista.aleatorioNodo();
                                }
                                jugadorActual = jugadorNuevo;
                            }
                        }
                        else{
                            Pastor regresando = eliminados.peek();
                            regresando.setFieles(regresando.getFieles()/2);
                            regresando.setRiquezas(regresando.getRiquezas()/2);
                            lista.agregar(regresando);
                            eliminados.pop();
                            System.out.println(jugadorActual.getDato().getNombre()+", benevolente como siempre, decidió devolver al juego a "+regresando.getNombre()+"\n");
                            jugadorNuevo = lista.aleatorioNodo();
                            if(lista.getSize()>2){
                                while(jugadorNuevo==jugadorActual){
                                    System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                    jugadorNuevo = lista.aleatorioNodo();
                                }
                                jugadorActual = jugadorNuevo;
                            }
                        }
                    }
                    default -> System.err.println("El controlador de alguna forma devolvió un numero distinto de 1 o 2 \n");
                }
                
            }
            else{
                int eleccion = controlador.eleccionPobre();
                System.out.println("<!EL JUGADOR ACTUAL ES EL MÁS POBRE!> \n");
                boolean yaRobo=false;
                do { 
                    switch (eleccion) {

                    case 1 -> {
                        eliminados.push(masRico);
                        lista.buscarEliminar(masRico);
                        System.out.println(jugadorActual.getDato().getNombre()+" decidió eliminar a: "+masRico.getNombre()+"\n");
                        NodoDoble<Pastor> jugadorNuevo = lista.aleatorioNodo();
                        if(lista.getSize()>2){
                            while(jugadorNuevo==jugadorActual){
                                System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                jugadorNuevo = lista.aleatorioNodo();
                            }
                            jugadorActual = jugadorNuevo;
                        }
                    }

                    case 2 -> {
                        NodoDoble<Pastor> jugadorNuevo;
                        if(eliminados.vacia()){
                            System.out.println(jugadorActual.getDato().getNombre()+" intentó devolver a un jugador eliminado inexistente \n");
                            jugadorNuevo = lista.aleatorioNodo();
                            if(lista.getSize()>2){
                                while(jugadorNuevo==jugadorActual){
                                    System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                    jugadorNuevo = lista.aleatorioNodo();
                                }
                                jugadorActual = jugadorNuevo;
                            }
                        }
                        else{
                            Pastor regresando = eliminados.peek();
                            regresando.setFieles(regresando.getFieles()/2);
                            regresando.setRiquezas(regresando.getRiquezas()/2);
                            lista.agregar(regresando);
                            eliminados.pop();
                            System.out.println(jugadorActual.getDato().getNombre()+", benevolente como siempre, decidió devolver al juego a "+regresando.getNombre()+"\n");
                            jugadorNuevo = lista.aleatorioNodo();
                            if(lista.getSize()>2){
                                while(jugadorNuevo==jugadorActual){
                                    System.out.println("<!EL JUGADOR ESTÁ INTENTANDO ESCOGERSE A SÍ MISMO!> \n");
                                    jugadorNuevo = lista.aleatorioNodo();
                                }
                                jugadorActual = jugadorNuevo;
                            }
                        }
                    }

                    case 3 ->{  
                        Pastor datoPobre = jugadorActual.getDato();
                        if(!yaRobo){
                            datoPobre.setFieles(datoPobre.getFieles()+masRico.getFieles()/3);
                            datoPobre.setRiquezas(datoPobre.getRiquezas()+masRico.getRiquezas()/3);
                            masRico.setFieles(masRico.getFieles()-masRico.getFieles()/3);
                            masRico.setRiquezas(masRico.getRiquezas()-masRico.getRiquezas()/3);
                            System.out.println("Como le era propio, "+datoPobre.getNombre()+" le echó mano a un tercio de las posesiones y fieles de "+masRico.getNombre()+"\n");
                            eleccion = controlador.eleccionPobre();
                            yaRobo = true; 
                        }
                        else{
                            System.out.println(datoPobre.getNombre()+" ya robó este turno. ¡Bendita codicia! \n");
                            eleccion = controlador.eleccionPobre();
                        }
                    }

                    default -> System.err.println("El controlador de alguna forma devolvió un numero distinto de 1,2 o 3 \n");

                    }
                } while (eleccion!=1 && eleccion!=2);
            }

            if(lista.getSize()>2){
                if(lista.compararSiguiente(jugadorActual, comp)){
                    System.out.println("<!EL JUGADOR ACTUAL TIENE UN VECINO CON SU MISMO NEGOCIO!> Reordenando... \n");
                    lista.intercambiar(jugadorActual.getId());
                    jugadorActual = lista.aleatorioNodo(); // Actualiza el nodo después de reordenar
                    System.out.println("Se escogió un nuevo jugador aleatorio para que continue");
                    System.out.println(lista);
                }
            }

        }
        System.out.println("Los pastores han terminado el juego. \n Ganó: "+jugadorActual.getDato().getNombre());
        System.err.println("Eliminados, en orden del ultimo al primero: \n"+eliminados);
        lista.eliminarLista();
        eliminados.eliminarPila();
    }
}