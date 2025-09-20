package Modelo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Representa una lista circular doblemente enlazada
 * Utiliza nodos de tipo T como sus "vagones"
 */
public class ListaDobleCircular<T> implements Iterable<T>{
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
     * @param dato : El nuevo dato a añadir a la lista
     */
    public void agregar(T dato){
        agregarAlFinal(dato);
    }

    /**
     * Ingresa un nodo al final, convirtiendolo en la nueva cola
     * @param dato : El nuevo dato a añadir a la lista
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
     * @param aEliminar : El nodo a eliminar
     * @throws NoSuchElementException si la lista está vacia
     */
    private void eliminar(NodoDoble<T> aEliminar){
        if(vacia()){
            throw new NoSuchElementException("No hay elementos a eliminar");
        }
        NodoDoble<T> anterior = aEliminar.getAnterior();
        NodoDoble<T> posterior = aEliminar.getSiguiente();

        //Caso especial
        if(size==1){
            eliminarLista();
            return;
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
     * Elimina la lista completa
     */
    public void eliminarLista(){
        this.cabeza = null;
        this.cola = null;
        this.size = 0;
    }

    /**
     * Regresa un string de los nodos
     * @return Un string que muestra cada nodo
     */
    @Override
    public String toString(){
        if(vacia()){
            return "La lista está vacia";
        }
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

    //Iterador
    /**
     * Itera a traves de los elementos de la lista, obteniendo el dato de cada nodo
     */
    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private NodoDoble<T> actual = cabeza;
            private int count = 0;

            @Override
            public boolean hasNext(){
                return count<size;
            }

            @Override
            public T next(){
                if(!hasNext()){
                    throw new NoSuchElementException();
                }
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                count++;
                return dato;
            }
        };
    }

    //Encontrar maximo y minimo
    /**
     * Busca y devuelve el mayor elemento encontrado en la lista
     * @param comp : Un comparador, determina sobre *que* se va a buscar
     * @return El dato encontrado
     */
    public T maximo(Comparator<T> comp){
        if(vacia()){
            return null; //No hay elementos a comparar
        }
        NodoDoble<T> actual = this.cabeza;
        T max = actual.getDato(); //Obtiene el dato de la cabeza y lo usa para la comparacion inicial

        for(int i=0; i<size; i++){
            //Comparator devuelve un entero positivo (Dios sabra cual) 
            if(comp.compare(actual.getDato(), max)>0){ //si el primer elemento es mayor al segundo
                max = actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return max;
        //return Collections.max(this,comp) <- Aplicable si se implementa *todo* collections
    }
    /**
     * Busca y devuelve el minimo elemento encontrado en la lista
     * @param comp : Un comparador, determina sobre *que* se va a buscar
     * @return El dato encontrado
     */
    public T minimo(Comparator<T> comp){ //Comparator devuelve 0 si son iguales, pero por el momento esto no nos concierne
        if(vacia()){
            return null; //No hay elementos a comparar
        }
        NodoDoble<T> actual = this.cabeza;
        T min = actual.getDato(); //Obtiene el dato de la cabeza y lo usa para la comparacion inicial

        for(int i=0; i<size; i++){
            //Comparator devuelve un entero negativo (Dios sabra cual) 
            if(comp.compare(actual.getDato(), min)<0){ //si el primer elemento es menor al segundo
                min = actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return min;
        //return Collections.min(this,comp) <- Aplicable si se implementa *todo* collections
    }
}