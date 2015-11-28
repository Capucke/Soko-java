package menus;

import java.awt.Color;

public abstract class FrontMenuItem extends MenuItem {

	private static final long serialVersionUID = 3L;
	
	public final static Color selectedColor = new Color(64, 0, 128);
	public final static Color normalColor = new Color(109, 0, 217);
	
	
	public FrontMenuItem(Color bg, String txt) {
		this(bg, txt, false);
	}
	
	public FrontMenuItem(Color bg, String txt,
			boolean selected) {
		super(bg, normalColor, selectedColor, txt, 70, selected);
	}
	
}
