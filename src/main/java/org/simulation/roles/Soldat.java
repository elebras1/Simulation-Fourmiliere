package org.simulation.roles;

import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;

public class Soldat extends Role{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	@Override
	public void bilan(Bilan bilan) {
		bilan.inscrire("Soldat");
	}
}
