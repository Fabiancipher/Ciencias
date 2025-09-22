package Modelo;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa una pila de Pastores (Desposeídos).
 */
public class Pila<T>{
    private NodoDoble<T> cima;
    private int size;
    
    public Pila(){
        this.cima = null;
        this.size = 0;
    }

    // Getters
    public NodoDoble<T> getCima(){ return this.cima; }
    public void setCima(NodoDoble<T> cima){ this.cima=cima; }
    
    public boolean vacia(){ return this.size==0; }

    public void push(T dato){
        // La ID se usa en NodoDoble, pero para la pila no es estrictamente necesaria.
        NodoDoble<T> nuevo = new NodoDoble<>(dato, null, null, this.size); 
        if(vacia()){
            this.cima = nuevo;
        } else {
            nuevo.setSiguiente(this.cima);
            this.cima = nuevo;
        }
        this.size++;
    }
    
    public void pop(){
        if(vacia()){
            throw new NoSuchElementException("No hay elementos para eliminar");
        }
        NodoDoble<T> nuevaCima = this.cima.getSiguiente();
        this.cima.setSiguiente(null); // Desenlaza la cima anterior
        this.cima = nuevaCima;
        this.size--;
    }

    public void eliminarPila(){
        this.cima = null;
        this.size = 0;
    }
    
    public T peek(){
        return !vacia() ? this.cima.getDato() : null;
    }
    
    /**
     * Devuelve una lista de los elementos de la pila, de cima a base (LIFO).
     */
    public List<T> getElementos() {
        List<T> lista = new ArrayList<>();
        NodoDoble<T> actual = this.cima;
        while(actual != null) {
            lista.add(actual.getDato());
            actual = actual.getSiguiente();
        }
        return lista;
    }

    @Override
    public String toString(){
        if(vacia()){
            return "La pila está vacia";
        }
        NodoDoble<T> actual = this.cima;
        StringBuilder sb = new StringBuilder();
        sb.append("<!CIMA!>\n");
        while(actual!=null){
            sb.append(actual).append("\n");
            sb.append(" <-> \n");
            actual = actual.getSiguiente();
        }
        sb.append("<!FONDO!>");
        return sb.toString();
    }
}