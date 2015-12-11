package menus.frontMenu;

import gameGraphics.SokoFenetre;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
//import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class FrontMenu extends JPanel {

	private static final long serialVersionUID = 6L;
	private SokoFenetre fen;

	private ArrayList<FrontMenuItem> itemListe;
	private int selectedItem;

	public FrontMenu(Color bg, SokoFenetre window) {
		super();

		this.setBackground(bg);
		this.fen = window;

//		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new GridLayout(2, 1));

		this.initItems();

		for (int i = 0; i < this.itemListe.size(); i++) {
			this.add(this.itemListe.get(i));
		}

		this.addKeyListener(new FrontMenuKeyListener(this.itemListe, this.selectedItem));
		
		this.setPreferredSize(new Dimension(this.fen.getFenWidth(), this.fen.getFenHeight()));
		
	}

	private void initItems() {
		this.itemListe = new ArrayList<FrontMenuItem>(2);
		this.selectedItem = 0;

		FrontMenuItem play = new PlayItem(this.fen, this.getBackground(), true);
		FrontMenuItem quit = new QuitItem(this.fen, this.getBackground(), false);

		this.itemListe.add(play);
		this.itemListe.add(quit);
	}

}
