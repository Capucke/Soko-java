//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.CenteredGraphicalElement;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Rectangle extends CenteredGraphicalElement {
    private Color drawColor;
    private Color fillColor;
    private int width;
    private int height;

    public Rectangle(int x, int y, Color drawColor, Color fillColor, int width, int height) {
        super(x, y);
        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.width = width;
        this.height = height;
    }

    public Rectangle(int x, int y, Color drawColor, Color fillColor, int size) {
        this(x, y, drawColor, fillColor, size, size);
    }

    public void paint(Graphics2D g2d) {
        Stroke currentStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0F));
        Color current = g2d.getColor();
        if(this.fillColor != null) {
            g2d.setColor(this.fillColor);
            g2d.fillRect(this.getX() - this.width / 2, this.getY() - this.height / 2, this.width, this.height);
        }

        g2d.setColor(this.drawColor);
        g2d.drawRect(this.getX() - this.width / 2, this.getY() - this.height / 2, this.width, this.height);
        g2d.setColor(current);
        g2d.setStroke(currentStroke);
    }

    public String toString() {
        return this.drawColor.toString() + " rectangle";
    }
}
