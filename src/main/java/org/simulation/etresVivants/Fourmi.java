package org.simulation.etresVivants;

import java.awt.Point;

import org.simulation.etats.Adulte;
import org.simulation.etats.Etat;
import org.simulation.etats.Larve;
import org.simulation.etats.Mort;
import org.simulation.etats.Nymphe;
import org.simulation.etats.Oeuf;

import org.simulation.fourmiliere.Bilan;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.parameter.Parameters;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;


public class Fourmi extends Individu {
	private final static int PAS_FAIM = 10000;
	private int dureeDeVie;
	private Etat etat;
	private int age;
	private int timetolunch;
	private Proie portProie;
	private Action action;
	private boolean aFaim;

	public Fourmi(Point point) {
		this.setAge(0);
		this.setEtat(new Oeuf());
		this.setPos(point);
		this.setAction(Action.DECOUVERTE);
		this.timetolunch = PAS_FAIM;
		this.portProie = null;
		this.aFaim = false;
	}

	public int getDureeDeVie() {
		return dureeDeVie;
	}

	public void setDureeDeVie(int dureeDeVie) {
		this.dureeDeVie = dureeDeVie;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public void setAction(Action action) {
		this.action = action;
	}

	public Action getAction() {
		return this.action;
	}

	public void evolution(Fourmiliere fourmiliere) {
		this.age++;

		if (this.age == Parameters.AGE_LARVE) {
			this.etat = new Larve();
			this.getVuObserver().notifyVu();
			this.setPoids(6);
		} else if (this.age == Parameters.AGE_NYMPHE) {
			this.etat = new Nymphe();
			this.getVuObserver().notifyVu();
			this.setPoids(0);
		} else if (this.age == Parameters.AGE_ADULTE) {
			this.etat = new Adulte(fourmiliere);
			Adulte adulte = (Adulte) this.etat;
			if(adulte.isAdulteSexuesMale()){
				fourmiliere.getFourmisSexueesMales().add(this);
			}else if(adulte.isAdulteReine()){
				fourmiliere.getFourmisReines().add(this);
			}
			this.getVuObserver().notifyVu();
			this.setPoids(2);
		}

		if (this.age > this.dureeDeVie) {
			this.etat = new Mort();
			fourmiliere.getFourmisSexueesMales().remove(this);
			fourmiliere.getFourmisReines().remove(this);
			this.getVuObserver().notifyVu();

		}
	}

	public void initialise(VueIndividu vue) {
		this.etat.initialise(vue);
	}

	public boolean getaFaim() {
		return aFaim;
	}

	public void setaFaim(boolean aFaim) {
		this.aFaim = aFaim;
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		super.etapeDeSimulation(contexte);
		if(this.timetolunch<=300){
			this.aFaim=true;
			this.setAction(Action.SUIVRE);
		}
		if(this.timetolunch<=0){
			this.etat.gestionDeFaim(contexte);
			this.timetolunch=PAS_FAIM;
		}
		this.timetolunch--;
		this.evolution(contexte.getFourmiliere());
		this.etat.etapeDeSimulation(contexte);
		this.poseProie(contexte);

	}

	private void poseProie(ContexteDeSimulation contexte) {
		Point posfourmiliere = contexte.getFourmiliere().getPos();
		if (this.portProie!=null && posfourmiliere.distance(this.pos)<=40){
			contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()+this.portProie.getPoids());
			contexte.getTerrain().getProies().remove(this.portProie);
			this.portProie.getVuObserver().notifyVuSuppression(contexte.getSimulation());
			this.setAction(Action.DECOUVERTE);
			this.portProie=null;
		}
	}

	public Proie getPortProie() {
		return portProie;
	}

	public void setPortProie(Proie proie) {
		this.portProie = proie;
	}

	@Override
	public void bilan(Bilan bilan) {
		this.etat.bilan(bilan);
	}

}
