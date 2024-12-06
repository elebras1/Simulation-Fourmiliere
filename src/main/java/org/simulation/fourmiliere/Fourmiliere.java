package org.simulation.fourmiliere;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.simulation.etresVivants.Fourmi;
import org.simulation.parameter.Parameters;
import org.simulation.vue.ContexteDeSimulation;

public class Fourmiliere {
	private List<Fourmi> population;
	private List<Fourmi> fourmisSexueesMales;
	private HashSet<Fourmi> fourmisReines;
	private Point pos;
	private Dimension dim;
	private double nourriture = Parameters.STOCK_NOURITURE;
	private Bilan bilan;

	public List<Fourmi> getFourmisSexueesMales() {
		return fourmisSexueesMales;
	}

	public HashSet<Fourmi> getFourmisReines() {
		return fourmisReines;
	}
	public Fourmiliere(Point pos) {
		this.population = new ArrayList<>();
		this.pos = pos;
		this.dim = new Dimension(80,80);
		this.bilan = new Bilan();
		this.fourmisReines = new HashSet<>();
		this.fourmisSexueesMales = new ArrayList<>();
	}

	public List<Fourmi> getPopulation() {
		return population;
	}

	public Point getPos() {
		return pos;
	}

	public Dimension getDimension() {
		return dim;
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
			this.bilan.clear();
			this.bilan(this.bilan);
			System.out.println(this.bilan);
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
