
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * CirclesPanel.java for E13.
 * The circles panel to 
 * @author Nikolah Pearce.
 */

public class CirclesPanel extends JPanel {

    private int iterations;
    private double ratio;
    private int thisIt;

    private double x;
    private double y;
    private double radius;

    private boolean usingFile;
    private double[] radii;
    private int[][] colours;

    /**
     * Constructor for when NOT using file input.
     */
    public CirclesPanel(int iterations, double ratio, boolean usingFile) {
        this.ratio = ratio;
        this.iterations = iterations;
        this.usingFile = usingFile;
        this.x = 350.0;
        this.y = 350.0;
        this.radius = 350.0;
        thisIt = 0;

        radii = new double[iterations+1];
        colours = new int[iterations+1][3];

        if (!usingFile) {
            for (int i = 0; i < iterations+1; i++) {
                if (i == 0) {
                    radii[i] = 1.0;
                    colours[i][0] = 255;
                    colours[i][1] = 0;
                    colours[i][2] = 0;
                } else {
                    radii[i] = (1.0/3.0);
                    Random rand = new Random(); 
                    int r = rand.nextInt(256);
                    int g = rand.nextInt(256);
                    int b = rand.nextInt(256);

                    colours[i][0] = r;
                    colours[i][1] = g;
                    colours[i][2] = b;
                }
            }
            
        }

        setPreferredSize(new Dimension((((int) x) * 2), (((int) y)) * 2));
        setBackground(Color.white);
    }

    /**
     * Constructor for when using file input.
     * Sets up raddii and colours arrays based on input data.
     */
    public CirclesPanel(int iterations, boolean usingFile, ArrayList<String> data) {
        this(iterations, 0.0, usingFile);
       
        for (int i = 0; i < data.size(); i++) {
            String line = data.get(i);
            
            String[] list = line.split(" ");
                
            String strRadius = list[0];
            int slash = strRadius.indexOf('/');
            if (slash != -1) {
                // We have a ratio, set this up
                int a = Integer.parseInt(strRadius.substring(0, slash));
                int b = Integer.parseInt(strRadius.substring(slash+1));
                ratio = ((double) a / (double) b);
            } else {
                ratio = Double.parseDouble(strRadius);
            }
            radii[i] = ratio;
            int r = Integer.parseInt(list[1]);
            int g = Integer.parseInt(list[2]);
            int b = Integer.parseInt(list[3]);

            colours[i][0] = r;
            colours[i][1] = g;
            colours[i][2] = b;        
        }

    }
    
    /**
     * Paint component to draw on the circles.
     * As well as call the recursive draw method below.
     * Has different tasks dependant on if using file or not.
     */
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        thisIt = 0;


        int width = getWidth();
        int height = getHeight();

        if (width < height) {
            double newR = ((double) width / 2.0);
            this.x = newR;
            this.y = newR;
            this.radius = newR;
        } else {
            double newR = ((double) height / 2.0);
            this.x = newR;
            this.y = newR;
            this.radius = newR;
        }

        if (!usingFile) {
            // Not using the file, have more room for random colours and fun.
            Random rand = new Random(); 
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);

            Circle first = new Circle(x, y, radius, r, g, b);
            first.draw(page);
            if (iterations == 0) {
                return;
            }
            thisIt++;
            int i = 1;
            r = rand.nextInt(256);
            g = rand.nextInt(256);
            b = rand.nextInt(256);
            while (i < 8) {
                Circle c = new Circle(i, x, y, radius, ratio, r, g, b);
                if (iterations == 1) {
                    c.draw(page);
                } else {
                    recursiveDraw(c, thisIt, page, ratio);
                }
                i++;
            }
        } else {
            // Using the set colours as specified in the file.
            Circle first = new Circle(x, y, radius, colours[thisIt][0], colours[thisIt][1], colours[thisIt][2]);
            first.draw(page);
            if (iterations == 0) {
                return;
            }
            thisIt++;
            int i = 1;
            while (i < 8) {
                Circle c = new Circle(i, x, y, radius, ratio, colours[thisIt][0], colours[thisIt][1], colours[thisIt][2]);
                if (iterations == 1) {
                    c.draw(page);
                } else {
                    recursiveDraw(c, thisIt, page, ratio);
                }
                i++;
            }
        }
        
    }

    /**
     * Recursive draw method draws seven circles based on coordinates of input circle.
     */
    public void recursiveDraw(Circle cIn, int thisItt, Graphics page, double ra) {
        
        if (thisItt == iterations) {
            return;
        }
        cIn.draw(page);
        Random rand = new Random();
        
        thisItt++;
        int i = 1; 
        if (!usingFile) {
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);
            while (i < 8) {
                Circle c = new Circle(i, cIn.getx(), cIn.gety(), cIn.getRadius(), 
                    ra, r, g, b);
                recursiveDraw(c, thisItt, page, ra);
                i++;
            }
        } else {
            while (i < 8) {
                Circle c = new Circle(i, cIn.getx(), cIn.gety(), cIn.getRadius(), 
                    ra, colours[thisItt][0], colours[thisItt][1], colours[thisItt][2]);
                recursiveDraw(c, thisItt, page, ra);
                i++;
            }
        }
        
    }

}