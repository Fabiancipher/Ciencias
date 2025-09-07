import java.util.*;
public class Main{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Ingrese el numero de candidato: \n");
            int n = sc.nextInt();
            System.out.println("Ingrese el tamaño de los eventos (m): \n");
            int m = sc.nextInt();
            Controlador c = new Controlador(n,m);

            Candidato candidatos[] = c.getCandidatos();

            
            System.out.println("Estado Inicial: \n 1) Invertido \n 2) Casi ordenado \n Cualquier otro numero) Aleatorio \n");
            int estado = sc.nextInt();
            if(estado!=1 && estado!=2){
                c.estado(candidatos, 1, estado); //Determina si se dejó aleatorio, en cuyo caso llama a la funcion con un valor por defecto
            }

            else{
                 System.out.println("Atributo Inicial: \n"); mostrarOpciones();
                 int atributoInicial = sc.nextInt();
                 while(atributoInicial<1 && atributoInicial>5){
                    System.out.println("Ingrese un dato valido: \n");
                    atributoInicial = sc.nextInt();
                 }
                 c.estado(candidatos, atributoInicial, estado);
            }
            while(true){ 
                System.out.println("1) Mostrar candidatos\n 2) Burbuja\n 3) Seleccion\n 4) Insercion\n 5) Merge \n 6) Quick \n 7) Escoger un Candidato \n 0) Salir");
                int dec = sc.nextInt();
                switch (dec) {
                    case 1:
                        c.mostrarCandidatos(n); 
                        break;
                    case 2:
                        mostrarOpciones();
                        int algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.burbuja(candidatos, algo);
                        break;
                    case 3:
                        mostrarOpciones();
                        algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.seleccion(candidatos, algo);
                        break;
                    case 4:
                        mostrarOpciones();
                        algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.insercion(candidatos, algo);
                        break;
                    case 5:
                        mostrarOpciones();
                        algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.mergeSort(candidatos, algo);
                        break;
                    case 6:
                        mostrarOpciones();
                        algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.quickSort(candidatos, algo);
                        break;
                    case 7:
                        System.err.println("Escoger al candidato en base al que menos tenga del atributo espicificado \n");
                        mostrarOpciones();
                        algo = sc.nextInt();
                        while(algo<1 && algo>5){
                            System.out.println("Ingrese un dato valido: \n");
                            algo = sc.nextInt();
                        }
                        c.quickSort(candidatos, algo);
                        Candidato ganador = candidatos[0];
                        System.err.println("El candidato: "+ganador.getNombre()+" fué elegido por ser el \"mejor\" entre todos los candidatos \n");
                        System.err.println("¡Felicidades!");  
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
    public static void mostrarOpciones(){
        System.err.println("1) Por Distancia \n 2) Por Horas \n 3) Por Prebendas \n 4) Por Politicos \n 5) Por Corrupcion \n");
    }
}