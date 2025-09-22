package Modelo;
/**
 * Representa un nodo generico de tipo T para añadir a la lista o pila.
 */
public class NodoDoble<T>{
    /**
     * La posición del nodo y un identificador único
     * <p>
     * Si un nodo fuese a ser eliminado, el id de los demás nodos *no* se actualiza
     * <p>
     * La idea es que el dato tambien posea un id que se corresponda con el del nodo, con ello se simplifica el proceso de
     * buscar un nodo en especifico
     */
    private final int id;
    private T dato; 
    private NodoDoble<T> anterior; 
    private NodoDoble<T> siguiente; 

    public NodoDoble(T dato, NodoDoble<T> anterior, NodoDoble<T> siguiente, int id){ 
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.id = id;
    }

    // Getters
    public T getDato() { return dato; }
    public NodoDoble<T> getSiguiente() { return siguiente; }
    public NodoDoble<T> getAnterior() { return anterior; }
    public int getId(){ return id; }
    
    // Setters
    public void setDato(T dato) { this.dato = dato; }
    public void setSiguiente(NodoDoble<T> siguiente) { this.siguiente = siguiente; }
    public void setAnterior(NodoDoble<T> anterior) { this.anterior = anterior; }
    
    // toString
    @Override
    public String toString() {
        // En lugar de llamar a toString de Dato, solo mostramos el dato.
        return "Nodo: {Dato: "+this.dato+"}";
    }
}