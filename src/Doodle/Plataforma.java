package Doodle;
import java.awt.*;
import javax.swing.ImageIcon;


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

    public void setY(int y){
        this.y = y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void draw(Graphics g) {
        Image plataformaImage = plataformaIcon.getImage();
        g.drawImage(plataformaImage, x, y, width, height, null);
    }

    public boolean getEnPantalla(){
        if(this.y<=800 ){
            return enPantalla;
        }
        return false;
    }
}

class PlataformaPlus extends Plataforma{
    private ImageIcon plataformaIcon;
    public PlataformaPlus(int x, int y){
        super(x, y);
        plataformaIcon = new ImageIcon(getClass().getResource("/images/impulso.png"));
    }
    public void draw(Graphics g) {
        Image plataformaImage = plataformaIcon.getImage();
        g.drawImage(plataformaImage, getX(), getY(), getWidth(), getHeight(), null);
    }
}
