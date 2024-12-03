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
		contexte.getSimulation().setNbTimer(contexte.getSimulation().getNbTimer()+1);
		if (fourmiliere == null) {
			Point p = new Point(this.pos.x + this.dim.width/2 - 30, this.pos.y + this.dim.height/2 - 30);
			fourmiliere = new Fourmiliere(p);
			Point posReine = new Point(p.x + 35, p.y + 35);
			Fourmi laReine = new Fourmi(posReine);

			laReine.setAge(30);
			laReine.setDureeDeVie(1000);
			laReine.setPoids(2);
			laReine.setEtat(new Adulte(new Reine()));
			contexte.getSimulation().nouvelIndividu(laReine);
			
			fourmiliere.setReine(laReine);

			contexte.getSimulation().nouvelleFourmiliere(fourmiliere);


			Fourmi fourmi = new Fourmi(laReine.getPos());
			fourmi.setEtat(new Adulte(contexte));
			Adulte adulte = (Adulte) fourmi.getEtat();
			adulte.setRole(new IndividuSexue());

			fourmi.setDureeDeVie(500);
			this.fourmiliere.ponte(fourmi);
			contexte.getSimulation().nouvelIndividu(fourmi);
		}
		if(contexte.getSimulation().getNbTimer()==2190){
			contexte.getSimulation().setNbTimer(0);
			contexte.getSimulation().setSaisonActual((contexte.getSimulation().getSaisonActual()+1)%4);
		}

		fourmiliere.etapeDeSimulation(contexte);
	}

}
