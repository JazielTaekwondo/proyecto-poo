package Doodle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botones extends JFrame implements ActionListener {

    JButton inicio;
    JButton salir;
    Musica M = new Musica();

    public Botones(){
        super("DOODLE JUMP-Menu");
        setLayout(null);

        inicio = new JButton();
        salir = new JButton();

        M.ReproducirMusic("/sounds/MusicMario.wav");

        ImageIcon fotoBoton = new ImageIcon(getClass().getResource("/images/BotonInicio.png"));
        ImageIcon fotoBoton2 = new ImageIcon(getClass().getResource("/images/BotonSalida.png"));

        inicio.setBounds(150, 430, 100, 40);
        salir.setBounds(350, 430, 100, 40);

        inicio.setIcon(fotoBoton);
        salir.setIcon(fotoBoton2);

        add(inicio);
        add(salir);
        
        inicio.addActionListener(this);
        salir.addActionListener(this);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Smily.png"));
        Image iconImage = icon.getImage();
        setIconImage(iconImage);

        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/images/FondoPantallaDoodle.png"));
        JLabel labelFondo = new JLabel(imagenFondo);
        labelFondo.setBounds(0, 0, 600, 900); // Establece tamaño y posición del fondo
        add(labelFondo, Integer.valueOf(0));

        setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight()); // Establece el tamaño del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==inicio){
            Seleccion a = new Seleccion(this); // Pasa la referencia de la ventana actual
            a.setBounds(0, 0, 600, 800);
            a.setResizable(false);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
            //mostrarSeleccionVentana();
            this.setVisible(false);
            M.reproducirSonido("/sounds/jump.wav");
        }
        if (e.getSource() == salir) {
            M.reproducirSonido("/sounds/pada.wav");
            System.exit(0);
        }
    }
}

class Seleccion extends JFrame implements ActionListener {

    JButton personaje1;
    JButton personaje2;
    JButton personaje3;
    JButton fondo1;
    JButton fondo2;
    JButton fondo3;
    JButton play;
    JButton menu;
    Musica M = new Musica();

    private JFrame ventanaAnterior;

