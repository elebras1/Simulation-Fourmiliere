package org.simulation.vue;

public enum Saisons {
    AUTOMNE, HIVER, PRINTEMPS, ETE;


    private int heure;

    Saisons() {
        heure = 0;
    }
    public int getHeure() {
        return heure;
    }

    public void incrementeHeure() {
        this.heure++;
    }

}
