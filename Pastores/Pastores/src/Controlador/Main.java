package Controlador;

import Vista.VistaJuego;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Se ejecuta la inicialización de la GUI en el Event Dispatch Thread (EDT) de Swing
        SwingUtilities.invokeLater(() -> {
            
            // 1. Crear el Controlador y el Modelo
            Controlador controlador = new Controlador();
            
            // 2. Crear la Vista
            VistaJuego vista = new VistaJuego();
            
            // 3. Conectar el Controlador con la Vista 
            controlador.setVista(vista);
            
            // Mostrar la ventana
            vista.setVisible(true);
        });
    }
}