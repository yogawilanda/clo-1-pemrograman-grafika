import javax.swing.*;
import java.awt.*;
import java.awt.geom.QuadCurve2D;

public class Exercise6 extends JFrame {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 400;

    private Point[] letterD = {
            new Point(50, 50),    // P1
            new Point(150, 50),   // P2
            new Point(150, 200),  // P3
            new Point(50, 200),   // P4
            new Point(50, 50)     // P5
    };

    private Point[] letterC = {
            new Point(350, 50),    // P1'
            new Point(450, 150),   // P2'
            new Point(550, 50),    // P3'
            new Point(450, 250),   // P4'
            new Point(350, 50)     // P5'
    };

    Exercise6() {
        setTitle("Letter Transformation");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the content pane to a custom JPanel
        setContentPane(new LetterTransformationPanel());

        // Start the animation
        Timer timer = new Timer(100, e -> repaint());
        timer.start();
    }

    private class LetterTransformationPanel extends JPanel {

        private double alpha = 0.0;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;

            // Draw the letter C
            drawLetter(g2d, letterC, Color.RED);

            // Draw the letter D
            drawLetter(g2d, letterD, Color.BLUE);

            // Draw the intermediate transformation
            Point[] intermediatePoints = calculateIntermediatePoints(alpha);
            drawLetter(g2d, intermediatePoints, Color.GREEN);

            // Update alpha for the next frame
            alpha += 0.02;
            if (alpha > 1.0) {
                alpha = 0.0;
            }
        }

        private void drawLetter(Graphics2D g2d, Point[] points, Color color) {
            g2d.setColor(color);

            QuadCurve2D curve1 = new QuadCurve2D.Double(
                    points[0].getX(), points[0].getY(),
                    points[2].getX(), points[2].getY(),
                    points[4].getX(), points[4].getY()
            );

            QuadCurve2D curve2 = new QuadCurve2D.Double(
                    points[1].getX(), points[1].getY(),
                    points[3].getX(), points[3].getY(),
                    points[4].getX(), points[4].getY()
            );

            g2d.draw(curve1);
            g2d.draw(curve2);
        }

        private Point[] calculateIntermediatePoints(double alpha) {
            Point[] intermediatePoints = new Point[5];
            for (int i = 0; i < 5; i++) {
                double x = (1 - alpha) * letterD[i].getX() + alpha * letterC[i].getX();
                double y = (1 - alpha) * letterD[i].getY() + alpha * letterC[i].getY();
                intermediatePoints[i] = new Point((int) x, (int) y);
            }
            return intermediatePoints;
        }
    }

    public static void main(String[] argv) {
        SwingUtilities.invokeLater(() -> {
            Exercise6 ex = new Exercise6();
            ex.setVisible(true);
        });
    }
}
