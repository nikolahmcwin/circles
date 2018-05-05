
import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Circles.java for E13.
 * The animation app class handling all input.
 * @author Nikolah Pearce.
 */

public class Circles {

    public static int iterations;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number of iterations");
        iterations = sc.nextInt();
        System.out.println("Please enter the radius of each next gen circle:");
        int staticRadius = sc.nextInt();
        
        startGUI();
    }


    public static void startGUI() {
        JFrame distributionFrame = new JFrame();
    
        distributionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        distributionFrame.getContentPane().add(new CirclesPanel());
    
        distributionFrame.pack();
        distributionFrame.setVisible(true);
        distributionFrame.setTitle("Circles Output.");
    }
}