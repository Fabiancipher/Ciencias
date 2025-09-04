public class Politico{
    private String nombre;
    private int valorSoborno; 
    public Politico(String name, int soborno){
        this.nombre= name;
        this.valorSoborno= soborno;
    }
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"/Valor: "+this.valorSoborno+"\n";
    }
}