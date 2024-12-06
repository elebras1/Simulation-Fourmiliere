package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;
import java.util.List;

import org.simulation.etats.Adulte;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Proie;
import org.simulation.etresVivants.Sexe;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.parameter.Parameters;
import org.simulation.proies.Proies;
import org.simulation.roles.IndividuSexue;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;
import org.simulation.vue.Simulation;


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
		this.creerFourmiliere(contexte.getSimulation());
		this.saisonSuivante(contexte.getSimulation());
		fourmiliere.etapeDeSimulation(contexte);
		proies.etapeDeSimulation(contexte);
	}

	private void creerFourmiliere(Simulation simulation) {
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			Point pt = new Point(p.x+40,p.y+40);

			this.fourmiliere = new Fourmiliere(pt);
			Fourmi laReine = new Fourmi(pt);
			laReine.setAge(30);
			laReine.setDureeDeVie(Parameters.DUREE_VIE_MAX);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			simulation.nouvelIndividu(laReine);

			this.fourmiliere.setReine(laReine);
			simulation.nouvelleFourmiliere(this.fourmiliere);
			simulation.nouveauPheromone(this.pheromones);


			Fourmi fourmi = new Fourmi(laReine.getPos());
			fourmi.setEtat(new Adulte(this.fourmiliere));
			Adulte adulte = (Adulte) fourmi.getEtat();
			adulte.setRole(new IndividuSexue());
			IndividuSexue individuSexue = (IndividuSexue) adulte.getRole();
			individuSexue.setSexe(Sexe.male);

			fourmi.setAge(30);
			fourmi.setDureeDeVie(Parameters.DUREE_VIE_MAX);
			fourmi.setPoids(2);
			this.fourmiliere.ponte(fourmi);
			simulation.nouvelIndividu(fourmi);

			this.fourmiliere.getFourmisReines().add(laReine);
			this.fourmiliere.getFourmisSexueesMales().add(fourmi);
		}
	}

	public void saisonSuivante(Simulation simulation) {
		if(simulation.getSaisons().getHeure()== Parameters.CHANGEMENT_SAISON){
			switch (simulation.getSaisons()) {
				case AUTOMNE:
					simulation.setSaisons(Saisons.HIVER);
					break;
				case HIVER:
					simulation.setSaisons(Saisons.PRINTEMPS);
					break;
				case PRINTEMPS:
					simulation.setSaisons(Saisons.ETE);
					break;
				case ETE:
					simulation.setSaisons(Saisons.AUTOMNE);
					break;
			}
		}
	}

	public Point convertirEnCoordonneesLocales(Point globale) {
		return new Point(globale.x - this.pos.x, globale.y - this.pos.y);
	}
}
