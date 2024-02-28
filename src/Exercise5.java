import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

public class Exercise5 extends JFrame {

    private static final int WINDOW_WIDTH = 500;
    private static final int WINDOW_HEIGHT = 300;
    private static final int HEART_SIZE = 30;
    private static final int PATH_LENGTH = 500;

    private int heartX = 0;
    private int heartY = 150;

    Exercise5() {
        setTitle("Beating Heart Animation");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Update heart position along the path
                heartX += 2;
                if (heartX > PATH_LENGTH) {
                    heartX = 0;
                }

                // Update heart's vertical position with a cosine function
                heartY = 150 + (int) (60 * Math.cos(5 * Math.PI * heartX / PATH_LENGTH));

                // Repaint the frame
                repaint();
            }
        });

        timer.start();
    }

    public void paint(Graphics g) {
        // Clear the previous drawings by filling the entire panel with a background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        Graphics2D g2d = (Graphics2D) g;

        // Draw the path
        g2d.setColor(Color.BLACK);
        g2d.drawLine(0, 150, PATH_LENGTH, 150);

        // Draw the beating heart
        drawHeart(g2d, heartX, heartY);
    }

    private void drawHeart(Graphics2D g2d, int x, int y) {
        // Define the shape of a heart
        GeneralPath heart = new GeneralPath();
        heart.moveTo(x, y);
        heart.quadTo(x + 10, y - 15, x + 15, y);
        heart.quadTo(x + 25, y + 15, x, y + 30);
        heart.quadTo(x - 25, y + 15, x - 15, y);
        heart.quadTo(x - 10, y - 15, x, y);

        // Draw the heart
        g2d.setColor(Color.RED);
        g2d.fill(heart);
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(() -> {
            Exercise5 ex = new Exercise5();
            ex.setVisible(true);
        });
    }
}
