import java.util.*;
public class Main{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el numero de candidato: \n");
            int n = sc.nextInt();
            System.out.println("Ingrese el tamaño de los eventos (m): \n");
            int m = sc.nextInt();
            Controlador c = new Controlador(n,m);
            while(true){ 
                System.out.println("1) Mostrar candidatos\n 2) Burbuja\n 3) Seleccion\n 4) Insercion\n 0) Salir\n");
                int dec = sc.nextInt();
                switch (dec) {
                    case 1:
                        c.mostrarCandidatos(n); 
                        break;
                    case 2:
                        c.burbuja();
                        break;
                    case 3:
                        c.seleccion();
                        break;
                    case 4:
                        c.insercion();
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        return;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        } 
    }
}