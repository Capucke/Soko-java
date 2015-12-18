package menus.levelMenu;

import filesManagement.ListeLevels;
import gameGraphics.SokoFenetre;
//import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
//import java.awt.FlowLayout;

//import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class LevelMenu extends JPanel {

	private static final long serialVersionUID = 11L;
	private SokoFenetre fen;

	private ArrayList<LevelMenuItem> itemListe;

	private int nbLevelPerLine = 3;
	private int nbLevelPerCol = 3;

	public LevelMenu(Color bg, SokoFenetre window) {
		super();

		this.setBackground(bg);
		this.fen = window;

		this.setLayout(new GridLayout(this.nbLevelPerCol, this.nbLevelPerLine));

		this.initLevels();

		this.switchPage(0);
	}

	private void initLevels() {
		this.itemListe = new ArrayList<LevelMenuItem>(10);

		int nbLevels = ListeLevels.NB_LEVELS;

		this.itemListe.add(new LevelMenuItem(this.fen, this.getBackground(), 0,
				true));
		for (int i = 1; i < nbLevels; i++) {
			this.itemListe.add(new LevelMenuItem(this.fen,
					this.getBackground(), i));
		}
	}

	protected void switchPage(int selectedPage) {
		this.removeAll();

		int nbLevelPerPage = this.nbLevelPerLine * this.nbLevelPerCol;

		int firstInd = selectedPage * nbLevelPerPage;
		int lastInd = Math.min(this.itemListe.size(), (selectedPage + 1)
				* nbLevelPerPage) - 1;
		for (int i = firstInd; i <= lastInd; i++) {
			this.add(this.itemListe.get(i));
		}

		for (int i = lastInd + 1; i < (selectedPage + 1) * nbLevelPerPage; i++) {
			JPanel fakePanel = new JPanel();
			fakePanel.setBackground(this.getBackground());
			this.add(fakePanel);
		}
	}

	public ArrayList<LevelMenuItem> getLevelListe() {
		return this.itemListe;
	}

	public int getNbLevelPerLine() {
		return this.nbLevelPerLine;
	}

	public int getNbLevelPerCol() {
		return this.nbLevelPerCol;
	}
	
	public SokoFenetre getFenetre(){
		return this.fen;
	}

}
