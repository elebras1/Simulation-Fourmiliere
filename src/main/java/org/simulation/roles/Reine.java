package org.simulation.roles;


import java.util.Random;

import org.simulation.etats.Adulte;
import org.simulation.etats.Oeuf;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Individu;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;

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
			if(contexte.getSimulation().getSaisons().getHeure()%15==0){
				Random rand = new Random();
				int nb = rand.nextInt(50);
				for (int i = 0; i < nb; i++) {
					Fourmi oeuf = new Fourmi(reine.getPos());
					// Min et max en heures
					int min = 547; // 1.5 années
					int max = 913; // 2.5 années

					// Générer un nombre aléatoire entre min et max (inclus)
					int duree = rand.nextInt(max - min + 1) + min;
					oeuf.setDureeDeVie(duree);
					contexte.getFourmiliere().ponte(oeuf);
					contexte.getSimulation().nouvelIndividu(oeuf);
				}
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
		if(reineNumber>1 && contexte.getSimulation().getSaisons() == Saisons.ETE){ {
			contexte.getFourmiliere().getPopulation().remove(reine);
		}
	}
}}
