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

		int i=0;
		switch (contexte.getSimulation().getGraphicAnimationDelay()) {
			case 100: {
				i = 1;
				break;
			}
			case 10: {
				i = 10;
				break;
			}
		}
		for (int j=0;j<i;j++) {
			Point newPosition = mouvement(x, y, gradientUp, gradientLeft, gradientRight, gradientDown, fourmi.getAction());
			Point locale = contexte.getTerrain().convertirEnCoordonneesLocales(newPosition);
			pheromone.deposerPheromone(locale.x, locale.y);
			fourmi.setPos(newPosition);
		}

	}

	public Point mouvement(int x, int y, int gradientUp, int gradientLeft, int gradientRight, int gradientDown, Action action) {
		// 10% de chance au minimum de bouger dans une direction
		float probMin = 0.1f;

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

		float probUp = 0, probLeft = 0, probRight = 0, probDown = 0;
		if (action.equals(Action.SUIVRE)) {
			probUp = probMin + (gradientUp / totalGradient);
			probLeft = probMin + (gradientLeft / totalGradient);
			probRight = probMin + (gradientRight / totalGradient);
			probDown = probMin + (gradientDown / totalGradient);
		} else if (action.equals(Action.DECOUVERTE)) {
			probUp = (float) (probMin + ((1.0 / gradientUp) / (1.0 / totalGradient)));
			probLeft = (float) (probMin + ((1.0 / gradientLeft) / (1.0 / totalGradient)));
			probRight = (float) (probMin + ((1.0 / gradientRight) / (1.0 / totalGradient)));
			probDown = (float) (probMin + ((1.0 / gradientDown) / (1.0 / totalGradient)));
		}

		float sum = probUp + probLeft + probRight + probDown;
		probUp /= sum;
		probLeft /= sum;
		probRight /= sum;
		probDown /= sum;

		float randomValue = random.nextFloat(1);

		if (randomValue < probUp) {
			return new Point(x, y - 1);
		} else if (randomValue < probUp + probLeft) {
			return new Point(x - 1, y);
		} else if (randomValue < probUp + probLeft + probRight) {
			return new Point(x + 1, y);
		} else {
			return new Point(x, y + 1);
		}
	}
}
