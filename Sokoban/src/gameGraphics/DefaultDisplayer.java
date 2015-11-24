package gameGraphics;

import gameGraphics.Displayer;

class DefaultDisplayer implements Displayer {
    DefaultDisplayer() {
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