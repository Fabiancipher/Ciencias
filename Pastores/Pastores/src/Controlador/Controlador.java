package Controlador;

import Modelo.*;
import Vista.VistaJuego;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Un controlador para el juego que gestiona la lógica y enlaza Modelo y Vista.
 */
public class Controlador {
    private final ListaDobleCircular<Pastor> pastores;
    private final Pila<Pastor> eliminados;
    private VistaJuego vista; // Referencia a la Vista
    private NodoDoble<Pastor> nodoActual; // El Nodo del pastor que tiene el turno
    private final Random r = new Random();
    
    // Propiedades de creación de Pastores (Mantenidas de tu código)
    private final String titulos[] = {"El Buen Pastor", "El Pastor de los Corderos", "El Pastor Fiel", "El Pastor Valiente", "El Pastor Sabio", "El Pastor Justo", "El Pastor Misericordioso", "El Pastor Amoroso", "El Pastor Paciente", "El Pastor Humilde"};
    private final String nombres[] = {"Juan", "Pedro", "Luis", "Carlos", "Miguel", "Jorge", "Andrés", "Diego", "Santiago", "Tomás"};
    private final String roles[] = {"Tienda", "Granja", "Iglesia", "Mercado", "Campo"};
    private final Comparator<Pastor> compNegocio = Comparator.comparing(Pastor::getNegocio); // Comparador por negocio
    
    // --- Constructor ---
    
    public Controlador() {
        this.pastores = new ListaDobleCircular<>();
        this.eliminados = new Pila<>();
    }

    // --- Enlace MVC y Control de Juego ---
    
    public void setVista(VistaJuego vista) {
        this.vista = vista;
        this.vista.getBtnIniciar().addActionListener(e -> {
            try {
                int n = Integer.parseInt(JOptionPane.showInputDialog(vista, "¿Cuántos pastores deseas crear? (Mínimo 2)", "Inicio de Juego", JOptionPane.QUESTION_MESSAGE));
                if (n < 2) {
                    vista.mostrarMensaje("Advertencia: Se requieren al menos 2 pastores para jugar.");
                    return;
                }
                iniciarJuego(n);
            } catch (NumberFormatException ex) {
                vista.mostrarMensaje("Error: Introduce un número válido.");
            }
        });
        this.vista.getBtnEjecutarTurno().addActionListener(e -> ejecutarTurnoDesdeVista());
    }

    private void iniciarJuego(int numPastores) {
        pastores.eliminarLista();
        eliminados.eliminarPila();
        llenar(numPastores);
        
        Pastor masRico = getMasRico();
        if (masRico != null) {
            this.nodoActual = pastores.buscar(masRico.getId()); 
            vista.mostrarMensaje("--- ¡Juego Iniciado! ---");
            vista.mostrarMensaje("Comienza el pastor más rico: " + masRico.getNombre());
        } else {
            vista.mostrarMensaje("Error: No se pudieron crear pastores.");
            return;
        }
        
        actualizarVista();
        vista.getBtnEjecutarTurno().setEnabled(true);
        vista.getBtnIniciar().setEnabled(false);
    }
    
    private void ejecutarTurnoDesdeVista() {
        if (pastores.getSize() <= 1) {
            finalizarJuego();
            return;
        }
        
        // Obtener datos de la Vista
        int n = vista.getN();
        String direccion = vista.getDireccionElegida();
        String accion = vista.getAccionElegida();
        
        // Ejecutar el turno con las decisiones del usuario
        String mensajeTurno = procesarTurno(n, direccion, accion);
        vista.mostrarMensaje(mensajeTurno);
        
        // Lógica de reordenamiento y selección del siguiente jugador
        verificarYReordenar();
        seleccionarSiguienteJugador();

        // Actualizar la interfaz
        actualizarVista();
        
        // Verificar fin de juego
        if (pastores.getSize() <= 1) {
            finalizarJuego();
        }
    }
    
    private void actualizarVista() {
        Pastor pastorEnTurno = (nodoActual != null) ? nodoActual.getDato() : null;
        vista.actualizarMesa(getPastoresEnMesa(), pastorEnTurno);
        vista.actualizarPila(getPastoresEnPila());
    }

    private void finalizarJuego() {
        vista.mostrarMensaje("¡Fin del juego!");
        if (nodoActual != null) {
            vista.mostrarMensaje("El ganador es: " + nodoActual.getDato().getNombre());
        }
        vista.getBtnEjecutarTurno().setEnabled(false);
        vista.getBtnIniciar().setEnabled(true);
    }

    // --- Lógica del Juego ---

