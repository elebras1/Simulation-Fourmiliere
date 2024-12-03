package org.simulation.etats;

import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class ProieMort  extends Etat {
    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.YELLOW);
        vue.setDimension(new Dimension(4, 3));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }
}
