package gameGraphics;

//import gameGraphics.DefaultDisplayer;
//import gameGraphics.Displayer;
import gameGraphics.SokoPanel;
import gameStructures.SokoGame;
import gameDisplayer.ImageElement;
import gameDisplayer.SokoDisplayer;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import javax.swing.JFrame;
//import javax.swing.JScrollPane;
import javax.swing.UIManager;

import menus.FrontMenu;

public class SokoFenetre extends JFrame {
	private static final long serialVersionUID = 1L;
	// private JScrollPane scrollPane;
	private SokoPanel sokoPanel;
	private FrontMenu frontMenu;
//	private LevelMenu levelMenu;

	private int panelWidth;
	private int panelHeight;
	private SokoDisplayer sokoDisplayer;

	 public SokoFenetre() {
		 this(800, 800, Color.MAGENTA);
	 }
	 
	//
	// public SokoFenetre(int width, int height, Color bgColor) {
	// this(width, height, bgColor, new DefaultDisplayer());
	// }

	public SokoFenetre(int width, int height, Color bgColor) {
		super("Sokoban - Judith ;)");

		this.frontMenu = new FrontMenu(Color.MAGENTA, this);
//		this.levelMenu = new LevelMenu(Color.MAGENTA, this);
		

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		this.sokoPanel = new SokoPanel(width, height, bgColor);
		// this.scrollPane = new JScrollPane(this.sokoPanel);
		// this.scrollPane.setPreferredSize(new Dimension(Math.min(800, width),
		// Math.min(600, height)));
		this.panelWidth = width;
		this.panelHeight = height;
		this.sokoPanel.setBackground(bgColor);

		this.getContentPane().setLayout(new BorderLayout());

		this.displayFrontMenu();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setResizable(true);

		this.sokoPanel.setSize(this.sokoPanel.getWidth(),
				this.sokoPanel.getHeight());

		this.pack();
		this.setVisible(true);
	}

	public void displayFrontMenu() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.frontMenu, "Center");
		this.frontMenu.setFocusable(true);
		this.frontMenu.requestFocus();

		this.pack();
		this.revalidate();
		// this.repaint();
	}

//	public void displayLevelMenu() {
//		this.getContentPane().removeAll();
//		this.getContentPane().add(this.levelMenu, "Center");
//		this.levelMenu.setFocusable(true);
//		this.levelMenu.requestFocus();
//
//		this.pack();
//		this.revalidate();
//		// this.repaint();
//	}

	public void displayGame(int numLevel) {
		this.setDisplayer(numLevel);
		
		this.getContentPane().removeAll();
		// à commenter pour remettre le scroll
		this.getContentPane().add(this.sokoPanel, "Center");

		// à décommenter pour remettre le scroll
		// this.getContentPane().add(this.scrollPane, "Center");
		this.sokoPanel.setFocusable(true);
		this.sokoPanel.requestFocus();
		
		this.sokoDisplayer.display();
//		this.sokoPanel.repaint();
		this.pack();
		this.revalidate();
		// this.repaint();
	}

	public void setGame(int numLevel) {
		SokoGame game = new SokoGame(numLevel);
		this.sokoDisplayer.setGame(game);
	}
	
	public void setGame(SokoGame game) {
		this.sokoDisplayer.setGame(game);
	}

	public void setDisplayer(SokoDisplayer sokoDispl) {
		this.sokoDisplayer = sokoDispl;
	}
	
	public void setDisplayer(SokoGame soko) {
		this.sokoDisplayer = new SokoDisplayer(soko, this);
	}
	
	public void setDisplayer(int numLevel) {
		SokoGame game = new SokoGame(numLevel);
		this.setDisplayer(game);
	}

	public SokoPanel getSokoPanel() {
		return this.sokoPanel;
	}

	public FrontMenu getFrontMenu() {
		return this.frontMenu;
	}

	public int getPanelWidth() {
		return this.panelWidth;
	}

	public int getPanelHeight() {
		return this.panelHeight;
	}

	public void addImageElement(ImageElement e) {
		this.sokoPanel.addImageElement(e);
	}

	public void reset() {
		this.sokoPanel.reset();
	}

//	public void display() {
//		this.sokoDisplayer.display();
//		this.sokoPanel.repaint();
//		this.repaint();
//	}

}
