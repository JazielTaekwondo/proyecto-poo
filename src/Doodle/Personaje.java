package Doodle;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Personaje extends JPanel implements ActionListener {
    private double x;
    private double y;
    private final double height = 60;
    private final double width = 40;

    private double velocity = 1.0;
    private final double gravity = 0.2;
    private final double jumpForce = -5; // Agregamos la fuerza de salto

    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    private List<Plataforma> plataformas = new ArrayList<>();
    private final int platformCount = 10;
    private double gap;

    public Personaje() {
        x = 200;
        y = 200 ; // Establecer la posición inicial en la parte inferior del panel

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

        generatePlatforms();

        setFocusable(true); // Permitir que el panel sea focuseable para las teclas
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!isJumping) {
                        isJumping = true;
                        jump();
                        desplazar(30);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
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

    private void generatePlatforms() {
        gap = getHeight() / platformCount;
        Random random = new Random();
        double initialY = y + height; // Ajustamos la posición inicial de las plataformas
    
        for (int i = 0; i <= platformCount; i++) {
            plataformas.add(new Plataforma(random.nextInt(Math.max(getWidth() - 60, 1)), (int) (initialY + i * gap)));
            //plataformas.add(new Plataforma(random.nextInt(Math.max(getWidth() - 60, 1)), (int) (initialY + i * gap)));
            plataformas.add(new Plataforma((int)(100*(random.nextDouble()*6.0)), (int)(100*(random.nextDouble()*7.0))));
            //initialY +=50;
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y >= 500) { // Cambiar esto según la posición del suelo
                y = 500;
                velocity = 0;
                isJumping = false;
            }
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

        repaint();
    }

    private void jump() {
        velocity = jumpForce;
    }

    private void desplazar(int totalTranslateY) { 
        final int incremento = 2; 
        
        Timer timer = new Timer(2, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int translateY = 0;
                if(isJumping){
                    for (Plataforma plataforma : plataformas) {
                    plataforma.setT(plataforma.getY() + incremento);
                }
                }
                translateY = translateY + incremento;
    
                if (translateY >= totalTranslateY) {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
    
        timer.start(); // Inicia el temporizador
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Doodler Game with Swing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 600);
            frame.add(new Personaje());
            frame.setVisible(true);
        });
    }
}
