package Doodle;

import java.awt.*;

public class Plataforma {
    private int x;
    private int y;
    private final int height = 15;
    private final int width = 60;

    public Plataforma(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(100, 255, 100));
        g.fillRect(x, y, width, height);
    }
}
