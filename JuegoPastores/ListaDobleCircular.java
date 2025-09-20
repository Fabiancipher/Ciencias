import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Representa una lista circular doblemente enlazada
 * Utiliza nodos de tipo T como sus "vagones"
 */
public class ListaDobleCircular<T>{
    private NodoDoble<T> cabeza;
    private NodoDoble<T> cola;
    private int size;
    public ListaDobleCircular(){
        this.cabeza = null;
        this.cola = null;
        this.size = 0;
    }
    //Getters y setters
    public NodoDoble<T> getCabeza() {
        return cabeza;
    }
    public NodoDoble<T> getCola() {
        return cola;
    }
    public int getSize() {
        return size;
    }
    public void setCabeza(NodoDoble<T> cabeza) {
        this.cabeza = cabeza;
    }
    public void setCola(NodoDoble<T> cola) {
        this.cola = cola;
    }
    public void setSize(int size) {
        this.size = size;
    }
    // Metodos basicos
    /**
     * Revisa si la lista está vacia
     * @return True si el tamaño es 0
     */
    public boolean vacia(){
        return this.size == 0;
    }

    /**
     * Busca un nodo en base a su carga o valor
     * @param dato : El dato a buscar
     * @return El nodo encontrado, o null si no se encuentra
     */
    public NodoDoble<T> buscar(T dato){
        NodoDoble<T> actual = this.cabeza;
        for(int i=0; i<this.size; i++){ //Itera hasta dar una vuelta completa
            if(Objects.equals(actual.getDato(), dato)){ //Compara los datos
                return actual; //Regresa el nodo
            }
            actual = actual.getSiguiente(); //Avanza
        }
        return null;
    }

    /**
     * Añade al final. Otra forma de llamar a {@link #agregarAlFinal(Object)}
     * @param dato
     */
    public void agregar(T dato){
        agregarAlFinal(dato);
    }

    /**
     * Ingresa un nodo al final, convirtiendolo en la nueva cola
     * @param dato
     */
    private void agregarAlFinal(T dato){
        NodoDoble<T> nuevo = new NodoDoble<>(dato, this.cola, this.cabeza);
        if(vacia()){ //Si es la primera insercion, se vuelve tanto la cabeza como la cola
            this.cabeza = nuevo;
            this.cola = nuevo;
            nuevo.setSiguiente(nuevo); //Se apunta a sí mismo
            nuevo.setAnterior(nuevo);
            this.size++;
        }
        else{
            this.cola.setSiguiente(nuevo); //Cambia las referencias de la antigua cola y la cabeza
            this.cabeza.setAnterior(nuevo); 
            this.cola = nuevo;
            this.size++;
        }
    }

    /**
     * Busca y elimina un nodo
     */
    private void eliminar(NodoDoble<T> aEliminar){
        if(vacia()){
            throw new NoSuchElementException("No hay elementos a eliminar");
        }
        NodoDoble<T> anterior = aEliminar.getAnterior();
        NodoDoble<T> posterior = aEliminar.getSiguiente();

        //Caso especial
        if(size==1){
            //eliminarLista();
            //return;
        }

        anterior.setSiguiente(posterior); //Actualiza las referencias
        posterior.setAnterior(anterior);

        if(aEliminar==this.cabeza){
            this.cabeza = posterior;
        }

        if(aEliminar==this.cola){
            this.cola = anterior;
        }

        this.size--;
    }  

    /**
     * Busca un nodo en base a su dato y lo elimina
     * <p>
     * Es más facil que pasar un nodo entero
     * @param dato : El valor del nodo a eliminar
     */
    public void buscarEliminar(T dato){
        NodoDoble<T> aEliminar = buscar(dato);
        if(aEliminar==null){
            return;
        }
        eliminar(aEliminar);
    }

    /**
     * Regresa un string de los nodos
     */
    @Override
    public String toString(){
        NodoDoble<T> actual = this.cabeza;
        StringBuilder sb = new StringBuilder();
        sb.append("<!CABEZA!>");
        sb.append("\n");
        for(int i=0; i<this.size; i++){
            sb.append(actual);
            sb.append("\n");
            sb.append(" <-> ");
            sb.append("\n");
            actual = actual.getSiguiente();
        }
        sb.append("<!COLA!>");
        return sb.toString();
    }
}