    private String procesarTurno(int n, String direccion, String accion) {
        if (nodoActual == null) return "Error: No hay un pastor en turno.";
        
        Pastor pastorActual = nodoActual.getDato();
        Pastor masRico = getMasRico();
        
        StringBuilder log = new StringBuilder();
        log.append("Turno de **").append(pastorActual.getNombre()).append("**\n");

        boolean esPobre = pastorActual.equals(getMasPobre());
        
        // 1. Lógica de Hurto (Prioridad para el más pobre si elige la acción)
        if (esPobre && accion.contains("Hurto")) {
            return ejecutarHurto(pastorActual, masRico);
        }
        
        // 2. Lógica de Eliminación (Eliminar vecino con menor grey)
        else if (accion.contains("Eliminar")) {
            return ejecutarEliminacion(pastorActual, direccion, n);
        }
        
        // 3. Lógica de Rescate (Sacar de la pila)
        else if (accion.contains("Rescatar")) {
            return ejecutarRescate(pastorActual);
        }
        
        return log.append("Acción no reconocida o no aplicable.").toString();
    }
    
    private String ejecutarHurto(Pastor pastorActual, Pastor masRico) {
        if (pastores.getSize() > 1 && !pastorActual.equals(masRico)) {
            int riquezasRobadas = masRico.getRiquezas() / 3;
            int fielesRobados = masRico.getFieles() / 3;

            pastorActual.setRiquezas(pastorActual.getRiquezas() + riquezasRobadas);
            pastorActual.setFieles(pastorActual.getFieles() + fielesRobados);
            masRico.setRiquezas(masRico.getRiquezas() - riquezasRobadas);
            masRico.setFieles(masRico.getFieles() - fielesRobados);
            
            return String.format(" -> **Hurto piadoso:** Robó %d doblones y %d fieles de %s (el más rico).\n", riquezasRobadas, fielesRobados, masRico.getNombre());
        }
        return " -> **Hurto fallido:** El pastor es el único, o es el más rico, o no eligió 'Hurto'.\n";
    }
    
    private String ejecutarEliminacion(Pastor pastorActual, String direccion, int n) {
        Pastor objetivo = buscarPastorAfectar(direccion, n);
        
        if (objetivo != null && pastores.buscarEliminar(objetivo)) {
            eliminados.push(objetivo);
            
            // Traspaso de bienes
            pastorActual.setRiquezas(pastorActual.getRiquezas() + objetivo.getRiquezas());
            pastorActual.setFieles(pastorActual.getFieles() + objetivo.getFieles());
            
            return String.format(" -> **Degüello:** %s elimina a **%s**. Traspasa %d doblones y %d fieles.\n", 
                                  pastorActual.getNombre(), objetivo.getNombre(), objetivo.getRiquezas(), objetivo.getFieles());
        }
        return " -> **Eliminación fallida:** No se encontró un objetivo válido o es el único pastor.\n";
    }
    
    private String ejecutarRescate(Pastor pastorActual) {
        if (!eliminados.vacia()) {
            Pastor regresando = eliminados.peek();
            
            // Traspaso de la mitad de bienes (según tu Main original)
            int riquezasDadas = pastorActual.getRiquezas() / 2;
            int fielesDados = pastorActual.getFieles() / 2;
            
            pastorActual.setRiquezas(pastorActual.getRiquezas() - riquezasDadas);
            pastorActual.setFieles(pastorActual.getFieles() - fielesDados);
            
            regresando.setRiquezas(regresando.getRiquezas() + riquezasDadas);
            regresando.setFieles(regresando.getFieles() + fielesDados);
            
            // Se le da la mitad de lo que tenía ANTES de que el actual lo transfiera.
            // Para el rescate se usa pop() de la pila y add() a la lista.
            eliminados.pop();
            pastores.agregar(regresando);
            
            return String.format(" -> **Rescate Lázaro:** %s rescata a **%s**, cediéndole la mitad de sus bienes (%d doblones, %d fieles).\n", 
                                  pastorActual.getNombre(), regresando.getNombre(), riquezasDadas, fielesDados);
        }
        return " -> **Rescate fallido:** La pila de desposeídos está vacía.\n";
    }

    /**
     * Busca al pastor con menor cantidad de feligreses entre los N vecinos.
     */
    private Pastor buscarPastorAfectar(String direccion, int n) {
        if (nodoActual == null || pastores.getSize() <= 1) return null;
        
        NodoDoble<Pastor> actual = nodoActual;
        Pastor pastorMasEscaso = null;
        int minFeligreses = Integer.MAX_VALUE;
        
        // Si n es mayor que el tamaño de la lista, se limita a size-1 para no contarse.
        int limite = Math.min(n, pastores.getSize() - 1); 

        for (int i = 0; i < limite; i++) {
            // Mover al siguiente nodo en la dirección elegida
            actual = direccion.contains("Diestra") ? actual.getSiguiente() : actual.getAnterior();
            
            Pastor vecino = actual.getDato();
            if (vecino.getFieles() < minFeligreses) {
                minFeligreses = vecino.getFieles();
                pastorMasEscaso = vecino;
            }
        }
        return pastorMasEscaso;
    }

