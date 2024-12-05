package org.simulation.etresVivants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;


import org.simulation.etats.Adulte;
import org.simulation.etats.Etat;
import org.simulation.etats.Larve;
import org.simulation.etats.Mort;
import org.simulation.etats.Nymphe;
import org.simulation.etats.Oeuf;

import org.simulation.parameter.Parameters;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;


public class Fourmi extends Individu {
	private final static int PAS_FAIM=10000;
	private int dureeDeVie;
	private Etat etat;
	private int age;
	private int timetolunch =PAS_FAIM;
	private double portProie=0;
	private Action action;
	
	public Fourmi(Point point) {
		this.setAge(0);
		this.setEtat(new Oeuf());
		this.setPos(point);
		this.setAction(Action.DECOUVERTE);
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

	public void evolution(ContexteDeSimulation contexte) {
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
			this.etat = new Adulte(contexte);
			this.getVuObserver().notifyVu();
			this.setPoids(2);
		}

		if (this.age > this.dureeDeVie) {
			this.etat = new Mort();
			this.getVuObserver().notifyVu();

		}
	}
	public void gestionDeFaim(ContexteDeSimulation contexte){
		switch (this.getEtat().getClass().getSimpleName()) {
			case "Larve" :
				if (contexte.getFourmiliere().getNourriture()<this.getPoids()){
					this.etat = new Mort();
					this.getVuObserver().notifyVu();
				}else {
					contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()-this.getPoids());
				}
			case "Adulte" :
				Point p = contexte.getFourmiliere().getPos();
				Point posfourmiliere = new Point(p.x + 40, p.y + 40);
				if (contexte.getFourmiliere().getNourriture()<this.getPoids()/3 ||
						posfourmiliere.distance(this.pos)>40){
					this.etat = new Mort();
					this.getVuObserver().notifyVu();
				}else {
					contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()-this.getPoids()/3);
				}
			default:

		}
	}


	public void initialise(VueIndividu vue) {
		this.etat.initialise(vue);
	}

	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		super.etapeDeSimulation(contexte);
		if(this.timetolunch<=0){
			this.gestionDeFaim(contexte);
			this.timetolunch=PAS_FAIM;
		}
		this.timetolunch--;
		this.evolution(contexte);
		this.etat.etapeDeSimulation(contexte);
		this.poseProie(contexte);
	}


	private void poseProie(ContexteDeSimulation contexte) {
		Point p = contexte.getFourmiliere().getPos();
		Point posfourmiliere = new Point(p.x + 40, p.y + 40);
		if (this.portProie>0 && posfourmiliere.distance(this.pos)>40){
			contexte.getFourmiliere().setNourriture(contexte.getFourmiliere().getNourriture()+this.getPoids());
			this.portProie=0;
		}
	}

	public double getPortProie() {
		return portProie;
	}

	public void setPortProie(double poids) {
		this.portProie = poids;
	}
}
