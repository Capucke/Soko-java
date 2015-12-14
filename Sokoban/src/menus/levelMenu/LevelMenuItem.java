package menus.levelMenu;

import gameGraphics.SokoFenetre;
import gameStructures.ListeLevels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import menus.MenuItem;

public class LevelMenuItem extends MenuItem {

	private static final long serialVersionUID = 12L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	private int numeroLevel;
	private final static int txtSize = 35;

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num) {
		this(sokoFen, bg, num, false);
	}

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, ListeLevels.LISTE_LEVELS
				.get(num).getName(), LevelMenuItem.txtSize, selected);
		this.numeroLevel = num;
	}
	
	public String getName() {
		return ListeLevels.LISTE_LEVELS.get(this.numeroLevel).getName();
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().displayGame(this.numeroLevel);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		this.paintItem(g2d, 0, 0);

	}

}
