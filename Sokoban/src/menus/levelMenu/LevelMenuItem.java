package menus.levelMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

import menus.MenuItem;

public class LevelMenuItem extends MenuItem {

	private static final long serialVersionUID = 4L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	private int numeroLevel;

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, String txt, int num) {
		this(sokoFen, bg, txt, num, false);
	}

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, String txt, int num,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, txt, 55, selected);
		this.numeroLevel = num;
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().displayGame(this.numeroLevel);
	}

}
