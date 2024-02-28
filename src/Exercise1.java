import java.awt.*;
import java.awt.geom.GeneralPath;

public class Exercise1 extends Frame {

    //Constructor
    Exercise1()
    {
        //Enables the closing of the window.
        addWindowListener(new MyFinishWindow());
    }


    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        //Use of antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

        //The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f);
        g2d.setStroke(bs);


        //Start at the lower front of the car.

        // Create a GeneralPath for the rectangle with rounded corners
        GeneralPath roundedRect = new GeneralPath();

        // Specify dimensions and corner arc dimensions
        int x = 50;
        int y = 75;
        int width = 150;
        int height = 100;
        int arcWidth = 20;
        int arcHeight = 20;

        // Construct the rounded rectangle path
        // Begin at top-left corner
        roundedRect.moveTo(x + arcWidth, y);

        // Top edge
        roundedRect.lineTo(x + width - arcWidth, y);

        // Top-right arc
        roundedRect.quadTo(x + width, y, x + width, y + arcHeight);

        // Right edge
        roundedRect.lineTo(x + width, y + height - arcHeight);


        // Bottom-right arc
        roundedRect.quadTo(x + width, y + height, x + width - arcWidth, y + height);


        // Bottom edge
        roundedRect.lineTo(x + arcWidth, y + height);


        // Bottom-left arc
        roundedRect.quadTo(x, y + height, x, y + height - arcHeight);

        // Left edge
        roundedRect.lineTo(x, y + arcHeight);

        // Top-left arc
        roundedRect.quadTo(x, y, x + arcWidth, y);

        // Close the path
        roundedRect.closePath();

        // Draw the rounded rectangle using Graphics2D
        g2d.draw(roundedRect);


    }



    /**
     * Draws a coordinate system (according to the window coordinates).
     *
     * @param xmax     x-coordinate to which the x-axis should extend.
     * @param ymax     y-coordinate to which the y-axis should extend.
     * @param g2d      Graphics2D object for drawing.
     */
    public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                                  Graphics2D g2d)
    {
        int xOffset = 30;
        int yOffset = 50;
        int step = 20;
        String s;
        //Remember the actual font.
        Font fo = g2d.getFont();
        //Use a small font.
        g2d.setFont(new Font("sansserif",Font.PLAIN,9));
        //x-axis.
        g2d.drawLine(xOffset,yOffset,xmax,yOffset);
        //Marks and labels for the x-axis.
        for (int i=xOffset+step; i<=xmax; i=i+step)
        {
            g2d.drawLine(i,yOffset-2,i,yOffset+2);
            g2d.drawString(String.valueOf(i),i-7,yOffset-7);
        }

        //y-axis.
        g2d.drawLine(xOffset,yOffset,xOffset,ymax);

        //Marks and labels for the y-axis.
        s="  ";
        for (int i=yOffset+step; i<=ymax; i=i+step)
        {
            g2d.drawLine(xOffset-2,i,xOffset+2,i);
            if (i>99){s="";}
            g2d.drawString(s+String.valueOf(i),xOffset-25,i+5);
        }

        //Reset to the original font.
        g2d.setFont(fo);
    }


    public static void main(String[] argv)
    {
        Exercise1 f = new Exercise1();
        f.setTitle("GeneralPath example");
        f.setSize(250,200);
        f.setVisible(true);
    }
}
