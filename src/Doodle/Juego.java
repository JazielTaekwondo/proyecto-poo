// Clase Juego.java
package Doodle;

import javax.swing.*;

public class Juego extends JFrame {
    private JLayeredPane layeredPane;
    private JLabel labelFondo;
    private JLabel labelPersonaje;

    public Juego(String fondoSeleccionado, String personajeSeleccionado) {
        super("DOODLE JUMP - Juego");
        configurarInterfaz(fondoSeleccionado, personajeSeleccionado);

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void configurarInterfaz(String fondoSeleccionado, String personajeSeleccionado) {

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 600, 800); 
        setContentPane(layeredPane);

        // Configurar el fondo en la capa 0
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource(fondoSeleccionado));
        labelFondo = new JLabel(imagenFondo);
        labelFondo.setBounds(0, 0, 600, 800);
        layeredPane.add(labelFondo, Integer.valueOf(0));

        // Configurar el personaje en la capa 1
        ImageIcon imagenPersonaje = new ImageIcon(getClass().getResource(personajeSeleccionado));
        labelPersonaje = new JLabel(imagenPersonaje);
        labelPersonaje.setBounds(300, 400, imagenPersonaje.getIconWidth(), imagenPersonaje.getIconHeight());
        layeredPane.add(labelPersonaje, Integer.valueOf(1));

        // Actualizar la interfaz (no es necesario en este contexto)
        // validate();
        // repaint();
    }
}
