package org.simulation.etresVivants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import org.simulation.vue.VuObserver;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueIndividu;

public abstract class Individu {
	private double poids=2;
	protected Point pos;
	private int dureeDeVie;
	private VuObserver vuObserver;
	

	public int getDureeDeVie() {
		return dureeDeVie;
	}

	public void setDureeDeVie(int dureeDeVie) {
		this.dureeDeVie = dureeDeVie;
	}

	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public double getPoids() {
		return poids;
	}

	public void setPoids(double poids) {
		this.poids = poids;
	}

	public void setVuObserver(VuObserver vuObserver) {
		this.vuObserver = vuObserver;
	}

	public VuObserver getVuObserver() {
		return this.vuObserver;
	}
	
	public void initialise(VueIndividu vue) {
		vue.setBackground(Color.gray);
		vue.setDimension(new Dimension(3, 3));
	}

	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.setIndividu(this);
	}
}
