package org.simulation.etats;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public class Mort extends Etat {

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}
	
	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.black);
		vue.setDimension(new Dimension(3, 3));
		vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
	}
	public boolean isAdulteSexuesMale() {
		return false;
	}
	
}
