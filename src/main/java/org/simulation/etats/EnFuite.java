package org.simulation.etats;

import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

import javax.swing.*;
import java.awt.*;

public class EnFuite extends Etat {
    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.RED);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }

    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire("En fuite");
    }
}
