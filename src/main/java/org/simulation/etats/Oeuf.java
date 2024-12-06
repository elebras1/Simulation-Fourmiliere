package org.simulation.etats;

import java.awt.Color;
import java.awt.Dimension;

import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Bilan;
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
		bilan.inscrire("Oeuf");
	}

}
