//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.CenteredGraphicalElement;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class ImageElement extends CenteredGraphicalElement {
    private Image image;
    private double width;
    private double height;
    private ImageObserver observer;

    public ImageElement(int x, int y, String fileName, int width, int height, ImageObserver obs) {
        super(x, y);
        this.image = Toolkit.getDefaultToolkit().getImage(fileName);
        this.width = (double)width;
        this.height = (double)height;
        this.observer = obs;
    }

    public void paint(Graphics2D g2d) {
        int imageWidth = this.image.getWidth(this.observer);
        int imageHeight = this.image.getHeight(this.observer);
        AffineTransform affineT = AffineTransform.getTranslateInstance((double)this.getX(), (double)this.getY());
        affineT.concatenate(AffineTransform.getScaleInstance(this.width / (double)imageWidth, this.height / (double)imageHeight));
        g2d.drawImage(this.image, affineT, this.observer);
    }

    public String toString() {
        return this.image.toString() + " image";
    }
}
