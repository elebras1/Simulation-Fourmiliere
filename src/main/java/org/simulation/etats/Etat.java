package org.simulation.etats;


import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public abstract class Etat {
	public abstract void etapeDeSimulation(ContexteDeSimulation contexte);
	public abstract void initialise(VueIndividu vue );
	public abstract void actionSiAttaquer(ContexteDeSimulation contexte,Individu individu);
	public abstract void gestionDeFaim(ContexteDeSimulation contexte);
	public abstract void bilan(Bilan bilan);
}
