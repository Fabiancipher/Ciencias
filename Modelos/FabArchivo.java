public class FabArchivo implements FabAbstracta{
    public FabArchivo(){

    }

    @Override
    public EntradaArchivo generarEntrada(){
        EntradaArchivo entrada = new EntradaArchivo();
        return entrada;
    }

    @Override
    public SalidaArchivo generarSalida(){
        SalidaArchivo escritor = new SalidaArchivo();
        return escritor;
    }
}