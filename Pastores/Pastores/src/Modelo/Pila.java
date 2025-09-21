package Modelo;

import java.util.NoSuchElementException;
/**
 * Representa una pila
 * <p>
 * Usa nodos dobles como sus elementos, pero el que sean nodos dobles es deliberado.
 * Podria usar nodos simples.
 */
public class Pila<T>{
    private NodoDoble<T> cima;
    private int size;
    public Pila(){
        this.cima = null;
        this.size = 0;
    }
    //getters y setters
    public NodoDoble<T> getCima(){
        return this.cima;
    }
    public void setCima(NodoDoble<T> cima){
        this.cima=cima;
    }
    //Metodos basicos
    /**
     * Revisa si la pila está vacia
     * @return True si el tamaño es 0
     */
    public boolean vacia(){
        return this.size==0;
    }

    /**
     * Añade un elemento a la pila
     * <p>
     * Como solo se puede añadir "encima", el ultimo elemento ingresado siempre se volverá la cima
     * @param dato : El dato a poner encima
     */
    public void push(T dato){
        NodoDoble<T> nuevo = new NodoDoble<>(dato, null, null,this.size);
        if(vacia()){
            this.cima = nuevo;
            this.size++;
        }
        else{
            nuevo.setSiguiente(this.cima);
            this.cima = nuevo;
            this.size++;
        }
    }
    /**
     * Elimina la cima
     * @throws NoSuchElementException si la pila está vacia
     */
    public void pop(){
        if(vacia()){
            throw new NoSuchElementException("No hay elementos para eliminar");
        }

        NodoDoble<T> nuevaCima = this.cima.getSiguiente();

        this.cima.setSiguiente(null);
        this.cima = nuevaCima;
        this.size--;
    }

    /**
     * Elimina la pila
     * @see {@link #pop()}
     */
    public void eliminarPila(){
        while(!vacia()){
            pop();
        }
    }
    /**
     * Permite ver el dato almacenado en la cima 
     * @return Un dato generico de tipo {@code T}
     */
    public T peek(){
        return !vacia()? this.cima.getDato():null;
    }

    /**
     * Devuelve un string con los elementos de la pila
     * @return Un String
     */
    @Override
    public String toString(){
        if(vacia()){
            return "La pila está vacia";
        }
        NodoDoble<T> actual = this.cima;
        StringBuilder sb = new StringBuilder();
        sb.append("<!CIMA!>\n");
        while(actual!=null){
            sb.append(actual);
            sb.append("\n");
            sb.append("<-> \n");
            actual = actual.getSiguiente();
        }
        sb.append("<!FONDO!>");
        return sb.toString();
    }
}