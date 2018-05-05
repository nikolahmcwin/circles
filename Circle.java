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
    private int parentX;
    private int parentY;
    private int parentRadius;
    private int x;
    private int y;
    private int radius;
    private int r;
    private int g;
    private int b;

    public Circle(int number, int pX, int pY, pR, radius, r, g, b) {
        this.number = number;
        parentY = pX;
        parentY = pY;
        parentRadius = pR;
        this.radius = radius;
        setSelfFields();
        this.r = r;
        this.g = g;
        this.b = b;        
    }

    public void setSelfFields() {
        if (number == 1) {
            x = parentX - radius;
            y = parentY - 2 * radius;
        } else if (number == 2) {
            x = parentX + radius;
            y = parentY - 2 * radius;
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
            y = parentY + 2 * radius;
        } else if (number == 7) {
            x = parentX + radius;
            y = parentY + 2 * radius;
        } 
    }

    public void draw(Graphics g){
        Color colour = new Color(r, g, b);
        g.setColor(colour);
        g.fillOval(x, y, radius, radius);

}