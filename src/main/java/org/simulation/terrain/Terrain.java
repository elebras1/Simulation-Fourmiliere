package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;
import java.util.Timer;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.roles.IndividuSexue;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;


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
		contexte.getSimulation().getSaisons().incrementeHeure();
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			fourmiliere = new Fourmiliere(p);
			Point posReine = new Point(p.x + 35, p.y + 35);
			Fourmi laReine = new Fourmi(posReine);

			laReine.setAge(30);
			laReine.setDureeDeVie(547);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);

			this.fourmiliere.setReine(laReine);
			contexte.getSimulation().nouvelleFourmiliere(this.fourmiliere);
			contexte.getSimulation().nouveauPheromone(this.pheromone);


			Fourmi fourmi = new Fourmi(laReine.getPos());
			fourmi.setEtat(new Adulte(contexte));
			Adulte adulte = (Adulte) fourmi.getEtat();
			adulte.setRole(new IndividuSexue());

			fourmi.setAge(30);
			fourmi.setDureeDeVie(547);
			fourmi.setPoids(2);
			this.fourmiliere.ponte(fourmi);
			contexte.getSimulation().nouvelIndividu(fourmi);
		}
		if(contexte.getSimulation().getSaisons().getHeure()==2190){
			switch (contexte.getSimulation().getSaisons()) {
				case AUTOMNE:
					contexte.getSimulation().setSaisons(Saisons.HIVER);
					break;
				case HIVER:
					contexte.getSimulation().setSaisons(Saisons.PRINTEMPS);
					break;
				case PRINTEMPS:
					contexte.getSimulation().setSaisons(Saisons.ETE);
					break;
				case ETE:
					contexte.getSimulation().setSaisons(Saisons.AUTOMNE);
					break;
			}
		}

		fourmiliere.etapeDeSimulation(contexte);
	}

	public Point convertirEnCoordonneesLocales(Point globale) {
		return new Point(globale.x - this.pos.x, globale.y - this.pos.y);
	}


}
