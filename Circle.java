import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * Circle.java for E13.
 * The circles panel to 
 * @author Nikolah Pearce.
 */

public class Circle {

    private int number;
    private double parentX;
    private double parentY;
    private double parentRadius;
    private double x;
    private double y;
    private double radius;
    private int r;
    private int g;
    private int b;

    /** 
     * Constructor at generation 0
     */
    public Circle (double x, double y, double radius,  int r, int g, int b) {
        this.number = -1;
        parentY = x;
        this.x = x;
        parentY = y;
        this.y = y;
        parentRadius = radius;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;  
/*
        System.out.println("PARENT CIRCLLLEEEEE -----------" );
        System.out.println("x: " + x + " y: " + y + " rad: " + radius);  
        System.out.println("r: " + r + " g: " + g + " b: " + b);  
        */
    
    }
    /**
     * Constructor for other generations
     */
    public Circle(int number, double pX, double pY, double pR, double radi, int r, int g, int b) {
        this.number = number;
        parentX = pX;
        parentY = pY;
        parentRadius = pR;
        this.radius = radi * pR;
        setSelfFields();
        this.r = r;
        this.g = g;
        this.b = b;  
       /* System.out.println("Circle number: " + number + " ------------" );
        System.out.println("parenx: " + parentX + " px: " + pX + " py: " + parentY + " rad: " + radius);  
        System.out.println("r: " + r + " g: " + g + " b: " + b);  */
    }

    public void setSelfFields() {
        double k = 1.73;
        if (number == 1) {
            x = parentX - radius;
            y = parentY - k * radius;
        } else if (number == 2) {
            x = parentX + radius;
            y = parentY - k * radius;
        } else if (number == 3) {
            x = parentX - 2 * radius;
            y = parentY;
        } else if (number == 4) {
            x = parentX;
            y = parentY;
        } else if (number == 5) {
            x = parentX + 2 * radius;
            y = parentY;
        } else if (number == 6) {
            x = parentX - radius;
            y = parentY + k * radius;
        } else if (number == 7) {
            x = parentX + radius;
            y = parentY + k * radius;
        } 
    }

    public void draw(Graphics page){
        Color colour = new Color(r, g, b);
        page.setColor(colour);
        int rInt = (int) radius;
        // centre x = corner x + r
        // centre y = corner y + r
        page.fillOval(((int) x - rInt), ((int) y - rInt), (2 * rInt), (2 * rInt));
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

}