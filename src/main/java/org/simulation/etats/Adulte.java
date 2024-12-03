package org.simulation.etats;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import org.simulation.etresVivants.Fourmi;
import org.simulation.roles.*;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public class Adulte extends Etat {
	private Role role;
	
	public Adulte(ContexteDeSimulation contexte) {
		Random rand = new Random();
		int proba = rand.nextInt(100);
		// 60 % d'ouvrieres, 25 % de soldats et 15 % d'individus sexu�s
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

		if(proba < 60) {
			this.setRole(new Ouvriere());
		} else if (proba < 85) {
			this.setRole(new Soldat());
		} else if (proba < 99){
			this.setRole(new IndividuSexue());
		} else if(reineNumber<3) {
			this.setRole(new Reine());
		}else{
			this.setRole(new IndividuSexue());
		}
	}
	
	public Adulte(Role role) {
		this.role = role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	public Role  getRole() {
		return this.role;
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		this.role.etapeDeSimulation(contexte);
	}

	public void initialise(VueIndividu vue ) {
		vue.setBackground(Color.blue);
		vue.setDimension(new Dimension(3, 3));
	}

	
}
