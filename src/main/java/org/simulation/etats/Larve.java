package org.simulation.etats;


import java.awt.Color;
import java.awt.Dimension;

import org.simulation.etresVivants.*;
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

	@Override
	public void actionSiAttaquer(ContexteDeSimulation contexte,Individu individu) {

	}

	@Override
	public void gestionDeFaim(ContexteDeSimulation contexte) {
		Fourmi fourmi = (Fourmi) contexte.getIndividu();
		if (contexte.getFourmiliere().getNourriture()<fourmi.getPoids()){
			fourmi.setEtat(new Mort());
		}else {
			fourmi.setaFaim(false);
			contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()-fourmi.getPoids());
		}
	}


}
