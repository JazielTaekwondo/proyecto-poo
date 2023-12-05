// Clase Juego.java
package Doodle;

import javax.swing.*;

public class Juego extends JFrame {

    private JLabel labelFondo;
    private JLabel labelPersonaje;

    public Juego(String fondoSeleccionado, String personajeSeleccionado) {
        super("DOODLE JUMP - Juego");
        
        configurarInterfaz(fondoSeleccionado, personajeSeleccionado);

        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void configurarInterfaz(String fondoSeleccionado, String personajeSeleccionado) {
        // Configurar el fondo
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource(fondoSeleccionado));
        labelFondo = new JLabel(imagenFondo);
        labelFondo.setBounds(0, 0, imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        add(labelFondo);

        // Configurar el personaje
        ImageIcon imagenPersonaje = new ImageIcon(getClass().getResource(personajeSeleccionado));
        labelPersonaje = new JLabel(imagenPersonaje);
        labelPersonaje.setBounds(300, 600, imagenPersonaje.getIconWidth(), imagenPersonaje.getIconHeight());
        add(labelPersonaje);

        // Actualizar la interfaz
        validate();
        repaint();
    }
}