    /**
     * Aplica la regla de vecindad de negocios y reordena si es necesario.
     */
    private void verificarYReordenar() {
        if (pastores.getSize() > 1 && nodoActual != null) {
            // Regla: No tener al mismo negocio a la DERECHA (siguiente)
            if (compNegocio.compare(nodoActual.getDato(), nodoActual.getSiguiente().getDato()) == 0) {
                vista.mostrarMensaje("<!Vecindad Rota!> El pastor actual ("+nodoActual.getDato().getNegocio()+") comparte negocio con su diestra. Reordenando...");
                
                // Usamos el reordenamiento global para asegurar la regla, asumiendo que es el Bubble Sort.
                pastores.reordenar(compNegocio); 
                vista.mostrarMensaje("Mesa reordenada para evitar conflictos de negocios.");
                
                // Después de reordenar, actualizamos el nodoActual a un nodo válido 
                // ya que su posición o incluso el nodo pueden haber cambiado.
                this.nodoActual = pastores.buscar(nodoActual.getDato().getId());
            }
        }
    }

    /**
     * Designa el siguiente pastor de forma aleatoria.
     */
    private void seleccionarSiguienteJugador() {
        if (pastores.getSize() > 1) {
            NodoDoble<Pastor> nuevoNodo;
            do {
                nuevoNodo = pastores.aleatorioNodo();
            } while (nuevoNodo.getDato().equals(nodoActual.getDato())); // Asegura que no se escoja a sí mismo

            this.nodoActual = nuevoNodo;
            vista.mostrarMensaje("El siguiente en tomar la palabra es: " + nodoActual.getDato().getNombre());
        } else {
            this.nodoActual = pastores.getCabeza();
        }
    }
    
    // --- Métodos Getters y Helpers ---
    
    /**
     * Genera pastores con datos aleatorios
     * @param n : La cantidad de pastores a generar
     */
    public void llenar(int n){
        for(int i=0; i<n; i++){
            this.pastores.agregar(new Pastor(titulos[r.nextInt(titulos.length)]+" "+nombres[r.nextInt(nombres.length)], r.nextInt(1000)+100, r.nextInt(1000)+100, roles[r.nextInt(roles.length)], i));
        }
    }
    
    /**
     * Busca un pastor en base a su nombre
     * @param nombre : El nombre del pastor a buscar
     * @return El objeto pastor encontrado, o null si no se encontró 
     */
    public Pastor buscar(String nombre){
        for(Pastor p : this.pastores){
            if(p.getNombre().equalsIgnoreCase(nombre)){
                return p;
            }
        }
        return null;
    }
    
    /**
     * Busca y obtiene al pastor más rico
     * @return Un pastor, el más rico
     */
    public Pastor getMasRico(){
        return this.pastores.maximo(Comparator.comparingInt(Pastor::getRiquezas));
    }
    
    /**
     * Busca y obtiene al pastor más pobre
     * @return Un pastor, el más pobre
     */
    public Pastor getMasPobre(){
        return this.pastores.minimo(Comparator.comparingInt(Pastor::getRiquezas));
    }

    /**
     * Obtiene los pastores en juego y los añade a una lista
     * @return Una lista con los pastores en juego
     */
    public List<Pastor> getPastoresEnMesa() {
        java.util.List<Pastor> lista = new java.util.ArrayList<>();
        for (Pastor p : this.pastores) {
            lista.add(p);
        }
        return lista;
    }
    
    /**
     * Obtiene los pastores eliminados y los añade a una lista
     * @return Una lista con los pastores eliminados
     */
    public List<Pastor> getPastoresEnPila() {
        return eliminados.getElementos(); 
    }
    
    // Métodos de elección aleatoria
    /**
     * Devuelve la eleccion del jugador. Se usa cuando el jugador no es el más pobre
     * @return 1 ó 2
     */
    public int eleccion(){ return r.nextInt(2)+1; } // 1 o 2
    /**
     * Devuelve la eleccion del jugador. Se usa cuando el jugador es el más pobre
     * @return 1, 2 ó 3
     */
    public int eleccionPobre(){ return r.nextInt(3)+1; } // 1, 2 o 3
}