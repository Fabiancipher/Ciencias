package Modelo;
/**Representa a uno de los jugadores del juego
 * <p>
 * Incluye un nombre, un numero de fieles, unas riquezas y un rol
 */
public class Pastor{
    private String nombre;
    private int fieles, riquezas;
    private String negocio;
    public Pastor(String name, int fieles, int riquezas, String negocio){
        this.nombre = name;
        this.fieles = fieles;
        this.riquezas = riquezas;
        this.negocio = negocio;
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
}