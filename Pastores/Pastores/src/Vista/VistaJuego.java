package Vista;

import Controlador.Controlador;
import Modelo.Pastor;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;
import java.awt.geom.Point2D;

public class VistaJuego extends JFrame {

    private final MesaPanel panelMesa;
    private final JPanel panelPila;
    private final JTextArea areaEstado;
    private final JButton btnIniciar;
    private final JButton btnEjecutarTurno;
    private final JComboBox<String> selectorAccion;
    private final JComboBox<String> selectorDireccion;
    private final JTextField inputN;

    public VistaJuego() {
        super("El Torno del Hado y Escuela de Picardías - MVC");
        
        // 1. Inicializar Componentes
        panelMesa = new MesaPanel();
        panelMesa.setPreferredSize(new Dimension(650, 650)); 
        
        panelPila = new JPanel();
        panelPila.setLayout(new BoxLayout(panelPila, BoxLayout.Y_AXIS)); 
        JScrollPane scrollPila = new JScrollPane(panelPila);
        scrollPila.setPreferredSize(new Dimension(300, 600));
        scrollPila.setBorder(BorderFactory.createTitledBorder("Pila de Desposeídos (Cima a Fondo)"));

        areaEstado = new JTextArea(8, 80);
        areaEstado.setEditable(false);
        JScrollPane scrollEstado = new JScrollPane(areaEstado);

        // 2. Inicializar Controles
        btnIniciar = new JButton("Iniciar Juego");
        btnEjecutarTurno = new JButton("Ejecutar Turno");
        btnEjecutarTurno.setEnabled(false);
        
        selectorAccion = new JComboBox<>(new String[]{"Eliminar Vecino", "Rescatar de la Pila", "Hurto (Solo si soy el más pobre)"});
        selectorDireccion = new JComboBox<>(new String[]{"Diestra (Siguiente)", "Siniestra (Anterior)"});
        inputN = new JTextField("3", 4);

        // 3. Configurar Layout
        configurarLayout(scrollPila, scrollEstado);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        mostrarMensaje("¡Bienvenido! Pulse 'Iniciar Juego' para comenzar.");
    }
    
    private void configurarLayout(JScrollPane scrollPila, JScrollPane scrollEstado) {
        setLayout(new BorderLayout(10, 10));

        // Panel de Controles (Norte)
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelControles.setBorder(BorderFactory.createTitledBorder("Acciones del Pastor en Turno"));
        panelControles.add(btnIniciar);
        panelControles.add(new JLabel("Vecinos (n):"));
        panelControles.add(inputN);
        panelControles.add(new JLabel("Dirección:"));
        panelControles.add(selectorDireccion);
        panelControles.add(new JLabel("Acción:"));
        panelControles.add(selectorAccion);
        panelControles.add(btnEjecutarTurno);
        
        add(panelControles, BorderLayout.NORTH);

        // Panel Central: Mesa (Izquierda) y Pila (Derecha)
        JSplitPane splitCentral = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelMesa, scrollPila);
        splitCentral.setDividerLocation(650); 
        add(splitCentral, BorderLayout.CENTER);

        // Área de Estado (Sur)
        add(scrollEstado, BorderLayout.SOUTH);
    }

    // --- Métodos de Actualización (Usados por el Controlador) ---
    
    public void actualizarMesa(List<Pastor> pastores, Pastor pastorEnTurno) {
        panelMesa.setPastores(pastores);
        panelMesa.setPastorEnTurno(pastorEnTurno);
        panelMesa.repaint(); 
    }

    public void actualizarPila(List<Pastor> desposeidos) {
        panelPila.removeAll();
        // Mostrar la pila de arriba (cima) a abajo (fondo)
        for (Pastor p : desposeidos) {
            panelPila.add(crearPanelPastorPila(p));
        }
        panelPila.revalidate();
        panelPila.repaint();
    }
    
    public void mostrarMensaje(String mensaje) {
        // Formateo simple del mensaje
        String textoFormateado = mensaje.replace("**", "<b>").replace("\n", "<br>");
        areaEstado.append(textoFormateado + "\n");
        areaEstado.setCaretPosition(areaEstado.getDocument().getLength());
    }
    
    // --- Getters para el Controlador ---

    public int getN() {
        try {
            return Math.max(1, Integer.parseInt(inputN.getText())); 
        } catch (NumberFormatException e) {
            mostrarMensaje("Advertencia: 'n' debe ser un número entero. Usando 3 por defecto.");
            inputN.setText("3");
            return 3;
        }
    }
    
    public String getDireccionElegida() { return (String) selectorDireccion.getSelectedItem(); }
    public String getAccionElegida() { return (String) selectorAccion.getSelectedItem(); }
    public JButton getBtnIniciar() { return btnIniciar; }
    public JButton getBtnEjecutarTurno() { return btnEjecutarTurno; }
    
    // --- Auxiliares ---
    
    private JPanel crearPanelPastorPila(Pastor p) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        panel.setMaximumSize(new Dimension(300, 45));
        panel.setMinimumSize(new Dimension(300, 45));
        panel.add(new JLabel(String.format("<html><b>%s</b><br>R: %d | F: %d | O: %s</html>", 
                                            p.getNombre().split(" ")[0], p.getRiquezas(), p.getFieles(), p.getNegocio())));
        return panel;
    }
}

