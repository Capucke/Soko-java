package menus.levelMenu;

import gameDisplayer.ImageElement;
import gameGraphics.SokoFenetre;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PeachPanel extends JPanel {

	private static final long serialVersionUID = 15L;
	
	private ImageElement bigPeach;
	private Color bgCol;
	
	public PeachPanel(Color bg, SokoFenetre window) {
		super();
		this.bgCol = bg;
		this.setBackground(bg);
		this.setPreferredSize(new Dimension(190, 140));
		this.bigPeach = new ImageElement(50, 0, ImageElement.MID_PEACH, window);
	}
	
	public JPanel getEquiPanel(){
		JPanel emptyPanel = new JPanel();
		emptyPanel.setBackground(this.bgCol);
		emptyPanel.setPreferredSize(this.getPreferredSize());
		return emptyPanel;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		this.bigPeach.paint(g2d);
		
		g2d.dispose();
	}

}
