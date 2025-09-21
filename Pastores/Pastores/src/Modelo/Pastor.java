package Modelo;
import java.util.Objects;
/**Representa a uno de los jugadores del juego
 * <p>
 * Incluye un nombre, un numero de fieles, unas riquezas y un rol
 */
public class Pastor{
    /**
     * Una id para cada pastor
     * <p>
     * Se corresponde con la id del nodo, pues se generan de la misma forma
     */
    private final int id;
    private String nombre;
    private int fieles, riquezas;
    private String negocio;
    public Pastor(String name, int fieles, int riquezas, String negocio, int id){
        this.nombre = name;
        this.fieles = fieles;
        this.riquezas = riquezas;
        this.negocio = negocio;
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public int getFieles() {
        return fieles;
    }

    public int getRiquezas() {
        return riquezas;
    }
    public String getNegocio(){
        return negocio;
    }
    public int getId(){
        return id;
    }
    public void setNegocio(String negocio){
        this.negocio=negocio;
    }
    public void setNombre(String name){
        this.nombre = name;
    }
    public void setFieles(int fieles) {
        this.fieles = fieles;
    }
    public void setRiquezas(int riquezas) {
        this.riquezas = riquezas;
    }
    /**
     * Devuelve los datos de un pastor
     * @return Un String
     */
    @Override
    public String toString() {
        return "Pastor{" + "Nombre= "+this.nombre + " | Riquezas= " + this.riquezas + " | Fieles= " + this.fieles + " | Negocio= "+this.negocio+'}';
    }
    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(!(obj instanceof Pastor)) return false;
        Pastor p = (Pastor) obj;
        return this.nombre.equalsIgnoreCase(p.getNombre());
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}