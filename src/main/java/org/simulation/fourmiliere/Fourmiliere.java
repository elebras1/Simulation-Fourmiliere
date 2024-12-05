package org.simulation.fourmiliere;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.simulation.etresVivants.Fourmi;
import org.simulation.parameter.Parameters;
import org.simulation.vue.ContexteDeSimulation;

public class Fourmiliere {

	public List<Fourmi> getPopulation() {
		return population;
	}

	private List<Fourmi> population;
	private Point pos;
	private Dimension dim;
	private double nourriture = 10000;


	public Point getPos() {
		return pos;
	}

	public Dimension getDimension() {
		return dim;
	}

	public Fourmiliere(Point pos) {
		this.population = new ArrayList<>();
		this.pos = pos;
		this.dim = new Dimension(80,80);
	}

	public void ponte(Fourmi oeuf) {
		this.population.add(oeuf);
	}
	
	public void setReine(Fourmi reine) {
		population.add(reine);
	}
	
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Fourmi[] mesFourmis = this.population.toArray(new Fourmi[this.population.size()]);
		contexte.setFourmiliere(this);
		for (Fourmi fourmi : mesFourmis) {
			fourmi.etapeDeSimulation(contexte);
		}
		if(Parameters.AFFICHER_TRACE) {
			Bilan bilan = new Bilan();
			this.bilan(bilan);
			System.out.println(bilan);
		}
	}

	public void bilan(Bilan bilan) {
		for(Fourmi fourmi : this.population) {
			fourmi.bilan(bilan);
		}
	}

	public double getNourriture() {
		return this.nourriture;
	}

	public void setNourriture(double nourriture) {
		this.nourriture=nourriture;
	}
}
