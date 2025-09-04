public class Prebenda{
    private String nombre_pre;
    private int valor; 
    public Prebenda(String name, int value){
        this.nombre_pre= name;
        this.valor= value;

    }
    @Override
    public String toString(){
        return "Nombre: "+this.nombre_pre+"/Valor: "+this.valor+"\n";
    }

    public int getValorPre(){
        return this.valor;
    }
}