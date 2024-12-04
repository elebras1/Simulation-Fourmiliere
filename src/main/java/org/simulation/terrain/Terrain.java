package org.simulation.terrain;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.simulation.etats.Adulte;
import org.simulation.etats.ProieMort;
import org.simulation.etats.ProieVivant;
import org.simulation.etresVivants.Fourmi;
import org.simulation.etresVivants.Proie;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.roles.Reine;
import org.simulation.vue.ContexteDeSimulation;
import org.simulation.vue.VueFourmiliere;


public class Terrain {
	private static final int NOMBRE_PROIE_MAX = 30;
	private List<Proie> proies = new ArrayList<>();
	private Point pos;
	private Dimension dim;
	private Fourmiliere fourmiliere;
	private static final int MIN_DISTANCE_FROM_FOURMILIERE = 100;

	
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
			Point p = new Point(this.pos.x + this.dim.width / 2 - 30, this.pos.y + this.dim.height / 2 - 30);
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

		if (proies.size() <= NOMBRE_PROIE_MAX) {
			spawnProieAleatoire(contexte);
		}
		List<Proie> proiesPlusLa = new ArrayList<>();
		for (Proie proie : proies) {
			for (Fourmi fourmi : fourmiliere.getPopulation()) {
				 proie.attaquerPar(fourmi);
			}
			if(!(proie.getEtat() instanceof ProieVivant)){
				proiesPlusLa.add(proie);

			}
			proie.etapeDeSimulation(contexte);
		}

		this.proies.removeAll(proiesPlusLa);


		fourmiliere.etapeDeSimulation(contexte);
	}

	private void spawnProieAleatoire(ContexteDeSimulation contexte) {
		int nombreProies = new Random().nextInt(2);
		for (int i = 0; i < nombreProies; i++) {
			Point position = generatePositionInZone();
			while (!isPositionValide(position)) {
				position = generatePositionInZone();
			}
			Proie nouvelleProie = new Proie(position);
			proies.add(nouvelleProie);
			contexte.getSimulation().nouvelIndividu(nouvelleProie);
		}
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

}