    public Seleccion(JFrame ventanaAnterior){
        super("DOODLE JUMP-Seleccion");
        setLayout(null);

        this.ventanaAnterior = ventanaAnterior;

        personaje1 = new JButton();
        personaje2 = new JButton();
        personaje3 = new JButton();
        fondo1 = new JButton();
        fondo2 = new JButton();
        fondo3 = new JButton();
        play = new JButton();
        menu = new JButton();
        

        ImageIcon fotoBoton = new ImageIcon(getClass().getResource("/images/BadSmilyR.png"));
        ImageIcon fotoBoton2 = new ImageIcon(getClass().getResource("/images/SmilyL.png"));
        ImageIcon fotoBoton3 = new ImageIcon(getClass().getResource("/images/boton4.png"));
        ImageIcon fotoBoton4 = new ImageIcon(getClass().getResource("/images/boton3.png"));
        ImageIcon fotoBoton5 = new ImageIcon(getClass().getResource("/images/PunkSmilyR.png"));
        ImageIcon fotoBoton6 = new ImageIcon(getClass().getResource("/images/boton6.png"));
        ImageIcon fotoBoton7 = new ImageIcon(getClass().getResource("/images/botonplay.png"));
        ImageIcon fotoBoton8 = new ImageIcon(getClass().getResource("/images/botonmenu.png"));
        

        personaje1.setBounds(78, 430, 96, 96);
        personaje2.setBounds(256, 430, 96, 96);
        personaje3.setBounds(426, 430, 96, 96);
        fondo1.setBounds(78, 230, 96, 96);
        fondo2.setBounds(256, 230, 96, 96);
        fondo3.setBounds(426, 230, 96, 96);
        play.setBounds(200, 600, 50, 40);
        menu.setBounds(350, 600, 50, 40);

        personaje1.setIcon(fotoBoton);
        personaje2.setIcon(fotoBoton2);
        personaje3.setIcon(fotoBoton5);
        fondo1.setIcon(fotoBoton3);
        fondo2.setIcon(fotoBoton4);
        fondo3.setIcon(fotoBoton6);
        play.setIcon(fotoBoton7);
        menu.setIcon(fotoBoton8);

        add(fondo1);
        add(fondo2);
        add(fondo3);
        add(personaje1);
        add(personaje2);
        add(personaje3);
        add(play);
        add(menu);
        
        personaje1.addActionListener(this);
        personaje2.addActionListener(this);
        personaje3.addActionListener(this);
        fondo1.addActionListener(this);
        fondo2.addActionListener(this);
        fondo3.addActionListener(this);
        play.addActionListener(this);
        menu.addActionListener(this);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Smily.png"));
        Image iconImage = icon.getImage();
        setIconImage(iconImage);

        // Crear una fuente personalizada (puedes ajustar el estilo y el tamaño según tus preferencias)
        Font fuente = new Font("Century Gothic", Font.ITALIC+Font.BOLD, 28);

        // Crear los JLabels con el texto y la fuente personalizada
        JLabel texto1 = new JLabel("M A P A S");
        texto1.setBounds(240, 130, 400, 140);
        texto1.setFont(fuente);
        texto1.setForeground(Color.WHITE);

        JLabel texto2 = new JLabel("P E R S O N A J E S");
        texto2.setBounds(180, 330, 400, 140);
        texto2.setFont(fuente);
        texto2.setForeground(Color.WHITE);

        // Añadir los JLabels al contenedor
        add(texto1);
        add(texto2);

        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/images/fondoseleccion.png"));
        JLabel labelFondo = new JLabel(imagenFondo);
        labelFondo.setBounds(0, 0, 600, 900);
        add(labelFondo, Integer.valueOf(0));

        setSize(imagenFondo.getIconWidth(), imagenFondo.getIconHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    // Variables para almacenar las selecciones del usuario
    private String fondoSeleccionado;
    private String personajeSeleccionadoL;
    private String personajeSeleccionadoR;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == personaje1) {
            // Lógica para el botón del personaje1
            personajeSeleccionadoL = "/images/BadSmilyL.png"; // Reemplazar con la lógica real
            personajeSeleccionadoR = "/images/BadSmilyR.png";
            M.reproducirSonido("/sounds/jump.wav");
            //System.out.println("Personaje seleccionado: " + personajeSeleccionado);
        }
        if (e.getSource() == personaje2) {
            // Lógica para el botón del personaje2
            personajeSeleccionadoL = "/images/SmilyL.png"; // Reemplazar con la lógica real
            personajeSeleccionadoR = "/images/SmilyR.png";
            M.reproducirSonido("/sounds/jump.wav");
            //System.out.println("Personaje seleccionado: " + personajeSeleccionado);
        }
        if (e.getSource() == personaje3) {
            // Lógica para el botón del personaje3
            personajeSeleccionadoL = "/images/PunkSmilyL.png"; // Reemplazar con la lógica real
            personajeSeleccionadoR = "/images/PunkSmilyR.png"; // Reemplazar con la lógica real
            M.reproducirSonido("/sounds/jump.wav");
            //System.out.println("Personaje seleccionado: " + personajeSeleccionado);
        }
        if (e.getSource() == fondo1) {
            // Lógica para el botón del fondo1
            fondoSeleccionado = "/images/fondo.png"; // Reemplazar con la lógica real
            M.reproducirSonido("/sounds/jump.wav");
            //System.out.println("Fondo seleccionado: " + fondoSeleccionado);
        }
        if (e.getSource() == fondo2) {
            // Lógica para el botón del fondo2
            fondoSeleccionado = "/images/fondo2.jpg"; // Reemplazar con la lógica real
            M.reproducirSonido("/sounds/jump.wav");
            //System.out.println("Fondo seleccionado: " + fondoSeleccionado);
        }
        if (e.getSource() == fondo3) {
            // Lógica para el botón del fondo3
            fondoSeleccionado = "/images/fondo3.png"; // Reemplazar con la lógica real
            M.reproducirSonido("/sounds/jump.wav");
           // System.out.println("Fondo seleccionado: " + fondoSeleccionado);
        }
        if (e.getSource() == play) {
            M.reproducirSonido("/sounds/jump.wav");
            // Lógica para el botón de play
            //System.out.println("Botón Play presionado");
            
            // Verificar que se hayan seleccionado un personaje y un fondo
            if (personajeSeleccionadoL != null && fondoSeleccionado != null) {
                M.StopMusic();
                 // Crear una instancia de la clase Personaje y pasarle los parámetros
                Personaje jugador = new Personaje(personajeSeleccionadoL, personajeSeleccionadoR, fondoSeleccionado);

                // Configurar la ventana del juego con el personaje
                JFrame ventanaJuego = new JFrame("DOODLE JUMP - Juego");
                ventanaJuego.add(jugador);  // Agregar el panel del personaje a la ventana
                ventanaJuego.setSize(600, 800);
                ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaJuego.setLocationRelativeTo(null);
                ventanaJuego.setVisible(true);

                // Ocultar la ventana de selección
                this.setVisible(false);
            } else {
    // Mensaje de advertencia si no se han seleccionado ambos
                String mensaje = "<html><body style='width: 200px; text-align: center;'><p style='font-size: 14px; color: blue;'><b>Por favor, selecciona un personaje y un fondo.</b></p></body></html>";
                JOptionPane.showMessageDialog(this, mensaje, "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == menu) {
            // Lógica para el botón de menu
            M.reproducirSonido("/sounds/jump.wav");
            // Mostrar la ventana anterior y ocultar la actual
            ventanaAnterior.setVisible(true);
            this.setVisible(false);
        }
    }
}