package menus.levelMenu;

import gameGraphics.SokoFenetre;
//import gameStructures.ListeLevels;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.GridLayout;
import java.util.ArrayList;
//import java.awt.FlowLayout;

//import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FullLevelMenu extends JPanel {

	private static final long serialVersionUID = 10L;
	private SokoFenetre fen;

	private LevelMenu levelPanel;
	private PagesMenu pagePanel;

	public FullLevelMenu(Color bg, SokoFenetre window) {
		super();

		this.setBackground(bg);
		this.fen = window;

		this.levelPanel = new LevelMenu(bg, this.fen);
		this.levelPanel.setPreferredSize(new Dimension(600, 400));

		int nbLevelPerPage = this.levelPanel.getNbLevelPerLine()
				* this.levelPanel.getNbLevelPerCol();
		this.pagePanel = new PagesMenu(bg, this.fen, nbLevelPerPage);
		this.pagePanel.setPreferredSize(new Dimension(600, 100));

		this.setPreferredSize(new Dimension(600, 500));

		this.add(this.levelPanel, BorderLayout.CENTER);
		this.add(this.pagePanel, BorderLayout.SOUTH);

		this.addKeyListener(new LevelMenuKeyListener(this.levelPanel,
				this.pagePanel));
		
		this.setPreferredSize(new Dimension(this.fen.getFenWidth(), this.fen.getFenHeight()));
	}

	public ArrayList<LevelMenuItem> getLevelListe() {
		return this.levelPanel.getLevelListe();
	}

	public ArrayList<PageMenuItem> getPageListe() {
		return this.pagePanel.getPageListe();
	}

	public int getNbLevelPerLine() {
		return this.levelPanel.getNbLevelPerLine();
	}

	public int getNbLevelPerCol() {
		return this.levelPanel.getNbLevelPerCol();
	}

}
