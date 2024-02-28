
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class Exercise2 extends JFrame {

    private static final int SUN_RADIUS = 30;
    private static final int PLANET_RADIUS = 10;
    private static final int ORBIT_RADIUS = 100;

    // Number of rotations around its own axis during one orbit
    private static final int ROTATION_SPEED = 365;

    // Angle of the planet in radians
    private double planetAngle = 0;

    Exercise2() {
        setTitle("Solar System Animation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update the planet angle for animation (3.14)
                planetAngle += 2 * Math.PI / ROTATION_SPEED;

                // Repaint the frame
                repaint();
            }
        });

        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set the coordinate system to the center of the frame
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        g2d.translate(centerX, centerY);

        // Draw the sun
        g2d.setColor(Color.YELLOW);
        g2d.fill(new Ellipse2D.Double(-SUN_RADIUS, -SUN_RADIUS, 2 * SUN_RADIUS, 2 * SUN_RADIUS));

        // Calculate the position of the planet
        double planetX = ORBIT_RADIUS * Math.cos(planetAngle);
        double planetY = ORBIT_RADIUS * Math.sin(planetAngle);

        // Draw the planet
        g2d.setColor(Color.BLUE);
        g2d.fill(new Ellipse2D.Double(planetX - PLANET_RADIUS, planetY - PLANET_RADIUS, 2 * PLANET_RADIUS, 2 * PLANET_RADIUS));

        // Describe the position of the point on the planet after one third of its orbit
        if (planetAngle >= 2 * Math.PI / 3 && planetAngle <= 4 * Math.PI / 3) {
            double pointX = planetX + PLANET_RADIUS * Math.cos(planetAngle);
            double pointY = planetY + PLANET_RADIUS * Math.sin(planetAngle);

            g2d.setColor(Color.RED);
            g2d.fill(new Ellipse2D.Double(pointX - 2, pointY - 2, 4, 4));
        }
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(() -> {
            Exercise4 ex = new Exercise4();
            ex.setVisible(true);
        });
    }
}
