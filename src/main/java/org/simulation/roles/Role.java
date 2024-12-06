package org.simulation.roles;


import org.simulation.vue.ContexteDeSimulation;

public abstract class Role {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract boolean isAdulteSexuesMale();
	public abstract boolean isAdulteReine();
}
