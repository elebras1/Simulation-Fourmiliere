package org.simulation.etats;

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
    public boolean isAdulteSexuesMale() {
        return false;
    }
}
