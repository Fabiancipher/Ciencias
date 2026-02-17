public class Main {
    static FabAbstracta fabrica;
    static Sumador sumador = new Sumador();
    public static void main(String[] args) {
        int seguir;
        do{
            fabrica = new FabConsola();

            Entrada entrada = fabrica.generarEntrada();
            Salida salida = fabrica.generarSalida();

            salida.mostrar("Desea Sumar?(1: Si/ 0: No): ");
            int dec = Integer.parseInt(entrada.capturar());

            while(dec!=0 && dec!=1){
                salida.mostrar("Decision invalida: ");
                dec = Integer.parseInt(entrada.capturar());
            }

            if(dec==0){
                return;
            }

            salida.mostrar("Ingrese el primer número: ");
            int numUno = Integer.parseInt(entrada.capturar());
            salida.mostrar("Ingrese el segundo número: ");
            int numDos = Integer.parseInt(entrada.capturar());

            String resultado = sumador.sumar(numUno, numDos).toString();

            salida.mostrar(resultado);

            salida.mostrar("Desea seguir sumando?(1: Si/ 0: No): ");
            seguir = Integer.parseInt(entrada.capturar());
            while(seguir!=0 && seguir!=1){
                salida.mostrar("Decision invalida: ");
                seguir = Integer.parseInt(entrada.capturar());
            }

            fabrica = new FabArchivo();
            entrada = fabrica.generarEntrada();
            salida = fabrica.generarSalida();

            numUno = Integer.parseInt(entrada.capturar()); 
            numDos = Integer.parseInt(entrada.capturar());

            resultado = sumador.sumar(numUno, numDos).toString();

            salida.mostrar(resultado);

        }while(seguir!=0);

    }

}