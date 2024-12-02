package org.simulation.etats;



import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public abstract class Etat {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void initialise(VueIndividu vue );
}
