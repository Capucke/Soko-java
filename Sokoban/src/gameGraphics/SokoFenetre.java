package gameGraphics;

import gameStructures.SokoGame;
import gameDisplayer.ImageElement;
import gameDisplayer.SokoDisplayer;
import gameDisplayer.SokoGamePanel;
import menus.frontMenu.FrontMenu;
import menus.levelMenu.FullLevelMenu;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SokoFenetre extends JFrame {
	private static final long serialVersionUID = 2L;
	// private JScrollPane scrollPane;
	private SokoGamePanel sokoGamePanel;
	// private ExplicationPanel explPanel;
	private FrontMenu frontMenu;
	private FullLevelMenu levelMenu;

	private int fenWidth = 400;
	private int fenHeight = 200;
	private SokoDisplayer sokoDisplayer;

	public SokoFenetre() {
		this(800, 800, Color.LIGHT_GRAY);
	}

	public SokoFenetre(int width, int height, Color bgColor) {
		super("Sokoban - Judith ;)");
		this.fenWidth = width;
		this.fenHeight = height;

		this.frontMenu = new FrontMenu(width, height, bgColor, this);
		this.levelMenu = new FullLevelMenu(bgColor, this);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception var7) {
			var7.printStackTrace();
		}

		// this.explPanel = new ExplicationPanel(200, height, bgColor, this);
		this.sokoGamePanel = new SokoGamePanel(width, height, Color.BLACK);
		// this.scrollPane = new JScrollPane(this.sokoGamePanel);
		// this.scrollPane.setPreferredSize(new Dimension(Math.min(800, width),
		// Math.min(600, height)));

		this.getContentPane().setLayout(new BorderLayout());

		this.displayFrontMenu();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setResizable(true);

		// this.sokoGamePanel.setSize(this.sokoGamePanel.getWidth(),
		// this.sokoGamePanel.getHeight());

		// this.pack();
		this.setVisible(true);
	}

	public void displayFrontMenu() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.frontMenu, "Center");
		this.frontMenu.setFocusable(true);
		this.frontMenu.requestFocus();

		this.pack();
		this.revalidate();
		this.repaint();
	}

	public void displayLevelMenu() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.levelMenu, "Center");
		this.levelMenu.setFocusable(true);
		this.levelMenu.requestFocus();

		this.pack();
		this.revalidate();
		this.repaint();
	}

	public void displayGame(int numLevel) {
		this.setDisplayer(numLevel);

		this.getContentPane().removeAll();

		// à commenter pour remettre le scroll
		this.getContentPane().add(this.sokoGamePanel, "Center");
		// this.getContentPane().add(this.explPanel, "East");

		// à décommenter pour remettre le scroll
		// this.getContentPane().add(this.scrollPane, "Center");

		this.sokoGamePanel.setFocusable(true);
		this.sokoGamePanel.requestFocus();

		this.sokoDisplayer.display();
		this.pack();
		this.revalidate();
		this.repaint();
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

	public SokoGamePanel getSokoGamePanel() {
		return this.sokoGamePanel;
	}

	public FrontMenu getFrontMenu() {
		return this.frontMenu;
	}

	public int getFenWidth() {
		return this.fenWidth;
	}

	public int getFenHeight() {
		return this.fenHeight;
	}

	public void addImageElement(ImageElement e) {
		this.sokoGamePanel.addImageElement(e);
	}

	public void reset() {
		this.sokoGamePanel.reset();
	}

}
