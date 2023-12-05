package Doodle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Personaje extends JPanel implements ActionListener {
    private double x;
    private double y;
    private final double height = 60;
    private final double width = 40;

    private double velocity = 0.0;
    private final double gravity = 0.2;
    private final double jumpForce = -9; // Agregamos la fuerza de salto

    private boolean isJumping = false;

    public Personaje() {
        x = 200;
        y = 300;

        Timer timer = new Timer(16, this); // 16ms para aprox. 60fps
        timer.start();

        setFocusable(true); // Permitir que el panel sea focuseable para las teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (!isJumping) {
                        isJumping = true;
                        jump();
                    }
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isJumping) {
            velocity += gravity;
            y += velocity;
            if (y >= 300) { // Cambiar esto según la posición del suelo
                y = 300;
                velocity = 0;
                isJumping = false;
            }
        }

        repaint();
    }

    private void jump() {
        velocity = jumpForce;
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
