package org.simulation.roles;


import java.util.Random;

import org.simulation.etats.Adulte;
import org.simulation.etats.Oeuf;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Individu;
import org.simulation.vue.ContexteDeSimulation;

public class Reine extends Role{	
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Individu reine = contexte.getIndividu();
		boolean individuSexueIsPresent=contexte.getFourmiliere().getPopulation().stream()
				.map(Fourmi::getEtat)
				.filter(etat -> etat instanceof Adulte)
				.map(Adulte.class::cast)
				.map(Adulte::getRole)
				.anyMatch(role -> role instanceof IndividuSexue);

		if(individuSexueIsPresent){
			Random rand = new Random();
			int nb = rand.nextInt(10);
			for (int i = 0; i < nb; i++) {
				Fourmi oeuf = new Fourmi(reine.getPos());
				int duree = rand.nextInt(250);
				oeuf.setDureeDeVie(duree);
				contexte.getFourmiliere().ponte(oeuf);
				contexte.getSimulation().nouvelIndividu(oeuf);
			}
		}
		int reineNumber= 0;
		if(contexte.getFourmiliere()!=null) {
			reineNumber=(int) contexte.getFourmiliere().getPopulation().stream()
					.map(Fourmi::getEtat)
					.filter(etat -> etat instanceof Adulte)
					.map(Adulte.class::cast)
					.map(Adulte::getRole)
					.filter(role -> role instanceof Reine)
					.count();
		}
		if(reineNumber>1 && contexte.getSimulation().getSaisonActual()==3){ {
			contexte.getFourmiliere().getPopulation().remove(reine);
		}
	}
}}
