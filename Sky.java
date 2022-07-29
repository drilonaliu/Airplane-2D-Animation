import java.util.Stack;
import java.awt.geom.*;
import java.awt.*;

public class Sky {

    private int frame_width;
    private int frame_height;
    private boolean day = true;
    private boolean night = false;
    private boolean sunrise = false;
    private int frame = 0;
    private Stack<Area> stars = new Stack<>();
    private Stack<Color> day_colors = new Stack<>();
    private Stack<Color> sunset_colors = new Stack<>();
    private Color c1 = new Color(128, 217, 255);
    private Color c2 = new Color(179, 218, 233);

    Sky(int frame_width, int frame_height) {
        this.frame_width = frame_width;
        this.frame_height = frame_height;
    }

    public void changeTime(Graphics2D g2) {

        Rectangle r = new Rectangle(0, 0, frame_width, frame_height);

        GradientPaint gp = new GradientPaint(frame_width / 2, 0, c1, frame_width / 2, frame_height, c2);
        g2.setPaint(gp);
        g2.fill(r);

        int c = 500;
        if (day) {
            sunrise = false;
            frame += 1;
            if (frame % 100 == 0) {
                day_colors.push(c1);
                c1 = c1.darker();
            }
            if (frame > c) {
                day = false;
                night = true;
                frame = 0;

            }
        }

        if (night) {
            frame += 1;
            if (frame % 100 == 0) {
                sunset_colors.push(c2);
                c2 = c2.darker();
            }
            if (frame > c) {
                night = false;
                sunrise = true;
                frame = 0;

            }
        }

        if (sunrise) {
            frame += 1;

            if (frame > 2 * c) {
                sunrise = false;
                day = true;
                frame = 0;
            }

            else if (frame % 100 == 0) {
                if (frame <= c) {
                    c2 = sunset_colors.pop();
                } else {
                    sunrise = true;
                    c1 = day_colors.pop();
                }
            }

        }

        drawStars(g2);

    }

    private void drawStars(Graphics2D g2) {
        g2.setColor(Color.white);

        if (night) {
            Area star = new Area(new Ellipse2D.Double(getRandomSign() * frame_width * Math.random(),
                    getRandomSign() * frame_height * Math.random(), 5, 5));
            stars.push(star);

        }

        if (stars.size() > 0 && sunrise) {
            stars.pop();
        }

        for (Area star_ : stars) {
            g2.fill(star_);
        }

    }

    private int getRandomSign() {
        if (Math.random() > 0.5) {
            return 1;
        } else {
            return -1;
        }
    }

    public Stack<Area> getStars() {
        return stars;
    }

    public boolean isNight() {
        return night;
    }
}
