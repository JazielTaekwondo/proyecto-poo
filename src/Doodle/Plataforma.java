package Doodle;

import java.awt.*;

import javax.swing.ImageIcon;

//import javax.swing.JFrame;

public class Plataforma {
    private int x;
    private int y;
    private final int height = 20;
    private final int width = 60;
    private boolean enPantalla;

    private ImageIcon plataformaIcon;

    public Plataforma(int x, int y) {
        this.x = x;
        this.y = y;
        this.enPantalla=true;

        plataformaIcon = new ImageIcon(getClass().getResource("/images/plataforma.png"));
    }

    public int getY(){return y;}

    public int getX(){return x;}

    public int getHeight(){return height;}

    public int getWidth(){return width;}

    public void setT(int y){
        this.y = y;
    }

    public void draw(Graphics g) {
        Image plataformaImage = plataformaIcon.getImage();
        g.drawImage(plataformaImage, x, y, width, height, null);
        /*g.setColor(new Color(100, 255, 100));
        g.fillRect(x, y, width, height);*/
    }

    public boolean getEnPantalla(){
        if(this.y<=800 ){
            return enPantalla;
        }
        return false;
    }
}