//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.CenteredGraphicalElement;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Text extends CenteredGraphicalElement {
    private Color color;
    private String text;

    public Text(int x, int y, Color c, String text) {
        super(x, y);
        this.color = c;
        this.text = text;
    }

    public void paint(Graphics2D g2d) {
        Color current = g2d.getColor();
        g2d.setColor(this.color);
        FontMetrics fm = g2d.getFontMetrics();
        Rectangle2D r = fm.getStringBounds(this.text, g2d);
        int x = this.getX() - (int)r.getWidth() / 2;
        int y = this.getY() - (int)r.getHeight() / 2 + fm.getAscent();
        g2d.drawString(this.text, x, y);
        g2d.setColor(current);
    }

    public String toString() {
        return "text - " + this.text;
    }
}
