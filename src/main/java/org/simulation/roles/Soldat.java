package org.simulation.roles;

import org.simulation.vue.ContexteDeSimulation;

public class Soldat extends Role{

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}

	public boolean isAdulteSexuesMale() {
		return false;
	}

	@Override
	public boolean isAdulteReine() {
		return false;
	}
}
