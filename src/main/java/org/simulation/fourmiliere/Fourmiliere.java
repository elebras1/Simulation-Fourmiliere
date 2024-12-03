package org.simulation.fourmiliere;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import org.simulation.etats.*;
import org.simulation.etresVivants.Fourmi;
import org.simulation.roles.IndividuSexue;
import org.simulation.roles.Ouvriere;
import org.simulation.roles.Soldat;
import org.simulation.vue.ContexteDeSimulation;

public class Fourmiliere {
	private List<Fourmi> population;
	private Point pos;
	private Dimension dim;
	
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

		this.afficherTrace();
	}

	public void afficherTrace() {
		int nombreOeufs = 0;
		int nombreLarves = 0;
		int nombreNymphes = 0;
		int nombreMorts = 0;
		int nombreOuvriere = 0;
		int nombreSoldats = 0;
		int nombreIndividuSexue = 0;
		for(Fourmi fourmi : this.population) {
			switch (fourmi.getEtat().getClass().getSimpleName()) {
				case "Oeuf" -> nombreOeufs++;
				case "Larve" -> nombreLarves++;
				case "Nymphe" -> nombreNymphes++;
				case "Mort" -> nombreMorts++;
				case "Adulte" -> {
					Adulte fourmiAdulte = (Adulte) fourmi.getEtat();
					switch (fourmiAdulte.getRole().getClass().getSimpleName()) {
						case "Ouvriere" -> nombreOuvriere++;
						case "Soldat" -> nombreSoldats++;
						case "IndividuSexue" -> nombreIndividuSexue++;
					}
				}
			}
		}

		System.out.println("Total fourmis : " + this.population.size() +
				", nombre d'oeufs : " + nombreOeufs +
				", nombre de larves : " + nombreLarves +
				", nombre de nymphes : " + nombreNymphes +
				", nombre de morts : " + nombreMorts +
				", nombre d'ouvrières : " + nombreOuvriere +
				", nombre de soldats : " + nombreSoldats +
				", nombre d'individus sexues : " + nombreIndividuSexue);
	}
}
