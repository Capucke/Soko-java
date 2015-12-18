package menus.frontMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

import menus.MenuItem;

public abstract class FrontMenuItem extends MenuItem {

	private static final long serialVersionUID = 7L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	public FrontMenuItem(SokoFenetre sokoFen, String txt) {
		this(sokoFen, txt, false);
	}

	public FrontMenuItem(SokoFenetre sokoFen, String txt, int txtSize,
			boolean selected) {
		super(sokoFen, normalColor, selectedColor, txt, txtSize, selected);
	}

	public FrontMenuItem(SokoFenetre sokoFen, String txt, boolean selected) {
		this(sokoFen, txt, 70, selected);
	}

	public FrontMenuItem(SokoFenetre sokoFen, Color bg, String txt) {
		this(sokoFen, bg, txt, false);
	}

	public FrontMenuItem(SokoFenetre sokoFen, Color bg, String txt,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, txt, 70, selected);
	}

}
