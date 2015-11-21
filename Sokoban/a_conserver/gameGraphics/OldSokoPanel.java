package gameGraphics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import javax.swing.JPanel;

public class OldSokoPanel extends JPanel {

	public OldSokoPanel() {
		super();
		this.setBackground(Color.PINK);
		this.setOpaque(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		try {
			// String path = new File("").getAbsolutePath();
			// Image img = ImageIO.read(new File(path +
			// "/img/clap-cinema.png"));
			Image img ;
			
			//OldSokoPanel.class.getResourceAsStream()
			
			img = ImageIO.read(new File("img/final/clap.png"));
			g.drawImage(img, 100, 0, this);
			
			img = ImageIO.read(new File("img/final/camera.png"));
			g.drawImage(img, 180, 0, this);
			// Pour une image de fond
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//g.setColor(getForeground());
	}

}
