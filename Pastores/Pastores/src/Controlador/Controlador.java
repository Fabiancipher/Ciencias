package Controlador;

import Modelo.*;
import java.util.Comparator;
import java.util.Random;
/**
 * Un controlador para el juego
 */
public class Controlador{
    private final ListaDobleCircular<Pastor> pastores;
    private final Pila<Pastor> eliminados;
    private final Random r = new Random();
    private final String titulos[] = {"El Buen Pastor", "El Pastor de los Corderos", "El Pastor Fiel", "El Pastor Valiente", "El Pastor Sabio", "El Pastor Justo", "El Pastor Misericordioso", "El Pastor Amoroso", "El Pastor Paciente", "El Pastor Humilde"};
    private final String nombres[] = {"Juan", "Pedro", "Luis", "Carlos", "Miguel", "Jorge", "Andrés", "Diego", "Santiago", "Tomás"};
    private final String roles[] = {"Tienda", "Granja", "Iglesia", "Mercado", "Campo"};
    public Controlador(){
        this.pastores = new ListaDobleCircular<>();
        this.eliminados = new Pila<>();
    }
    //Getters
    public ListaDobleCircular<Pastor> getLista(){
        return this.pastores;
    }
    public Pila<Pastor> getEliminados(){
        return this.eliminados;
    }
    public Pastor getMasRico(){
        return this.pastores.maximo(Comparator.comparingInt(Pastor::getRiquezas));
    }
    public Pastor getMasPobre(){
        return this.pastores.minimo(Comparator.comparingInt(Pastor::getRiquezas));
    }
    //Metodos basicos
    /**
     * Llena la lista con n pastores aleatorios
     * @param n : La cantidad de pastores a crear
     */
    public void llenar(int n){
        for(int i=0; i<=n; i++){
            this.pastores.agregar(new Pastor(titulos[r.nextInt(titulos.length)]+" "+nombres[r.nextInt(nombres.length)], r.nextInt(2000), r.nextInt(2000), roles[r.nextInt(roles.length)]));
        }
    }
    /**
     * Busca un pastor por su nombre
     * @param nombre : El nombre del pastor a buscar
     * @return El pastor encontrado, o null si no se encuentra
     */
    public Pastor buscar(String nombre){
        for(Pastor p : this.pastores){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                return p;
            }
        }
        return null;
    }
}