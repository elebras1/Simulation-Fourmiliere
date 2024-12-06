package org.simulation.etats;

import java.awt.*;
import java.util.Random;

import org.simulation.etresVivants.Action;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Bilan;
import org.simulation.parameter.Parameters;
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

		int seuilOuvriere = Parameters.PROBABILITE_OUVRIERE;
		int seuilSoldat = seuilOuvriere + Parameters.PROBABILITE_SOLDAT;
		int seuilSexue = seuilSoldat + Parameters.PROBABILITE_INDIVIDU_SEXUE;

		if (proba < seuilOuvriere) {
			this.setRole(new Ouvriere());
		} else if (proba < seuilSoldat) {
			this.setRole(new Soldat());
		} else if (proba < seuilSexue) {
			this.setRole(new IndividuSexue());
		} else if (reineNumber < Parameters.MAXIMUM_REINES) {
			this.setRole(new Reine());
		} else {
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

	@Override
	public void actionSiAttaquer(ContexteDeSimulation contexte, Individu individu) {

	}

	@Override
	public void gestionDeFaim(ContexteDeSimulation contexte) {
		Fourmi fourmi = (Fourmi) contexte.getIndividu();
		Point p = contexte.getFourmiliere().getPos();
		Point posfourmiliere = new Point(p.x + 40, p.y + 40);
		if (contexte.getFourmiliere().getNourriture()<fourmi.getPoids()/3 ||
				posfourmiliere.distance(fourmi.getPos())>40){
			fourmi.setEtat(new Mort());
		}else {
			fourmi.setaFaim(false);
			fourmi.setAction(Action.DECOUVERTE);
			contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()-fourmi.getPoids()/3);
		}
	}


	public void bilan(Bilan bilan) {
		this.role.bilan(bilan);
	}
}
