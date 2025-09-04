public class ActoCorrupcion{
    private String nombre;
    private int valorCorr; 
    public ActoCorrupcion(String name, int corr){
        this.nombre= name;
        this.valorCorr= corr;

    }
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+"/Valor: "+this.valorCorr+"\n";
    }

    public int getValorSobo(){
        return this.valorCorr;
    }
}