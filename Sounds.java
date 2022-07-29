import java.io.IOException;
import java.io.*;
import java.net.MalformedURLException;
import javax.sound.sampled.*;

public class Sounds {

    public static void playSound(String soundFile, boolean loop)
            throws MalformedURLException, UnsupportedAudioFileException, IOException, LineUnavailableException {
                
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        if (loop) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        clip.start();
    }

}
