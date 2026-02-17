import java.io.FileWriter; 
import java.io.IOException; 

public class SalidaArchivo implements Salida{

    FileWriter escritor;


    public SalidaArchivo(){

        try{
            this.escritor = new FileWriter("resultados.txt", true);
        }
        catch(IOException e){
            System.err.println("Error: "+e);
        }
    }

    @Override
    public void mostrar(String m){
        try {
            escritor.write(m+"\n");
            escritor.close();
        } 
        catch (IOException e) {
            System.err.println("Error: "+e);
        }
    }
}