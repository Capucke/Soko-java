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

	private static ArrayList<LevelMenuItem> itemListe;

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
	
	private void addLevel(int numLevel, boolean selected){
		itemListe.add(new LevelMenuItem(this.fen,
				this.getBackground(), numLevel, ListeLevels.LISTE_LEVELS.get(numLevel).isCompleted(), selected));
	}

	private void initLevels() {
		itemListe = new ArrayList<LevelMenuItem>(10);

		int nbLevels = ListeLevels.NB_LEVELS;

		this.addLevel(0, true);
		for (int i = 1; i < nbLevels; i++) {
			this.addLevel(i, false);
		}
	}

	protected void switchPage(int selectedPage) {
		this.removeAll();

		int nbLevelPerPage = this.nbLevelPerLine * this.nbLevelPerCol;

		int firstInd = selectedPage * nbLevelPerPage;
		int lastInd = Math.min(itemListe.size(), (selectedPage + 1)
				* nbLevelPerPage) - 1;
		for (int i = firstInd; i <= lastInd; i++) {
			this.add(itemListe.get(i));
		}

		for (int i = lastInd + 1; i < (selectedPage + 1) * nbLevelPerPage; i++) {
			JPanel fakePanel = new JPanel();
			fakePanel.setBackground(this.getBackground());
			this.add(fakePanel);
		}
	}
	
	public static void setCompletedItem(int num){
		itemListe.get(num).setCompleted(true);
	}
	
	public static void reInitStat(){
		for(int i = 0; i<itemListe.size();i++){
			itemListe.get(i).setCompleted(false);
		}
	}

	public static ArrayList<LevelMenuItem> getLevelListe() {
		return itemListe;
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
