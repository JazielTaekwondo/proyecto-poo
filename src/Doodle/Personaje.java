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
    private final double jumpForce = -9; // Agregamos la fuerza de salto

    private boolean isJumping = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    private List<Plataforma> plataformas = new ArrayList<>();

    /* 
    private final int platformCount = 10;
    private int PlataformasEnPanatalla = 0;
    private double gap;
    private boolean start = true;
    */

    private int translateY = 0;
    private Random random = new Random();

private Timer timer;

    public Personaje() {
        x = 200;
        y = 500; // Establecer la posición inicial en la parte inferior del panel

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

        //generatePlatforms();
        plataformas.add(new Plataforma(100,500));
        plataformas.add(new Plataforma(400,700));
        plataformas.add(new Plataforma(200,400));
        plataformas.add(new Plataforma(150,200));

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

            //plataformasInfinitas();
        }
        
        if (!isJumping) {
            //PlataformasEnPanatalla-=10;
            //plataformasInfinitas();

            //plataformasInfinitas();
        }
        
        if (!isJumping) {
            //plataformasInfinitas();
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

        while(plataformas.size()>=8){
            plataformas.remove(0);
        }

        repaint();
    }


    /** 
    private void plataformasInfinitas(){
        Random random = new Random();
        for (int i = 0; i< plataformas.size(); i++) {
            if(plataformas.get(i).getEnPantalla()==false){
                plataformas.remove(i);
                i--;
                plataformas.add(new Plataforma((int)(100*(random.nextDouble()*4.0)), (int)(-100*(random.nextDouble()*10.0))));
            }
        }
    }
    */
    

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

    /* 
    private void generatePlatforms() {
        Random random = new Random();
        double initialY = (y) + height; // Ajustamos la posición inicial de las plataformas

        plataformas.add(new Plataforma(300, (int)initialY));
        for (int i = 0; i <= 20; i++) {
            plataformas.add(new Plataforma((int)(100*(random.nextDouble()*6.0+1)), (int)(100*(random.nextDouble()*16.0-8.0))));

        }
    }
    */

    private void jump() {
        velocity = jumpForce;
        isJumping = true;
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y < 0) { 
                y = 0;
                velocity = 0;
            }
        }
    }

    public void colisionConPlataforma() {
        boolean move = false;
        for (Plataforma plataforma : plataformas) {
            if (x < plataforma.getX() + plataforma.getWidth() &&
                x + width > plataforma.getX() &&
                y < plataforma.getY() + plataforma.getHeight() &&
                y + height > plataforma.getY() &&            
                velocity>=0) 
            {   
                if(y+height <=500){

                    move = desplazar(300,30);

                    desplazar(300,30);

                }
                velocity = 0;
                jump();
                isJumping = false;
            }
        }
        if(move){
            plataformas.add(new Plataforma(random.nextInt(401),-1));
            plataformas.add(new Plataforma(random.nextInt(401),-500));
            move=!move;
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