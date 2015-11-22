package gameGraphics;

import gameGraphics.Simulable;

class DefaultSimulator implements Simulable {
    DefaultSimulator() {
    }

    public void display() {
        this.message("display()");
    }

    public void restart() {
        this.message("restart()");
    }

    private void message(String functionName) {
        System.out.println("Methode " + functionName + ": pas encore implementee!");
    }
}