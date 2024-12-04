package org.simulation.roles;

import java.awt.Point;
import java.util.Random;


import org.simulation.etresVivants.Action;
import org.simulation.etresVivants.Fourmi;
import org.simulation.terrain.Pheromone;
import org.simulation.vue.ContexteDeSimulation;

public class Ouvriere extends Role {

	public Ouvriere() {
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Fourmi fourmi = (Fourmi) contexte.getIndividu();
		if(fourmi.getAction().equals(Action.CHASSE)) {
			return;
		}
		int x = fourmi.getPos().x;
		int y = fourmi.getPos().y;
		Pheromone pheromone = contexte.getTerrain().getPheromone();
		int gradientUp = pheromone.getPheromone(x, y - 1);
		int gradientLeft = pheromone.getPheromone(x - 1, y);
		int gradientRight = pheromone.getPheromone(x + 1, y);
		int gradientDown = pheromone.getPheromone(x, y + 1);

			Point newPosition = mouvement(x, y, gradientUp, gradientLeft, gradientRight, gradientDown, fourmi.getAction());
			Point locale = contexte.getTerrain().convertirEnCoordonneesLocales(newPosition);
			pheromone.deposerPheromone(locale.x, locale.y);
			fourmi.setPos(newPosition);

	}

	public Point mouvement(int x, int y, int gradientUp, int gradientLeft, int gradientRight, int gradientDown, Action action) {
		Random random = new Random();
		float totalGradient = gradientUp + gradientLeft + gradientRight + gradientDown;
		// Si aucun pheromone → direction aléatoire
		if (totalGradient == 0) {
			int direction = random.nextInt(4);
			switch (direction) {
				case 0: return new Point(x, y - 1);
				case 1: return new Point(x - 1, y);
				case 2: return new Point(x + 1, y);
				case 3: return new Point(x, y + 1);
			}
		}

		double minBias = 10.0; // Biais minimal de 10 %
		double maxPheromone = 255.0; // Valeur maximale des phéromones

		// Calcul des probabilités brutes en fonction de l'action
		double probUp, probLeft, probRight, probDown;

		if (action == Action.SUIVRE) {
			probUp = Math.max(minBias, gradientUp / maxPheromone * (100 - 4 * minBias));
			probLeft = Math.max(minBias, gradientLeft / maxPheromone * (100 - 4 * minBias));
			probRight = Math.max(minBias, gradientRight / maxPheromone * (100 - 4 * minBias));
			probDown = Math.max(minBias, gradientDown / maxPheromone * (100 - 4 * minBias));
		} else if (action == Action.DECOUVERTE) {
			probUp = Math.max(minBias, (maxPheromone - gradientUp) / maxPheromone * (100 - 4 * minBias));
			probLeft = Math.max(minBias, (maxPheromone - gradientLeft) / maxPheromone * (100 - 4 * minBias));
			probRight = Math.max(minBias, (maxPheromone - gradientRight) / maxPheromone * (100 - 4 * minBias));
			probDown = Math.max(minBias, (maxPheromone - gradientDown) / maxPheromone * (100 - 4 * minBias));
		} else {
			// Mode CHASSE ou autre
			probUp = probLeft = probRight = probDown = 25.0; // Égalité parfaite
		}

		// Normalisation pour que la somme fasse 100 %
		double total = probUp + probLeft + probRight + probDown;

		probUp /= total;
		probLeft /= total;
		probRight /= total;
		probDown /= total;

		// Génération d'un mouvement basé sur les probabilités normalisées
		double rand = Math.random();
		if (rand < probUp) {
			return new Point(x, y - 1); // Haut
		} else if (rand < probUp + probLeft) {
			return new Point(x - 1, y); // Gauche
		} else if (rand < probUp + probLeft + probRight) {
			return new Point(x + 1, y); // Droite
		} else {
			return new Point(x, y + 1); // Bas
		}

	}
}
