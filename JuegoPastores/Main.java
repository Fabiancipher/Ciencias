
public class Main{
    public static void main(String[] args) {
        ListaDobleCircular<Pastor> lista = new ListaDobleCircular<>();
        lista.agregar(new Pastor("Juan", 30, 30, "Tienda"));
        lista.agregar(new Pastor("Pedro", 25, 25, "Tienda"));
        lista.agregar(new Pastor("Luis", 40, 40, "Tienda"));
        System.out.println(lista);
    }
}