import java.util.*;
public class Main{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el numero de candidato: \n");
            int n = sc.nextInt();
            System.out.println("Ingrese el tamaño de los eventos (m): \n");
            int m = sc.nextInt();
            Controlador c = new Controlador(n,m);
            System.out.println("1) Mostrar candidatos\n");
            int dec = sc.nextInt();
            switch (dec) {
                case 1:
                    c.mostrarCandidatos(n); 
                    break;
            
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
        } 
    }
}