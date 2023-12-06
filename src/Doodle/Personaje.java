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
    private final double jumpForce = -9; // Agregamos la fuerza de salto

    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    private List<Plataforma> plataformas = new ArrayList<>();
    private final int platformCount = 10;
    private int PlataformasEnPanatalla = 0;
    private double gap;
    private boolean start = true;
    private int translateY = 0;

private Timer timer;

    public Personaje() {
        x = 200;
        y = 500; // Establecer la posición inicial en la parte inferior del panel

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

        generatePlatforms();

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (isJumping) {
            velocity += gravity;
            y += velocity;
        }
        
        if (!isJumping) {
            PlataformasEnPanatalla-=10;
            plataformasInfinitas();
            jump();
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

        colisionConPlataforma();

        repaint();
    }

    private void plataformasInfinitas(){
        Random random = new Random();
        int generar = plataformasFueraDePantalla();
        for (int i = 0; i < generar; i++) {
            if (PlataformasEnPanatalla <= platformCount){
                //plataformas.add(new Plataforma(random.nextInt(Math.max(getWidth() - 60, 1)), (int) (initialY + i * gap)));
                plataformas.add(new Plataforma((int)(100*(random.nextDouble()*4.0)), i*(int)(-200*(random.nextDouble()*1.0-2))));
                PlataformasEnPanatalla +=1;
            }
        }
    }
    
    private int plataformasFueraDePantalla(){
        int generar=0;
        for (int i = 0; i< plataformas.size(); i++) {
            if(!plataformas.get(i).getEnPantalla()){
                generar++;
            }
        }
        return generar;
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
        double initialY = (y) + height; // Ajustamos la posición inicial de las plataformas
        start = false;
        plataformas.add(new Plataforma(300, (int)initialY));
        for (int i = 0; i <= 8; i++) {
            plataformas.add(new Plataforma((int)(100*(random.nextDouble()*4.0)), (int)(100*(random.nextDouble()*1.0*i))));
            PlataformasEnPanatalla +=1;
        }
    }

    private void jump() {
        velocity = jumpForce;
        isJumping = true;
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y < 100) { 
                y = 100;
                velocity = 0;
            }
        }
    }

    public void colisionConPlataforma() {
        for (Plataforma plataforma : plataformas) {
            if (x < plataforma.getX() + plataforma.getWidth() &&
                x + width > plataforma.getX() &&
                y < plataforma.getY() + plataforma.getHeight() &&
                y + height > plataforma.getY() &&            
                velocity>=0) 
            {   
                if(y+height <=500){
                    desplazar(300,30);
                }
                velocity = 0;
                jump();
                isJumping = false;
            }
        }
    }

    private void desplazar(int totalTranslateY, int totalTranslateX) {
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