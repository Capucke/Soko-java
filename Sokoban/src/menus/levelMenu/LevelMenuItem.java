package menus.levelMenu;

import gameGraphics.SokoFenetre;

import java.awt.Color;

import menus.MenuItem;

public class LevelMenuItem extends MenuItem {

	private static final long serialVersionUID = 12L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	private int numeroLevel;

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num) {
		this(sokoFen, bg, num, false);
	}

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, new String("Niveau " + (num +1)), 40, selected);
		this.numeroLevel = num;
	}
	
	public String getName(){
		return Integer.toString(this.numeroLevel + 1);
	}

	@Override
	public void actionIfSelected() {
		this.getFenetre().displayGame(this.numeroLevel);
	}

}
