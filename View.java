import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.awt.event.*;

/**
 * View
 */

public class View extends JPanel {

    int width = 1200;
    int height = 800;
    int frame = 0;

    private Plane plane;
    private Clouds clouds;
    private Sky sky;
    private BufferedImage planeIcon;
    private boolean flyAway = false;

    View() {
        JFrame frame = new JFrame() {
            @Override
            protected void processWindowEvent(WindowEvent e) {
                super.processWindowEvent(e);
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    ourExit();

                }
            };
        };
        frame.add(this);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBackground(new Color(128, 217, 255));

        // Inicializimi i objekteve
        plane = new Plane(-width / 2, 0, width / 10, width);
        planeIcon = plane.getPlaneIcon();
        sky = new Sky(width, height);
        clouds = new Clouds(width, height);

        frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Properties te g
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(10);
        g2.setColor(Color.red);
        g2.setStroke(stroke);

        // Ndrimi i dites
        sky.changeTime(g2);

        g2.translate(width / 2, height / 2);

        // Vizatimi i Aeroplanit
        plane.movePlane();
        if (flyAway) {
            plane.flyAway();
        }
        AffineTransform planeMove = new AffineTransform();
        planeMove.translate(plane.getX(), plane.getY());
        // planeMove.scale(1.2, 1.2); plane 1
        planeMove.scale(0.9, 0.9);
        g2.drawImage(planeIcon, planeMove, null);

        // Vizatimi i reve
        // Nese nuk eshte nate, mos vizato re
        Cloud[] clouds_arr = clouds.getClouds();
        if (sky.isNight()) {
            clouds.noMoreClouds();
        } else {
            clouds.moveAll();

            for (Cloud cloud : clouds_arr) {
                AffineTransform cloudTransform = new AffineTransform();
                cloudTransform.translate(cloud.getX(), cloud.getY());
                cloudTransform.scale(cloud.getScale(), cloud.getScale());
                g2.drawImage(cloud.getImage(), cloudTransform, null);
            }

        }

        // Frame updates
        try {
            Thread.sleep(5);
        } catch (Exception e) {

        }

        repaint();
    }

    void ourExit() {
        flyAway = true;
        if (!plane.flewAway()) {

            JOptionPane.showMessageDialog(this, "Shpresojme se keni pasur nje udhetim te mbare",
                    "Grupi_D Airlines",
                    JOptionPane.INFORMATION_MESSAGE);

            System.exit(0);
            ourExit();
        }

    }
}
