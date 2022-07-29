import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Cloud {

    private double x;
    private double y;
    private double scale = 0.1;
    private BufferedImage image;

    Cloud(double x, double y, String fileName) {
        this.x = x;
        this.y = y;
        try {
            image = ImageIO.read(getClass().getResource(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void move(int left) {
        x -= left;

    }

    public void resetX(double reset) {
        x = reset;
    }

    public void resetY(double reset) {
        y = reset;
    }

    public void resetScale(double scale) {
        this.scale = scale;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public BufferedImage getImage() {
        return image;
    }

    public double getScale() {
        return scale;
    }
}
