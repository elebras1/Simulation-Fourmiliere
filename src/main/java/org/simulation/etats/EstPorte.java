package org.simulation.etats;

import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class EstPorte extends Etat{
    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.cyan);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }

}
