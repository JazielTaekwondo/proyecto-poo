package Doodle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Botones extends JFrame implements ActionListener {

    JButton inicio;
    JButton salir;

    public Botones(){
        super("DOODLE JUMP");
        setLayout(null);

        inicio = new JButton();
        salir = new JButton();

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
            Seleccion a = new Seleccion();
            a.setBounds(0, 0, 600, 800);
            a.setResizable(false);
            a.setLocationRelativeTo(null);
            a.setVisible(true);
            //mostrarSeleccionVentana();
            this.setVisible(false);
        }
        if (e.getSource() == salir) {
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

    public Seleccion(){
        super("DOODLE JUMP");
        setLayout(null);

        personaje1 = new JButton();
        personaje2 = new JButton();
        personaje3 = new JButton();
        fondo1 = new JButton();
        fondo2 = new JButton();
        fondo3 = new JButton();
        play = new JButton();
        menu = new JButton();
        

        ImageIcon fotoBoton = new ImageIcon(getClass().getResource("/images/BadSmily.png"));
        ImageIcon fotoBoton2 = new ImageIcon(getClass().getResource("/images/SmilyL.png"));
        ImageIcon fotoBoton3 = new ImageIcon(getClass().getResource("/images/boton3.png"));
        ImageIcon fotoBoton4 = new ImageIcon(getClass().getResource("/images/boton4.png"));
        ImageIcon fotoBoton5 = new ImageIcon(getClass().getResource("/images/PunkSmily.png"));
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

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/Smily.png"));
        Image iconImage = icon.getImage();
        setIconImage(iconImage);

        JLabel texto1 = new JLabel("MAPAS");
        texto1.setBounds(285, 150, 400, 140); 
        JLabel texto2 = new JLabel("PERSONAJES");
        texto2.setBounds(265, 350, 400, 140); 
        texto1.setForeground(Color.WHITE);
        texto2.setForeground(Color.WHITE);
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
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==personaje1){
            //mostrarSeleccionVentana();
            this.setVisible(false);
        }
        if (e.getSource() == personaje2) {
            System.exit(0);
        }
        
    }
}