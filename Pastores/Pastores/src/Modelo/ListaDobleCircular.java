package Modelo;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

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
    public NodoDoble<T> buscar(int dato){
        NodoDoble<T> actual = this.cabeza;
        for(int i=0; i<this.size; i++){ //Itera hasta dar una vuelta completa
            if(actual.getId()==dato){ //Compara los datos
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
        NodoDoble<T> nuevo = new NodoDoble<>(dato, this.cola, this.cabeza, this.size);
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

        //aEliminar.setDato(null);
        this.size--;
    }  

    /**
     * Busca un nodo en base a su dato y lo elimina
     * <p>
     * Es más facil que pasar un nodo entero
     * @param dato : El valor del nodo a eliminar
     */
    public boolean buscarEliminar(T dato){
        if(vacia()||this.size==0){
            return false;
        }
        NodoDoble<T> aEliminar = this.cabeza;
        for(int i=0; i<this.size; i++){
            if(aEliminar==null){
                return false;
            }
            if(Objects.equals(aEliminar.getDato(), dato)){
                eliminar(aEliminar);
                return true;
            }
            aEliminar = aEliminar.getSiguiente();
        }
        return false; 
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
    /**
     * Compara un dato con el siguiente en la lista
     * <p>
     * Si el dato no se encuentra, regresa false
     * @param dato : El dato a comparar
     * @param comp : El comparador que define la comparación
     * @return True si son iguales, false si no lo son o si no se encuentra el dato
     */
    public boolean compararSiguiente(NodoDoble<T> actual, Comparator<T> comp){
        if(actual==null||actual.getSiguiente()==null){
            return false; //No se encontró el dato
        }
        T dato = actual.getDato();
        T siguiente = actual.getSiguiente().getDato();
        return comp.compare(dato, siguiente)==0; //Si el dato es igual al siguiente
    }
    /**
     * Intercambia los datos de un nodo con su siguiente nodo
     * @param n : La id del nodo
     */
    public void intercambiar(int n){
        NodoDoble<T> nodoEncontrado = buscar(n);
        NodoDoble<T> nodoSiguiente = nodoEncontrado.getSiguiente();
        T datoEncontrado = nodoEncontrado.getDato();
        T datoSiguiente = nodoSiguiente.getDato();
        nodoEncontrado.setDato(datoSiguiente);
        nodoSiguiente.setDato(datoEncontrado);
    }
    /**
     * Reordena la lista en base a si el siguiente elemento comparte el mismo dato
     * @param comp El comparador bajo el que se determina la comparacion
     */
    public void reordenar(Comparator<T> comp){
        if(vacia() || size==1){
            return; //No hay nada que ordenar
        }
        boolean swapped;
        int peorCaso = this.size*this.size;
        int iteraciones=0;
        do{
            swapped = false;
            NodoDoble<T> actual = this.cabeza;
            for(int i=0; i<size-1; i++){ //-1 porque se compara con el siguiente
                NodoDoble<T> siguiente = actual.getSiguiente();
                if(comp.compare(actual.getDato(), siguiente.getDato())==0){ //Si el actual igual al siguiente
                    //Intercambia los datos
                    T temp = actual.getDato();
                    actual.setDato(siguiente.getDato());
                    siguiente.setDato(temp);
                    swapped = true; //Se hizo un intercambio
                }
                actual = actual.getSiguiente(); //Avanza
            }
            iteraciones++;
            if(iteraciones>=peorCaso){
                System.err.println("Ocurrió el peor caso intercambiando. Rompiendo...");
                break;
            }
        }while(swapped); //Mientras se hayan hecho intercambios, sigue ordenando
    }
    /**
     * Regresa el dato de un nodo aleatorio
     * <p>
     * Si el tamaño es 1, inevitablemente escoge la cabeza
     * @return El dato de uno de los nodos
     */
    public T aleatorio(){
        Random r = new Random();
        int id = r.nextInt(this.size); //Genera un numero aleatorio de 0 a el tamaño de la lista-1
        int veces = 0; //Cantidad de veces que se buscó un dato aleatorio
        NodoDoble<T> actual = this.cabeza; //Toma la cabeza como base
        for(int i=0; i<this.size; i++){
            if(veces==id){
                return actual.getDato(); //Retorna el dato obtenido
            }
            actual = actual.getSiguiente(); //Avanza
            veces++;
        }
        return null;
    }
    /**
     * Regresa un nodo aleatorio
     * <p>
     * Si el tamaño es 1, inevitablemente escoge la cabeza
     * @return Un nodo de la lista
     */
    public NodoDoble<T> aleatorioNodo(){
        Random r = new Random();
        int id = r.nextInt(this.size); //Genera un numero aleatorio de 0 a el tamaño de la lista-1
        int veces = 0; //Cantidad de veces que se buscó un dato aleatorio
        NodoDoble<T> actual = this.cabeza; //Toma la cabeza como base
        for(int i=0; i<this.size; i++){
            if(veces==id){
                return actual; //Retorna el nodo obtenido
            }
            actual = actual.getSiguiente(); //Avanza
            veces++;
        }
        return null;
    }
}