package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import org.simulation.etats.Adulte;
import org.simulation.etats.ProieMort;
import org.simulation.etats.ProieVivant;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Proie;
import org.simulation.etresVivants.Sexe;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.roles.IndividuSexue;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.Saisons;


public class Terrain {
	private static final int NOMBRE_PROIE_MAX = 2000;
	private List<Proie> proies = new ArrayList<>();
	private Point pos;
	private Dimension dim;
	private Fourmiliere fourmiliere;
	private static final int MIN_DISTANCE_FROM_FOURMILIERE = 45;

	public List<Proie> getProies() {
		return proies;
	}

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
			contexte.getSimulation().nouveauPheromone(this.pheromone);


			Fourmi fourmi = new Fourmi(laReine.getPos());
			fourmi.setEtat(new Adulte(contexte));
			Adulte adulte = (Adulte) fourmi.getEtat();
			adulte.setRole(new IndividuSexue());
			IndividuSexue individuSexue = (IndividuSexue) adulte.getRole();
			individuSexue.setSexe(Sexe.male);

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

		if (proies.size() <= NOMBRE_PROIE_MAX) {
			for(int i = 0; i < NOMBRE_PROIE_MAX; i++){
				spawnProieAleatoire(contexte);
			}

		}
		for (int i = 0;i<this.proies.size();i++){
			this.proies.get(i).etapeDeSimulation(contexte);
		}
	}

	private void spawnProieAleatoire(ContexteDeSimulation contexte) {

		Point position = generatePositionInZone();
		while (!isPositionValide(position)) {
			position = generatePositionInZone();
		}
		Proie nouvelleProie = new Proie(position);
		proies.add(nouvelleProie);
		contexte.getSimulation().nouvelIndividu(nouvelleProie);

	}

	private Point generatePositionInZone() {
		int zone = new Random().nextInt(4);
		switch (zone) {
			case 0: return generatePosition(0, 0);
			case 1: return generatePosition(dim.width / 2, 0);
			case 2: return generatePosition(0, dim.height / 2);
			case 3: return generatePosition(dim.width / 2, dim.height / 2);
			default: return new Point(0, 0);
		}
	}

	private Point generatePosition(int xOffset, int yOffset) {
		Random random = new Random();
		int x = random.nextInt(dim.width / 2) + xOffset;
		int y = random.nextInt(dim.height / 2) + yOffset;
		return new Point(x + pos.x, y + pos.y);
	}

	private boolean isPositionValide(Point position) {
		if (fourmiliere == null) return true;
		Point posFourmiliere = fourmiliere.getPos();
		return posFourmiliere.distance(position) >= MIN_DISTANCE_FROM_FOURMILIERE;
	}

	public Point convertirEnCoordonneesLocales(Point globale) {
		return new Point(globale.x - this.pos.x, globale.y - this.pos.y);
	}


}
