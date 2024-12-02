package org.simulation.etats;

import java.awt.Color;
import java.awt.Dimension;

import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public class Oeuf extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.white);
		vue.setDimension(new Dimension(3, 3));
	}
	
	
}
