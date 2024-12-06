package org.simulation.etats;

import java.awt.Color;
import java.awt.Dimension;

import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public class Nymphe extends Etat{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.pink);
		vue.setDimension(new Dimension(5, 5));
	}
	public boolean question() {
		return false;
	}
}
