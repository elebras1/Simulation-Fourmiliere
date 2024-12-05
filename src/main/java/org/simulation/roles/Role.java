package org.simulation.roles;

import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;

public abstract class Role {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void bilan(Bilan bilan);
}
