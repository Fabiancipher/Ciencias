package Modelo;

import java.util.Objects;

/**
 * Representa a uno de los jugadores del juego (un Pastor).
 */
public class Pastor {
    private final int id;
    private String nombre;
    private int fieles, riquezas;
    private String negocio; // Rol

    public Pastor(String name, int fieles, int riquezas, String negocio, int id){
        this.nombre = name;
        this.fieles = fieles;
        this.riquezas = riquezas;
        this.negocio = negocio;
        this.id = id;
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getFieles() { return fieles; }
    public int getRiquezas() { return riquezas; }
    public String getNegocio() { return negocio; }
    public int getId() { return id; }

    // Setters
    public void setNegocio(String negocio) { this.negocio = negocio; }
    public void setNombre(String name) { this.nombre = name; }
    public void setFieles(int fieles) { this.fieles = Math.max(0, fieles); } // Asegurar no negativo
    public void setRiquezas(int riquezas) { this.riquezas = Math.max(0, riquezas); } // Asegurar no negativo

    @Override
    public String toString() {
        return "Pastor{" + "Nombre= "+this.nombre + " | Riquezas= " + this.riquezas + " | Fieles= " + this.fieles + " | Negocio= "+this.negocio+'}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof Pastor)) return false;
        Pastor p = (Pastor) obj;
        // Para asegurar unicidad, compararemos por ID si no hay nombres duplicados, 
        // pero por el momento mantendremos la comparación por nombre para la lógica de juego.
        return this.nombre.equalsIgnoreCase(p.getNombre()) && this.id == p.id; 
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(nombre, id);
    }
}