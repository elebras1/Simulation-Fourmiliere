package org.simulation.etats;

import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class ProieVivant extends Etat {

    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {

    }


    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.GREEN);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }

    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire("Proie vivante");
    }
}
