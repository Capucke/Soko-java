//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.GraphicalElement;

abstract class CenteredGraphicalElement implements GraphicalElement {
    private int x;
    private int y;

    public CenteredGraphicalElement(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    public void translate(int tx, int ty) {
        this.x += tx;
        this.y += ty;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
