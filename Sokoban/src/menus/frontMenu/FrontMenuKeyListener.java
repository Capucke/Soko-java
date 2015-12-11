package menus.frontMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class FrontMenuKeyListener implements KeyListener {
	
	private FrontMenu menu;
	
//	private ArrayList<FrontMenuItem> itemListe;
//	private int selectedItem;
	
	public FrontMenuKeyListener(FrontMenu frontMenu, ArrayList<FrontMenuItem> items, int numSelect){
		this.menu = frontMenu;
//		this.itemListe = items ;
//		this.selectedItem = numSelect;
	}
	
	private void switchSelectedItem(int verticalDiff){
		/*
		 * verticalDiff < 0 : on sélectionne un élément plus haut
		 * verticalDiff > 0 : on sélectionne un élément plus bas
		 */
		int newSelectedItemNum;
		int oldSelectedItemNum;
		
		oldSelectedItemNum = this.menu.getSelectedItemNum() ;
		newSelectedItemNum = (this.menu.getNbItems() + oldSelectedItemNum + verticalDiff)%this.menu.getNbItems();
		
		this.menu.setItemSelected(oldSelectedItemNum, false);
		this.menu.setItemSelected(newSelectedItemNum, true);
		
		this.menu.setSelectedItemNum(newSelectedItemNum);
		
		this.menu.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP:
			this.switchSelectedItem(-1);
			break;
		case KeyEvent.VK_DOWN:
			this.switchSelectedItem(1);
			break;
		case KeyEvent.VK_ENTER:
			this.menu.getItem(this.menu.getSelectedItemNum()).actionIfSelected();
			break;
		default:
			return;
		}
		
		return;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
	}

}
