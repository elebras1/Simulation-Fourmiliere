package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Proie;
import org.simulation.etresVivants.Sexe;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.proies.Proies;
import org.simulation.roles.IndividuSexue;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;

import static org.simulation.parameter.Parameters.NOMBRE_PROIE_MAX;


public class Terrain {
	private Point pos;
	private Dimension dim;
	private Fourmiliere fourmiliere;
	private Proies proies = new Proies();
	private Pheromone pheromones;

	public Terrain(Point pos, Dimension dim) {
		this.pos = pos;
		this.dim = dim;
		this.pheromones = new Pheromone(this.dim);
	}

	public List<Proie> getProies() {
		return proies.getProies();
	}

	public Point getPos() {
		return this.pos;
	}

	public Dimension getDimension() {
		return this.dim;
	}

	public Pheromone getPheromone() {
		return this.pheromones;
	}

		
	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		contexte.getSimulation().getSaisons().incrementeHeure();
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			Point pt=new Point(p.x+40,p.y+40);
			fourmiliere = new Fourmiliere(pt);
            Fourmi laReine = new Fourmi(pt);

			laReine.setAge(30);
			laReine.setDureeDeVie(547);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);

			this.fourmiliere.setReine(laReine);
			contexte.getSimulation().nouvelleFourmiliere(this.fourmiliere);
			contexte.getSimulation().nouveauPheromone(this.pheromones);


			Fourmi fourmi = new Fourmi(laReine.getPos());
			fourmi.setEtat(new Adulte(this.fourmiliere));
			Adulte adulte = (Adulte) fourmi.getEtat();
			adulte.setRole(new IndividuSexue());
			IndividuSexue individuSexue = (IndividuSexue) adulte.getRole();
			individuSexue.setSexe(Sexe.male);

			fourmi.setAge(30);
			fourmi.setDureeDeVie(547);
			fourmi.setPoids(2);
			this.fourmiliere.ponte(fourmi);
			contexte.getSimulation().nouvelIndividu(fourmi);

			fourmiliere.getFourmisReines().add(laReine);
			fourmiliere.getFourmisSexueesMales().add(fourmi);
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
		proies.etapeDeSimulation(contexte);
	}



	public Point convertirEnCoordonneesLocales(Point globale) {
		return new Point(globale.x - this.pos.x, globale.y - this.pos.y);
	}
}
