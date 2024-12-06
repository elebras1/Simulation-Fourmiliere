package org.simulation.roles;

import java.util.Random;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Individu;
import org.simulation.etresVivants.Sexe;
import org.simulation.fourmiliere.Bilan;
import org.simulation.parameter.Parameters;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;

public class Reine extends Role{	
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Individu reine = contexte.getIndividu();
		boolean individuSexueMaleIsPresent=
				contexte.getFourmiliere().getPopulation().stream()
				.map(Fourmi::getEtat)
				.filter(etat -> etat instanceof Adulte)
				.map(Adulte.class::cast)
				.map(Adulte::getRole)
				.filter(role -> role instanceof IndividuSexue)
				.map(IndividuSexue.class::cast)
				.map(IndividuSexue::getSexe)
				.anyMatch(sexe -> sexe== Sexe.male);

		if(individuSexueMaleIsPresent){
			if(contexte.getSimulation().getSaisons().getHeure()%15==0){
				Random rand = new Random();
				int nb = rand.nextInt(20);
				for (int i = 0; i < nb; i++) {
					Fourmi oeuf = new Fourmi(reine.getPos());
					// Min et max en heures
					int min = Parameters.DUREE_VIE_MIN; // 1.5 années
					int max = Parameters.DUREE_VIE_MAX; // 2.5 années

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
		if(reineNumber>1 && contexte.getSimulation().getSaisons() == Saisons.ETE){
			contexte.getFourmiliere().getPopulation().remove(reine);
		}
	}

	@Override
	public void bilan(Bilan bilan) {
		bilan.inscrire("Reine");
	}
}
