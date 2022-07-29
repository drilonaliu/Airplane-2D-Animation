import java.awt.image.*;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.imageio.ImageIO;

public class Plane {

    private double x;
    private double y;
    private BufferedImage planeIcon;
    private double scale;
    private double width;
    private double y_constant;
    private boolean tookOff = false;
    private boolean plane_steady = false;
    private boolean case1 = true;
    private boolean sound1playing = false;

    Plane(double x, double y, double scale, double width) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.width = width;

        try {
            planeIcon = ImageIO.read(getClass().getResource("PlaneImages/PlaneDrawing.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void movePlane() {
        if (!plane_steady) {
            takeOff();
        } else {
            steadyFlight();
        }
    }

    private void takeOff() {

        if (!sound1playing) {
            playTakeOffSound();
            sound1playing = true;
        }

        double sc = scale;
        if (x < 300 && !tookOff) {
            x += 9;
            y = -sc * Math.pow(2, x / sc); // funksioni 1

            if (x >= 0) {
                y = -sc * (1 + Math.log10((x / sc) + 1)); // funksioni 2
            }
        } else {
            tookOff = true;
            {
                if (x > -width / 3) {
                    x -= 5;
                } else {
                    plane_steady = true;
                    y_constant = y;
                }
            }
        }
    }

    private void steadyFlight() {
        if (sound1playing) {
            playConstantSound();
            sound1playing = false;
        }
        if (y > y_constant - 50 && case1) {
            y -= 1;
        } else {
            case1 = false;
        }

        if (y < y_constant + 50 && !case1) {
            y += 1;
        } else {
            case1 = true;
        }
    }

    public void flyAway() {
        x = x + 20;
    }

    private void playTakeOffSound() {
        try {
            Sounds.playSound("Sounds/takeOffSound.wav", false);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    private void playConstantSound() {
        try {
            Sounds.playSound("Sounds/planeSound1.wav", true);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean flewAway() {
        return x > width;
    }

    public BufferedImage getPlaneIcon() {
        return planeIcon;
    }

}
