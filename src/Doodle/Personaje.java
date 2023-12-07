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
    private final double width = 40;

    private double velocity = 1.0;
    private final double gravity = 0.2;
    private final double jumpForce = -6.5; // Agregamos la fuerza de salto

    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean solido = false;

    private List<Plataforma> plataformas = new ArrayList<>();
    private int no_plataformas = 0;

    /* 
    private final int platformCount = 10;
    private int PlataformasEnPanatalla = 0;
    private double gap;
    private boolean start = true;
    */

    private int translateY = 0;
    private Random random = new Random();

    private JLabel scoreLabel;
    private int puntuacion = 0;

private Timer timer;

    public Personaje() {
        x = 200;
        y = 500; // Establecer la posición inicial en la parte inferior del panel

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

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
                solido = false;
        }

        if (moveLeft) {
            x -= 4;
            if (x + width < 0) {
                x = getWidth();
            }
        }
        if (moveRight) {
            x += 4;
            if (x > getWidth()) {
                x = -width;
            }
        }

        if (y > 700) {
            // Si la posición en y supera 700, detener el juego y mostrar un mensaje
            JOptionPane.showMessageDialog(this, "¡Has perdido!\nPuntuación: " + getPuntuacion());
            
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

        g2d.setColor(Color.BLUE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) x, (int) y, (int) width, (int) height);

        for (Plataforma plataforma : plataformas) {
            plataforma.draw(g2d);
        }
    }

    private void jump() {
        velocity = jumpForce;
        isJumping = true;
        puntuacion++; // Incrementar la puntuación al saltar
        updateScoreLabel(); // Actualizar el texto del label
        /*if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y < 0) { 
                y = 0;
                velocity = 0;
            }
        }*/
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
                desplazar(350,30);
                isJumping = false;
            }
        }
        plataformasInfinitas();
    }

    private void plataformasInfinitas(){
        eliminar();
        int total = 9-no_plataformas;
        for (int i = 0; i < total; i++) { // Generar plataformas en diferentes alturas
            plataformas.add(new Plataforma(random.nextInt(401), -100*i));
            no_plataformas++;
        }
    }

    private boolean desplazar(int totalTranslateY, int totalTranslateX) {

        final int incremento = 10;
        
        // Detenemos el temporizador previo si existe
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isJumping) {
                    for (Plataforma plataforma : plataformas) {
                        plataforma.setT(plataforma.getY() + incremento);
                    }
                }
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Doodler Game with Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 800);
            frame.add(new Personaje());
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        });
    }
}