package org.simulation.etats;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;

import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Bilan;
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

	@Override
	public void actionSiAttaquer(ContexteDeSimulation contexte, Individu individu) {

	}

	@Override
	public void gestionDeFaim(ContexteDeSimulation contexte) {

	}


	public void bilan(Bilan bilan) {
		bilan.inscrire("Mort");
	}
	
}
