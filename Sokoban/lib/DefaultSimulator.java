//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package gui;

import gui.Simulable;

class DefaultSimulator implements Simulable {
    DefaultSimulator() {
    }

    public void next() {
        this.message("next()");
    }

    public void restart() {
        this.message("restart()");
    }

    private void message(String functionName) {
        System.out.println("Methode " + functionName + ": pas encore implementee!");
    }
}
