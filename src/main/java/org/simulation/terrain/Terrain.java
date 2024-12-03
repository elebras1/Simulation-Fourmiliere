package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;


public class Terrain {
	private Point pos;
	private Dimension dim;
	private Fourmiliere fourmiliere;
	private Pheromone pheromone;
	
	public Point getPos() {
		return this.pos;
	}

	public Dimension getDimension() {
		return this.dim;
	}

	public Pheromone getPheromone() {
		return this.pheromone;
	}

	public Terrain(Point pos, Dimension dim) {
		this.pos = pos;
		this.dim = dim;
		this.pheromone = new Pheromone(this.dim);
	}
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		if (this.fourmiliere == null) {
			Point position = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			this.fourmiliere = new Fourmiliere(position);
			Point posReine = new Point(position.x + 15, position.y + 15);
			Fourmi laReine = new Fourmi(posReine);

			laReine.setAge(30);
			laReine.setDureeDeVie(500);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);

			this.fourmiliere.setReine(laReine);
			contexte.getSimulation().nouvelleFourmiliere(this.fourmiliere);
			contexte.getSimulation().nouveauPheromone(this.pheromone);

		}
		this.fourmiliere.etapeDeSimulation(contexte);
	}

}
