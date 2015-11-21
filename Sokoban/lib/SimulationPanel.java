//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.GraphicalElement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JPanel;

class SimulationPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Collection<GraphicalElement> shapes;
    private int width;
    private int height;
    private Color bgColor;

    protected SimulationPanel(int _width, int _height, Color _bgColor) {
        this.bgColor = _bgColor;
        this.width = _width;
        this.height = _height;
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.reset();
    }

    protected void reset() {
        this.shapes = new LinkedList();
        this.repaint();
    }

    protected void addGraphicalElement(GraphicalElement e) {
        synchronized(this.shapes) {
            this.shapes.add(e);
        }

        this.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(this.bgColor);
        g2d.fillRect(0, 0, this.width, this.height);

        synchronized(this.shapes) {
            for (GraphicalElement e : this.shapes) {
                e.paint(g2d);
            }
        }

        g2d.dispose();
    }
}
