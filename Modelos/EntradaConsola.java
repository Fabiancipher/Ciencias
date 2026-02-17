import java.util.Scanner;
public class EntradaConsola implements Entrada{
    static Scanner sc = new Scanner(System.in);

    public EntradaConsola() {

    }

    @Override
    public String capturar() {
        return sc.nextLine();
    }

    /*
    @Override
    public String capturarTexto() {
        return sc.nextLine();
    }
    */
}