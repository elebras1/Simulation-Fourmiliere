package org.simulation.etats;

import org.simulation.etresVivants.*;
import org.simulation.etresVivants.Action;
import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class ProieMort  extends Etat {

    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.YELLOW);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }

    @Override
    public void actionSiAttaquer(ContexteDeSimulation contexte,Individu individu) {
        Proie proie = (Proie) contexte.getIndividu();
        Fourmi fourmi= (Fourmi) individu;
        double distance = proie.getPos().distance(fourmi.getPos());
        if (distance <= 5 && fourmi.getPortProie() == null) {
            fourmi.setPortProie(proie);
            proie.setEstPortePar(fourmi);
            fourmi.setAction(Action.SUIVRE);
            proie.setEtat(new EstPorte());
        }
    }

    @Override
    public void gestionDeFaim(ContexteDeSimulation contexte) {
    }

    public void bilan(Bilan bilan) {
        bilan.inscrire("Proie morte");
    }
}
