
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

        radii = new double[iterations];
        colours = new int[iterations][3];

        if (!usingFile) {
            for (int i = 0; i < iterations; i++) {
                if (i == 0) {
                    radii[i] = 1.0;
                } else {
                    radii[i] = (1.0/3.0);
                }
                Random rand = new Random(); 
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);

                colours[i][0] = r;
                colours[i][1] = g;
                colours[i][2] = b;
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
                radius = (a / b);
            } else {
                radius = Double.parseDouble(strRadius);
            }
            radii[i] = radius;

            int r = Integer.parseInt(list[1]);
            int g = Integer.parseInt(list[2]);
            int b = Integer.parseInt(list[3]);

            colours[i][0] = r;
            colours[i][1] = g;
            colours[i][2] = b;
        }
        //System.out.println("input File = " + usingFile);
    }
    
    public void paintComponent(Graphics page) {
        super.paintComponent(page);
        Random rand = new Random(); 
        if (usingFile) {
            
        } else {
            int r = rand.nextInt(256);
            int g = rand.nextInt(256);
            int b = rand.nextInt(256);

            Circle first = new Circle(x, y, radius, r, g, b);
            first.draw(page);

            //double ra = (1.0/3.0);
            int i = 1;
            r = rand.nextInt(256);
            g = rand.nextInt(256);
            b = rand.nextInt(256);
            while (i < 8) {
                Circle c = new Circle(i, x, y, radius, ratio, r, g, b);
                //c.draw(page);
                recursiveDraw(c, 1, page, ratio);
                i++;
            }
        
        }
        


    }

    public void recursiveDraw(Circle cIn, int thisIt, Graphics page, double ra) {
        if (thisIt == iterations) {
            return;
        }
        thisIt++;
        cIn.draw(page);
        Random rand = new Random(); 
        int i = 1;
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        while (i < 8) {
            Circle c = new Circle(i, cIn.getx(), cIn.gety(), cIn.getRadius(), ra, r, g, b);
            //c.draw(page);
            recursiveDraw(c, thisIt, page, ra);
            i++;
        }
        
    }
}