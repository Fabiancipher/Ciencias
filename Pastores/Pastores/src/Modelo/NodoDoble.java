package Modelo;
/**
 * Representa un nodo generico de tipo T para añadir a la lista
 */
public class NodoDoble<T>{
    private int id;
    private T dato; //Valor
    private NodoDoble<T> anterior; //Referencia al vecino anterior, o a su izquierda, si se quiere
    private NodoDoble<T> siguiente; //Referencia al vecino posterior
    /**
     * Genera un nodo sin enlaces
     * @param dato
     */
    public NodoDoble(T dato){ //Constructor sencillo. Solo genera el nodo sin enlazarlo a nada
        this(dato, null, null,0);
    }
    /**
     * Genera un nodo con ambos enlaces
     * @param dato
     * @param anterior
     * @param siguiente
     */
    public NodoDoble(T dato, NodoDoble<T> anterior, NodoDoble<T> siguiente, int id){ //Constructor especial. Genera el nodo con sus enlaces
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.id = id;
    }
    //Getters
    public T getDato() {
        return dato;
    }
    public NodoDoble<T> getSiguiente() {
        return siguiente;
    }
    public NodoDoble<T> getAnterior() {
        return anterior;
    }
    public int getId(){
        return id;
    }
    //Setters <!Importantes!>
    public void setDato(T dato) {
        this.dato = dato;
    }
    public void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }
    public void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
    //toString
    @Override
    public String toString() {
        return "Nodo: {Dato: "+this.dato+"}";
    }
}