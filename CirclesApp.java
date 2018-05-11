
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
        //System.out.println("Size or args is: " + args.length);

        if (args.length == 1) {
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
            } catch (IOException e) {
                System.out.println("Please check your file name.");
            }

        } else {
            usingFile = false;
            sc = new Scanner(System.in);

            System.out.println("Please enter the number of iterations! Between 0-9");
            iterations = sc.nextInt();        
        }

        startGUI(iterations, radius, usingFile, data);
    }


    public static void startGUI(int iterations, double radius, boolean usingFile, ArrayList<String> data) {
        JFrame distributionFrame = new JFrame();
    
        distributionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (!usingFile) {
            distributionFrame.getContentPane().add(new CirclesPanel(iterations, radius, usingFile));
        } else {
            distributionFrame.getContentPane().add(new CirclesPanel(iterations, usingFile, data));

        }
    
        distributionFrame.pack();
        distributionFrame.setVisible(true);
        distributionFrame.setTitle("Circles Output.");
    }
}