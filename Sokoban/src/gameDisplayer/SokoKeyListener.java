package gameDisplayer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SokoKeyListener implements KeyListener {
	
	private SokoDisplayer displayer ;
	
	public SokoKeyListener(SokoDisplayer disp){
		this.displayer = disp ;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch (key) {
		case KeyEvent.VK_UP:
			this.displayer.moveUp();
			break;
		case KeyEvent.VK_DOWN:
			this.displayer.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			this.displayer.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			this.displayer.moveRight();
			break;
		case KeyEvent.VK_R:
			this.displayer.restart();
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
