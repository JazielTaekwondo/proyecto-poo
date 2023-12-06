package Doodle;

import java.awt.*;

public class Plataforma {
    private int x;
    private int y;
    private final int height = 15;
    private final int width = 60;
    private boolean enPantalla;

    public Plataforma(int x, int y) {
        this.x = x;
        this.y = y;
        this.enPantalla=true;
    }

    public int getY(){return y;}

    public int getX(){return x;}

    public int getHeight(){return height;}

    public int getWidth(){return width;}

    public void setT(int y){
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(100, 255, 100));
        g.fillRect(x, y, width, height);
    }

    public boolean getEnPantalla(){
        if(this.y<=800 ){
            return enPantalla;
        }
        return false;
    }
}
