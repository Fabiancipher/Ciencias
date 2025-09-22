package Modelo;
/**
 * Representa un nodo generico de tipo T para añadir a la lista o pila.
 */
public class NodoDoble<T>{
    private int id;
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