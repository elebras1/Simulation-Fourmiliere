package org.simulation.etats;

import org.simulation.etresVivants.*;
import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ProieVivant extends Etat {
    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {

    }


    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.GREEN);
        vue.setDimension(new Dimension(4, 3));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
    }
}
