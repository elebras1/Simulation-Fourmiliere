package org.simulation.roles;

import java.awt.Point;
import java.util.Random;


import org.simulation.etresVivants.Fourmi;
import org.simulation.terrain.Pheromone;
import org.simulation.vue.ContexteDeSimulation;

public class Ouvriere extends Role {

	public Ouvriere() {
	}

	public void etapeDeSimulation(ContexteDeSimulation contexte) {
		Fourmi fourmi = (Fourmi) contexte.getIndividu();
		int x = fourmi.getPos().x;
		int y = fourmi.getPos().y;
		int pos;
		Random rand = new Random();
		pos = rand.nextInt(4);

		switch (pos) {
			case 0 -> y++;
			case 1 -> x++;
			case 2 -> y--;
			case 3 -> x--;
		}

		Pheromone pheromone = contexte.getTerrain().getPheromone();
		Point locale = contexte.getTerrain().convertirEnCoordonneesLocales(new Point(x, y));
		pheromone.deposerPheromone(locale.x, locale.y);

		fourmi.setPos(new Point(x,y));
	}

}
