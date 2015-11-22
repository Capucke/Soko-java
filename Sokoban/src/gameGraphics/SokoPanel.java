package gameGraphics;

import gameSimulator.ImageElement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import javax.swing.JPanel;

public class SokoPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private LinkedList<ImageElement> objets;
	private int width;
	private int height;

	protected SokoPanel(int w, int h, Color bgColor) {
		this.setBackground(bgColor);
		this.width = w;
		this.height = h;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.objets = new LinkedList<ImageElement>();
	}

	protected void reset() {
		synchronized (this.objets) {
			this.objets.clear();
		}
		this.repaint();
	}

	protected void addImageElement(ImageElement e) {
		synchronized (this.objets) {
			this.objets.add(e);
		}
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getBackground());
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		synchronized (this.objets) {
			for (ImageElement e : this.objets) {
				e.paint(g2d);
			}
		}

		g2d.dispose();
	}
}
