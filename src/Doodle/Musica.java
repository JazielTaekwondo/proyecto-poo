package Doodle;

import java.io.IOException;

import javax.sound.sampled.*;

public class Musica{

    private static Clip clip;
    private Clip clip2;

    public void ReproducirMusic(String Ruta) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(Ruta));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void StopMusic(){

        clip.stop();
        clip.close();
    }

    public void reproducirSonido(String Ruta) {
        try {
            // Cargar el archivo de sonido
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(Ruta));
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream);

            // Reproducir el sonido
            clip2.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}