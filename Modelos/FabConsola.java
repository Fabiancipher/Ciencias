public class FabConsola implements FabAbstracta{
    public FabConsola(){

    }

    @Override
    public EntradaConsola generarEntrada(){
        EntradaConsola entrada = new EntradaConsola();
        return entrada;
    }

    @Override
    public SalidaConsola generarSalida(){
        SalidaConsola consola = new SalidaConsola();
        return consola;
    }
}