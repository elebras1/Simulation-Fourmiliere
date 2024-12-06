package org.simulation.roles;

import java.util.Random;

import org.simulation.etresVivants.Sexe;
import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.ContexteDeSimulation;

public class IndividuSexue extends Role {
	private Sexe sexe;

	public IndividuSexue() {
		super();
		Random rand = new Random();
		int proba = rand.nextInt(100);
		
		// 50 % de males et 50 % de femelles
		if (proba < 50) {
			this.setSexe(Sexe.femelle);
		} else {
			this.setSexe(Sexe.male);
		}
	}


	public Sexe getSexe() {
		return sexe;
	}


	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	@Override
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
	}


	public boolean isAdulteSexuesMale() {
		return this.sexe==Sexe.male;
	}

	@Override
	public boolean isAdulteReine() {
		return false;
	}
	@Override
	public void bilan(Bilan bilan) {
		bilan.inscrire("Individu sexue " + this.getSexe());
	}
}
