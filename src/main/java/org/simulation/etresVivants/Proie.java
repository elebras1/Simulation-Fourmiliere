package org.simulation.etresVivants;

import org.simulation.etats.*;
import org.simulation.vue.*;

import java.awt.*;

public class Proie  extends Individu {
    private Etat etat;

    public Proie(Point point) {
        this.setPos(point);
    }
    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    public void initialise(VueIndividu vue) {
        this.etat.initialise(vue);
    }
}
