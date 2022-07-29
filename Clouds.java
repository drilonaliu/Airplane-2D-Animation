import java.util.Random;

public class Clouds {

    private Cloud[] clouds;
    private double frame_width;
    private double frame_height;
    private int[] cloudMovement = { 10, 15, 20 };

    Clouds(double frame_width, double frame_height) {
        this.frame_width = frame_width;
        this.frame_height = frame_height;
        clouds = new Cloud[3];
        clouds[0] = new Cloud(frame_width, -frame_height / 2, "Clouds/Cloud0.png");
        clouds[1] = new Cloud(frame_width, 0, "Clouds/Cloud1.png");
        clouds[2] = new Cloud(frame_width, frame_height / 3, "Clouds/Cloud2.png");

    }

    public void moveAll() {
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].move(cloudMovement[i]);
            // Reseto rete 
            if (clouds[i].getX() < -frame_width) {
                clouds[i].resetX(frame_width / 2);
                clouds[i].resetY(getRandomSign() * Math.random() * frame_height / 3);
                clouds[i].resetScale(getRandomScale());
            }
        }

    }


    //Largo rete nga frame
    public void noMoreClouds() {
        for (int i = 0; i < clouds.length; i++) {
            clouds[i].move(cloudMovement[i]);
            // reset
            if (clouds[i].getX() < -frame_width-100) {
                clouds[i].resetX(frame_width / 2);
                clouds[i].resetY(frame_height);
                clouds[i].resetScale(getRandomScale());
            }
        }
    }

    public double getRandomScale() {
        return getRandomNumberUsingNextInt(0, 3) / 10.0;
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void moveCloud(int i) {
        clouds[i].move(10);
    }

    public Cloud[] getClouds() {
        return clouds;
    }

    public Cloud getCloud(int i) {
        return clouds[i];
    }

    private int getRandomSign() {
        if (Math.random() > 0.5) {
            return 1;
        } else {
            return -1;
        }
    }
}
