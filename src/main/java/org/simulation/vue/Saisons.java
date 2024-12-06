package org.simulation.vue;

public enum Saisons {
    AUTOMNE, HIVER, PRINTEMPS, ETE;


    private int heure;

    public int getHeure() {
        return heure;
    }

    public void incrementeHeure() {
        this.heure++;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }
}
