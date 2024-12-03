package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueFourmiliere;


public class Terrain {
	private Point pos;
	private Dimension dim;
	private Fourmiliere fourmiliere;

	
	public Point getPos() {
		return this.pos;
	}

	public Dimension getDimension() {
		return this.dim;
	}


	public Terrain(Point pos, Dimension dim) {
		this.pos = pos;
		this.dim = dim;
	}
		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			fourmiliere = new Fourmiliere(p);
			Point posReine = new Point(p.x + 15, p.y + 15);
			Fourmi laReine = new Fourmi(posReine);

			laReine.setAge(30);
			laReine.setDureeDeVie(500);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);
			
			fourmiliere.setReine(laReine);
			contexte.getSimulation().nouvelleFourmiliere(fourmiliere);

		}
		fourmiliere.etapeDeSimulation(contexte);
	}

}
