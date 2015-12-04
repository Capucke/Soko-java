package menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public abstract class MenuItem extends JPanel {

	private static final long serialVersionUID = 2L;
	private Color normalTxtColor;
	private Color selectTxtColor;
	private String texte;
	private boolean isSelected;
	private int txtSize;

	public MenuItem(Color bg, Color txtCol, Color selectCol, String txt,
			int size) {
		this(bg, txtCol, selectCol, txt, size, false);
	}

	public MenuItem(Color bg, Color txtCol, Color selectCol, String txt,
			int size, boolean selected) {
		super();
		this.setBackground(bg);
		this.normalTxtColor = txtCol;
		this.selectTxtColor = selectCol;
		this.texte = txt;
		this.txtSize = size;
		this.isSelected = selected;
		this.setPreferredSize(new Dimension(this.getTxtWidth(), this
				.getTxtHeight()));
	}

	public void setSelected(boolean selected) {
		this.isSelected = selected;
	}
	
	public abstract void actionIfSelected();

	private Rectangle2D getTxtRect() {
		TextLayout textTl = new TextLayout(this.texte, new Font("Helvetica", 1,
				this.txtSize), new FontRenderContext(null, false, false));
		return textTl.getBounds();
	}

	private int getTxtWidth() {
		double w = this.getTxtRect().getWidth();
		return (int) (3 * w / 2);
	}

	private int getTxtHeight() {
		double h = this.getTxtRect().getHeight();
		return (int) (3 * h / 2);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		Dimension d = getSize();
		int w = d.width;
		int h = d.height;

		TextLayout textTl = new TextLayout(this.texte, new Font("Helvetica", 1,
				this.txtSize), new FontRenderContext(null, false, false));

		AffineTransform textAt = new AffineTransform();
		textAt.translate(0, (float) textTl.getBounds().getHeight());

		// TextLayout thisTl = new TextLayout(this.texte, new Font("Helvetica",
		// 0, 10), g2d.getFontRenderContext());
		// float width = (float)thisTl.getBounds().getWidth();
		// float height = (float)thisTl.getBounds().getHeight();
		// thisTl.draw(g2d, w/2-width/2, 15);
		//
		//
		// thisTl = new TextLayout(this.texte, new Font("Helvetica", 0, 10),
		// g2d.getFontRenderContext());
		// width = (float)thisTl.getBounds().getWidth();
		// thisTl.draw(g2d, w/2-width/2, height + 17);
		//
		//
		AffineTransform at = new AffineTransform();
		at.setToIdentity();
		at.translate(w / 2, h / 2);

		// Sets the Stroke.
		Stroke oldStroke = g2d.getStroke();

		float thickness = (this.isSelected) ? (this.txtSize / 10)
				: (this.txtSize / 12);
		g2d.setStroke(new BasicStroke(thickness));

		// Sets the Paint.
		Paint oldPaint = g2d.getPaint();

		g2d.setPaint(Color.blue);

		// Sets the Shape.
		Shape shape = textTl.getOutline(textAt);
		Rectangle r = shape.getBounds();

		// Sets the selected Shape to the center of the Canvas.
		AffineTransform saveXform = g2d.getTransform();
		AffineTransform toCenterAt = new AffineTransform();
		toCenterAt.concatenate(at);
		toCenterAt.translate(-(r.width / 2), -(r.height / 2));

		g2d.transform(toCenterAt);

		// Sets the rendering method.
		Graphics2D tempg2d = g2d;
		Color txtColor = (this.isSelected) ? this.selectTxtColor
				: this.normalTxtColor;
		g2d.setColor(txtColor);
		g2d.fill(shape);

		g2d.setColor(Color.darkGray);
		g2d.draw(shape);
		g2d.setPaint(tempg2d.getPaint());

		g2d.setStroke(oldStroke);
		g2d.setPaint(oldPaint);
		g2d.setTransform(saveXform);

	}
}