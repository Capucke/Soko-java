package gameGraphics;

import gameDisplayer.ImageElement;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class ExplicationPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private ImageElement explImg;
	private int width;
	private int height;

	protected ExplicationPanel(int w, int h, Color bgColor, ImageObserver obs) {
		this.setBackground(bgColor);
		this.width = w;
		this.height = h;
		this.setPreferredSize(new Dimension(this.width, this.height));
		this.explImg = new ImageElement(0, 0, ImageElement.EXPL_JEU, obs);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(this.getBackground());
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		synchronized (this.explImg) {
			this.explImg.paint(g2d);
		}

		g2d.dispose();
	}

	public int getExplPanelWidth() {
		return this.width;
	}

	public int getExplPanelHeight() {
		return this.height;
	}
}
