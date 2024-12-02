package org.simulation.etats;


import java.awt.Color;
import java.awt.Dimension;

import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public class Larve extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.yellow);
		vue.setDimension(new Dimension(5, 5));
	}

	
}
