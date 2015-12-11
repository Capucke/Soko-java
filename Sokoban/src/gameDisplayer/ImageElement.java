package gameDisplayer;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.net.URL;

public class ImageElement {
	private int x;
	private int y;
	private Image image;
	private ImageObserver observer;

	public final static Image BAG_IMG;
	public final static Image WALL_IMG;
	public final static Image SOKO_IMG;
	public final static Image GOAL_IMG;
	public final static Image EMPTY_IMG;
	public final static Image TXT_COMPLETE;
	public final static Image EXPL_JEU;
	public final static Image BIG_PEACH;

	static {
		BAG_IMG = ImageElement.chargeImg("/img/camera.png");
		WALL_IMG = ImageElement.chargeImg("/img/vide.png");
		SOKO_IMG = ImageElement.chargeImg("/img/peach.png");
		GOAL_IMG = ImageElement.chargeImg("/img/clap.png");
		EMPTY_IMG = ImageElement.chargeImg("/img/parquet.jpg");
		TXT_COMPLETE = ImageElement.chargeImg("/img/texte_complete.png");
		EXPL_JEU = ImageElement.chargeImg("/img/texte_complete.png");
		BIG_PEACH = ImageElement.chargeImg("/img/big_peach.png");
	}

	public ImageElement(int theX, int theY, Image img, ImageObserver obs) {
		this.image = img;
		this.x = theX;
		this.y = theY;
		this.observer = obs;
	}

	private static Image chargeImg(String fileName) {
		URL imgUrl = ImageElement.class.getResource(fileName);
		Image img = Toolkit.getDefaultToolkit().getImage(imgUrl);
		return img;
	}

	public void paint(Graphics2D g2d) {
		g2d.drawImage(this.image, this.x, this.y, this.observer);
	}

	public String toString() {
		return "Image : " + this.image.toString();
	}
}
