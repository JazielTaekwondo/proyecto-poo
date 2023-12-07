package Doodle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

//import java.util.Random;

public class Personaje extends JPanel implements ActionListener {
    private double x;
    private double y;
    private final double height = 60;
    private final double width = 60;

    private double velocity = 1.0;
    private final double gravity = 0.2;
    private final double jumpForce = -6.5; // Agregamos la fuerza de salto

    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean solido = false;

    private List<Plataforma> plataformas = new ArrayList<>();
    private int no_plataformas = 0;

    private String personajeSeleccionadoL;
    private String personajeSeleccionadoR;
    private String fondoSeleccionado;
    private boolean mirandoizquierda = false;

    private Musica M = new Musica();

    private int translateY = 0;
    private Random random = new Random();

    private JLabel scoreLabel;
    private int puntuacion = 0;
    private PlataformaPlus impulso;
    
    private Timer timer;

    public Personaje(String personajeSeleccionadoL, String personajeSeleccionadoR, String fondoSeleccionado) {
        this.personajeSeleccionadoL = personajeSeleccionadoL;
        this.personajeSeleccionadoR = personajeSeleccionadoR;
        this.fondoSeleccionado = fondoSeleccionado;
        impulso = new PlataformaPlus(random.nextInt(401), -1);
        x = 200;
        y = 500; // Establecer la posición inicial en la parte inferior del panel

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

        M.ReproducirMusic("/sounds/Zelda.wav");
        //generatePlatforms();
        plataformas.add(new Plataforma(200,450));
        plataformas.add(new Plataforma(400,300));
        plataformas.add(new Plataforma(200,200));
        plataformas.add(new Plataforma(150,100));
        //no_plataformas+=5;

        setFocusable(true); // Permitir que el panel sea focuseable para las teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveLeft = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveRight = false;
                }
            }
        });

        scoreLabel = new JLabel("Puntuación: 0");
        scoreLabel.setForeground(Color.WHITE); // Color del texto
        scoreLabel.setBounds(10, 10, 150, 30); // Posición y tamaño del label
        add(scoreLabel); // Agrega el label al panel

        updateScoreLabel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            solido = true;
            //plataformasInfinitas();
        }
        
        /*if (!isJumping && y <= 150) {
            velocity += 100;
            y += velocity;
            //if (y > 150){
              //  isJumping = false;
            //}
        }*/
        
        if (!isJumping) { // salto
                jump();
                M.reproducirSonido("/sounds/jump.wav");
                solido = false;
        }

        if (moveLeft) {
            mirandoizquierda = true; // Establecer la dirección a izquierda
            x -= 4;
            if (x + width < 0) {
                x = getWidth();
            }
        }
        if (moveRight) {
            mirandoizquierda = false; // Establecer la dirección a derecha
            x += 4;
            if (x > getWidth()) {
                x = -width;
            }
        }

        if (y > 750) {
            // Si la posición en y supera 700, detener el juego y mostrar un mensaje
            M.reproducirSonido("/sounds/pada.wav");
            JOptionPane.showMessageDialog(this,
            "<html><body style='width: 230px; text-align: center;'>" +
            "<p style='font-size: 16px; color: blue;'><b>¡Has perdido!</b></p>" +
            "<p style='font-size: 14px;'>Puntuación: " + getPuntuacion() + "</p>" +
            "</body></html>",
            "Mensaje de Pérdida", JOptionPane.INFORMATION_MESSAGE);
            
            System.exit(0); // Salir del juego
        }

            colisionConPlataforma();

        repaint();
    }

    private void eliminar(){
        for (int i = 0; i< plataformas.size(); i++) {
            if(plataformas.get(i).getEnPantalla()==false){
                plataformas.remove(i);
                i--;
                no_plataformas--;
            }
        }
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    
        // Cargar la imagen de fondo
        ImageIcon fondoIcon = new ImageIcon(getClass().getResource(fondoSeleccionado));
        Image fondoImage = fondoIcon.getImage();
    
        // Dibujar la imagen de fondo
        g2d.drawImage(fondoImage, 0, 0, getWidth(), getHeight(), this);
    
        // Seleccionar la imagen del personaje basándote en la dirección
        Image personajeImage;
        if (mirandoizquierda) {
            ImageIcon personajeIconL = new ImageIcon(getClass().getResource(personajeSeleccionadoL));
            personajeImage = personajeIconL.getImage();
        } else {
            ImageIcon personajeIconR = new ImageIcon(getClass().getResource(personajeSeleccionadoR));
            personajeImage = personajeIconR.getImage();
        }
    
        // Dibujar la imagen del personaje
        g2d.drawImage(personajeImage, (int) x, (int) y, (int) width, (int) height, this);
    
        // Dibujar las plataformas
        for (Plataforma plataforma : plataformas) {
            plataforma.draw(g2d);
        }
        impulso.draw(g2d);
    }

   private void jump() {
        velocity = jumpForce;
        isJumping = true;
        puntuacion++; // Incrementar la puntuación al saltar
        updateScoreLabel(); // Actualizar el texto del label
    }
    private void jumpPlus() {
        velocity = -22.0;
        isJumping = true;
        puntuacion+=5; // Incrementar la puntuación al saltar
        updateScoreLabel(); // Actualizar el texto del label
    }

    public void colisionConPlataforma() {
        //boolean move = false;
        for (Plataforma plataforma : plataformas) {
            if (x < plataforma.getX() + plataforma.getWidth() &&
                x + width > plataforma.getX() &&
                y < plataforma.getY() + plataforma.getHeight() &&
                y + height > plataforma.getY() &&            
                velocity>=0 && y > 100 && solido) 
            {   
                if(y+height <=500){
                }
                velocity = 0;
                jump();
                desplazar(300);
                isJumping = false;
            }
        }
        if (x < impulso.getX() + impulso.getWidth() &&
                x + width > impulso.getX() &&
                y < impulso.getY() + impulso.getHeight() &&
                y + height > impulso.getY() &&            
                velocity>=0 && y > 100 && solido) 
            {   
                velocity = 0;
                jumpPlus();
                desplazar(800);
                isJumping = false;
            }
        plataformasInfinitas();
    }

    private void plataformasInfinitas(){
        eliminar();
        int total = 8-no_plataformas;
        for (int i = 0; i < total; i++) { // Generar plataformas en diferentes alturas
            plataformas.add(new Plataforma(random.nextInt(401), -100*i));
            no_plataformas++;
        }
        if(impulso.getEnPantalla()==false){
            impulso.setX(random.nextInt(401));
            impulso.setY(-1000);
        }
    }

    private boolean desplazar(int totalTranslateY) {

        final int incremento = 10;
        
        // Detenemos el temporizador previo si existe
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    
        timer = new Timer(12, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isJumping) {
                    for (Plataforma plataforma : plataformas) {
                        plataforma.setY(plataforma.getY() + incremento);
                    }
                }
                impulso.setY(impulso.getY()+incremento);
                translateY += incremento;
    
                if (translateY >= totalTranslateY) {
                    ((Timer) e.getSource()).stop();
                    translateY = 0; // Reiniciamos la variable para la próxima llamada
                }
            }
        });
        timer.start();

        return true;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Puntuación: " + getPuntuacion());
    }
}