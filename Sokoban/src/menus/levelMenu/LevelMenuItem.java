package menus.levelMenu;

import filesManagement.ListeLevels;
import gameGraphics.SokoFenetre;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import menus.MenuItem;

public class LevelMenuItem extends MenuItem {

	private static final long serialVersionUID = 12L;

	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);

	public final static Color completedSelectedColor = new Color(64, 120, 10);
	public final static Color completedNormalColor = new Color(109, 180, 0);

	private int numeroLevel;
	private final static int txtSize = 35;

	private boolean completed;

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num, boolean compl) {
		this(sokoFen, bg, num, false, compl);
	}

	public LevelMenuItem(SokoFenetre sokoFen, Color bg, int num, boolean compl,
			boolean selected) {
		super(sokoFen, bg, normalColor, selectedColor, ListeLevels.LISTE_LEVELS
				.get(num).getName(), LevelMenuItem.txtSize, selected);
		this.numeroLevel = num;
		this.completed = compl;
	}

	public String getName() {
		return ListeLevels.LISTE_LEVELS.get(this.numeroLevel).getName();
	}
	
	public void setCompleted(boolean compl){
		this.completed = compl;
	}

	@Override
	protected Color getColorIfSelected() {
		return (this.completed) ? completedSelectedColor : selectedColor;
	}
	
	@Override
	protected Color getColorIfNotSelected() {
		return (this.completed) ? completedNormalColor : normalColor;
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