/**
 * Clase interna para dibujar la mesa circular.
 */
class MesaPanel extends JPanel {
    private List<Pastor> pastores;
    private Pastor pastorEnTurno;
    private static final int RADIO = 280; 
    private static final int TAMAÑO_PASTOR = 100; 

    public void setPastores(List<Pastor> pastores) { this.pastores = pastores; }
    public void setPastorEnTurno(Pastor pastorEnTurno) { this.pastorEnTurno = pastorEnTurno; }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // 1. Dibujar la Mesa Redonda
        g2d.setColor(new Color(139, 69, 19)); 
        g2d.fillOval(centerX - RADIO, centerY - RADIO, 2 * RADIO, 2 * RADIO);
        
        if (pastores == null || pastores.isEmpty()) return;

        int numPastores = pastores.size();
        double angleStep = 2 * Math.PI / numPastores;
        
        for (int i = 0; i < numPastores; i++) {
            Pastor p = pastores.get(i);
            // El ángulo de inicio -PI/2 asegura que el primer pastor esté arriba (12 en el reloj)
            double angle = i * angleStep - Math.PI / 2; 

            // Coordenadas del centro del área del pastor
            int x = (int) (centerX + RADIO * 0.9 * Math.cos(angle) - TAMAÑO_PASTOR / 2);
            int y = (int) (centerY + RADIO * 0.9 * Math.sin(angle) - TAMAÑO_PASTOR / 2);

            // 3. Dibujar las "manos" (conexiones circulares)
            if (numPastores > 1) {
                int nextIndex = (i + 1) % numPastores;
                double nextAngle = nextIndex * angleStep - Math.PI / 2;
                
                int x1 = (int) (centerX + RADIO * 0.9 * Math.cos(angle));
                int y1 = (int) (centerY + RADIO * 0.9 * Math.sin(angle));
                int x2 = (int) (centerX + RADIO * 0.9 * Math.cos(nextAngle));
                int y2 = (int) (centerY + RADIO * 0.9 * Math.sin(nextAngle));
                
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.setStroke(new BasicStroke(2));
                g2d.drawLine(x1, y1, x2, y2);
            }
            
            // 4. Crear y dibujar el "Panel" del Pastor
            crearYDibujarPastor(g2d, p, x, y);
        }
    }

    private void crearYDibujarPastor(Graphics2D g2d, Pastor p, int x, int y) {
        boolean esTurno = (p.equals(pastorEnTurno));
        
        // Fondo
        g2d.setColor(esTurno ? new Color(255, 255, 153) : Color.WHITE); // Amarillo pálido para turno
        g2d.fillRect(x, y, TAMAÑO_PASTOR, TAMAÑO_PASTOR);
        
        // Borde
        g2d.setColor(esTurno ? new Color(204, 0, 0) : Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(esTurno ? 4 : 1));
        g2d.drawRect(x, y, TAMAÑO_PASTOR, TAMAÑO_PASTOR);
        
        // Información del Pastor
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 11));
        g2d.drawString(p.getNombre().split(" ")[0], x + 5, y + 15);
        
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.drawString("Rol: " + p.getNegocio(), x + 5, y + 30);
        g2d.drawString("Doblones: " + p.getRiquezas(), x + 5, y + 50);
        g2d.drawString("Fieles: " + p.getFieles(), x + 5, y + 65);
        
        // Etiqueta de Turno
        if (esTurno) {
            g2d.setColor(new Color(204, 0, 0));
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.drawString("¡TURNO!", x + 15, y + 90);
        }
    }
}