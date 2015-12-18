package menus.frontMenu;

import gameDisplayer.ImageElement;
import gameGraphics.SokoFenetre;
import gameGraphics.SokoPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

//import javax.swing.BoxLayout;

public class FrontMenu extends SokoPanel {

	private static final long serialVersionUID = 6L;
	private SokoFenetre fen;

	private ArrayList<FrontMenuItem> itemListe;
	private int selectedItem;

	public FrontMenu(int w, int h, Color bg, SokoFenetre window) {
		super(w, h, bg);
		this.fen = window;
		this.setBackground(Color.CYAN);

		this.initBackground();
		this.initItems();

		this.addKeyListener(new FrontMenuKeyListener(this, this.itemListe,
				this.selectedItem));

		this.setPreferredSize(new Dimension(this.fen.getFenWidth(), this.fen
				.getFenHeight()));

	}

	private void initBackground() {
		ImageElement imgElemt = new ImageElement(
				this.getSokoPanelWidth() - 250,
				this.getSokoPanelHeight() - 250, ImageElement.BIG_PEACH, this.fen);
		this.addImageElement(imgElemt);
	}

	private void initItems() {
		this.itemListe = new ArrayList<FrontMenuItem>(2);
		this.selectedItem = 0;

		FrontMenuItem play = new PlayItem(this.fen, true);
		FrontMenuItem reInit = new ReInitStatItem(this.fen, false);
		FrontMenuItem quit = new QuitItem(this.fen, false);

		this.itemListe.add(play);
		this.itemListe.add(reInit);
		this.itemListe.add(quit);
	}
	
	public FrontMenuItem getItem(int numItem){
		int realNum = numItem % this.itemListe.size();
		return this.itemListe.get(realNum);
	}
	
	public void setItemSelected(int numItem, boolean bool){
		if (numItem < 0 || numItem >= this.itemListe.size()){
			return;
		}
		this.itemListe.get(numItem).setSelected(bool);
	}
	
	public int getSelectedItemNum(){
		return this.selectedItem;
	}
	
	public void setSelectedItemNum(int num){
		this.selectedItem = num;
	}
	
	public int getNbItems(){
		return this.itemListe.size();
	}
	
	private int wantedItemX(){
		return this.getSokoPanelWidth()/2;
	}
	
	private int wantedItemY(int numItem){
		return numItem*(this.unitHeight()) + (this.unitHeight()/2);
	}
	
	private int unitHeight(){
		return this.getSokoPanelHeight()/(this.itemListe.size());
	}
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		
		this.paintImgList(g2d);
		
		synchronized (this.itemListe) {
			FrontMenuItem item;
			int yItem;
			for (int i = 0; i < this.itemListe.size(); i++){
				item = this.itemListe.get(i);
				yItem = this.wantedItemY(i);
				item.paintItem(g2d, this.wantedItemX(), yItem);
			}
		}
		
		g2d.dispose();
	}

}
