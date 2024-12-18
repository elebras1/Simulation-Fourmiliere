package org.simulation.etats;

import org.simulation.etresVivants.Individu;
import org.simulation.etresVivants.Proie;
import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

import javax.swing.*;
import java.awt.*;

public class EnFuite extends Etat {


    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
        Proie proie = (Proie) contexte.getIndividu();
        contexte.getTerrain().getProies().remove(proie);
        proie.getVuObserver().notifyVuSuppression(contexte.getSimulation());
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.RED);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

    }

    @Override
    public void actionSiAttaquer(ContexteDeSimulation contexte, Individu individu) {

    }

    @Override
    public void gestionDeFaim(ContexteDeSimulation contexte) {

    }

    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire("En fuite");
    }
    public boolean isAdulteSexuesMale() {
        return false;
    }
}
