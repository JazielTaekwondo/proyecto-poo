import Doodle.*;

public class Doodle {
    public static void main(String[] args) {
        Botones juego = new Botones();
        juego.setBounds(0, 0, 600, 800);
        juego.setResizable(false);
        juego.setLocationRelativeTo(null);
        juego.setVisible(true);
    }
}
