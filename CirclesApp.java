
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Circles.java for E13.
 * The animation app class handling all input.
 * @author Nikolah Pearce.
 */

public class CirclesApp {

    public static void main(String[] args) {

        int iterations = 0;
        boolean usingFile = false;
        double radius = (1.0 / 3.0);
        ArrayList<String> data = new ArrayList<>();
        Scanner sc;

        if (args.length == 1) {
            // If a file name is specified
            usingFile = true;
            try {
                File file = new File(args[0]);

                sc = new Scanner(file);
                String line;
            
                while (sc.hasNext()) {
                    iterations++;
                    line = sc.nextLine();
                    data.add(line);
                }
                iterations--;
            } catch (IOException e) {
                System.out.println("Please check your file name is correct. Start again");
                System.exit(0);
            }

        } else {
            // Otherwise prompt for a number of iterations
            usingFile = false;
            sc = new Scanner(System.in);
            System.out.println("Please enter the number of iterations! Between 0-9");
            try { 
                iterations = sc.nextInt(); 
                if (iterations > 9) {
                    System.out.println("You must enter a number less than 10. Start again.");
                    System.exit(0);
                } else if (iterations < 0) {
                    System.out.println("You must enter a positive integer. Start again.");
                    System.exit(0);
                }           
            } catch (InputMismatchException e) {
                System.out.println("You must enter an integer. Start again.");
                System.exit(0);
            }
            System.out.println("Would you also like to enter a radius ration? y/n");
            if (sc.hasNext()) {
                String yn = sc.next();
                //System.out.println("You entered: " + yn + " and length is: " + yn.length());
                if (yn.equals("y")) {
                    System.out.println("Please enter a ratio. Less than 1/3 works best.");
                    
                    String strRadius;
                    if (sc.hasNext()) {
                        strRadius = sc.next();
                    
                        int slash = strRadius.indexOf('/');
                        if (slash != -1) {
                            // We have a ratio, set this up
                            int a = Integer.parseInt(strRadius.substring(0, slash));
                            int b = Integer.parseInt(strRadius.substring(slash+1));
                            radius = ((double) a / (double) b);
                        } else {
                            // We have a straight float
                            radius = Double.parseDouble(strRadius);
                        }
                    }
                } else if (yn.equals("n")) {
                    radius = (1.0 / 3.0);
                } else {
                    System.out.println("Incorrect response. You must enter either 'y' or 'n'. Start again");
                    System.exit(0);
                }
                
            }
            
        }

        startGUI(iterations, radius, usingFile, data);
    }


    public static void startGUI(int iterations, double radius, boolean usingFile, ArrayList<String> data) {
        JFrame circleFrame = new JFrame();
        circleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (!usingFile) {
            circleFrame.getContentPane().add(new CirclesPanel(iterations, radius, usingFile));
        } else {
            circleFrame.getContentPane().add(new CirclesPanel(iterations, usingFile, data));

        }
        circleFrame.pack();
        circleFrame.setVisible(true);
        circleFrame.setTitle("Circles Output.");
    }
}