package gameDisplayer;

import gameStructures.SokoGame;
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

	public final static int TAILLE_IMG = 60;
	public final static String FOLDER_IMG = "x" + Integer.toString(TAILLE_IMG);

	public final static Image TXT_COMPLETE;
	// public final static Image EXPL_JEU;
	public final static Image BIG_PEACH;
	public final static Image MID_PEACH;
	public final static Image FOND_ETOILE;
	public final static Image HOLLYWOOD;
	public final static Image FLASH_CAMERA;

	private final static String IMG_FOLDER = "/img/";
	public final static String BAG_FILE_STRING = "/camera.png";
	// public final static String WALL_FILE_STRING = "/vide.png";
	public final static String SOKO_FILE_STRING = "/peach.png";
	public final static String GOAL_FILE_STRING = "/clap.png";
	public final static String EMPTY_FILE_STRING = "/parquet.jpg";

	static {
		TXT_COMPLETE = ImageElement.chargeImg("/img/texte_complete.png");
		// EXPL_JEU = ImageElement.chargeImg("/img/texte_complete.png");
		BIG_PEACH = ImageElement.chargeImg("/img/big_peach.png");
		MID_PEACH = ImageElement.chargeImg("/img/mid_peach.png");
		FOND_ETOILE = ImageElement.chargeImg("/img/etoiles.png");
		HOLLYWOOD = ImageElement.chargeImg("/img/hollywood.png");
		FLASH_CAMERA = ImageElement.chargeImg("/img/image_camera.png");
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

	public static Image chargeImg(int gameElt, int size) {
		String eltFileName;
		switch (gameElt) {
		case SokoGame.BAG:
			eltFileName = BAG_FILE_STRING;
			break;
		case SokoGame.SOKO:
			eltFileName = SOKO_FILE_STRING;
			break;
		case SokoGame.GOAL:
			eltFileName = GOAL_FILE_STRING;
			break;
		case SokoGame.EMPTY:
			eltFileName = EMPTY_FILE_STRING;
			break;
		default:
			throw new IllegalArgumentException(
					"Le premier argument doit être un game élément : BAG, SOKO, GOAL, ou EMPTY");
		}
		System.out.println("\n\n" + IMG_FOLDER + "x" + Integer.toString(size)
				+ eltFileName + "\n\n");
		return chargeImg(IMG_FOLDER + "x" + Integer.toString(size)
				+ eltFileName);
	}

	public void paint(Graphics2D g2d) {
		g2d.drawImage(this.image, this.x, this.y, this.observer);
	}

	public String toString() {
		return "Image : " + this.image.toString();
	}
}
