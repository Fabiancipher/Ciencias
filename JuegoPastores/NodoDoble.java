/**
 * Representa un nodo generico de tipo Pastor para añadir a la lista
 */
//import java.util.Objects;
public class NodoDoble<Pastor>{
    private Pastor dato; //Valor
    private NodoDoble<Pastor> anterior; //Referencia al vecino anterior, o a su izquierda, si se quiere
    private NodoDoble<Pastor> siguiente; //Referencia al vecino posterior
    /**
     * Genera un nodo sin enlaces
     * @param dato
     */
    public NodoDoble(Pastor dato){ //Constructor sencillo. Solo genera el nodo sin enlazarlo a nada
        this(dato, null, null);
    }
    /**
     * Genera un nodo con ambos enlaces
     * @param dato
     * @param anterior
     * @param siguiente
     */
    public NodoDoble(Pastor dato, NodoDoble<Pastor> anterior, NodoDoble<Pastor> siguiente){ //Constructor especial. Genera el nodo con sus enlaces
        this.dato = dato;
        this.anterior = anterior;
        this.siguiente = siguiente;
    }
    //Getters
    public Pastor getDato() {
        return dato;
    }
    public NodoDoble<Pastor> getSiguiente() {
        return siguiente;
    }
    public NodoDoble<Pastor> getAnterior() {
        return anterior;
    }
    //Setters <!Importantes!>
    public void setDato(Pastor dato) {
        this.dato = dato;
    }
    public void setSiguiente(NodoDoble<Pastor> siguiente) {
        this.siguiente = siguiente;
    }
    public void setAnterior(NodoDoble<Pastor> anterior) {
        this.anterior = anterior;
    }
    //toString
    @Override
    public String toString() {
        return "Nodo: {Dato: "+this.dato+"}";
    }
}