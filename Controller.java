import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Controller {
    int width = 400;
    int height = 400;
    JFrame frame = new JFrame("Plane Simulation");

    public Controller() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                frame.add(new MenuPane());
                frame.setSize(width, height);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class MenuPane extends JPanel {

        public MenuPane() {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());
            setSize(300, 400);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;
            setBackground(new Color(66, 199, 255));
            JLabel label = new JLabel(
                    "<html><h1><strong><i>Airplane Simulation <br> <center>With Java2d</center></i></strong></h1><hr></html>");
            label.setForeground(Color.white);
            label.setFont(new Font("Verdana", Font.BOLD + Font.ITALIC, 26));
            add(label, gbc);

            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;

            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.setBackground(new Color(66, 199, 255));
            JButton button = new JButton("Start");

            buttons.add(button, gbc);
            gbc.weighty = 1;
            add(buttons, gbc);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(false);
                    new View();
                }
            });
        }

    }
}