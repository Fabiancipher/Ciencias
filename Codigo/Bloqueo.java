public class Bloqueo{
    private String nombre;
    private int horas; 
    public Bloqueo(String name, int hours){
        this.nombre= name;
        this.horas= hours;
    }
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"/Horas: "+this.horas+"\n";
    }

    public int getHoras(){
        return this.horas;
    }
}