public class Marcha{
    private String nombre;
    private int distancia;

    public Marcha(String name, int distance){
        this.nombre = name;
        this.distancia = distance;
    }
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"/Distancia: "+this.distancia+"\n";
    }
}