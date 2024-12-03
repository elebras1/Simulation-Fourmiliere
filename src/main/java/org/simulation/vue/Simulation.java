package org.simulation.vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.terrain.Pheromone;
import org.simulation.terrain.Terrain;

public class Simulation {
	private final NiSpace space = new NiSpace("Simulation Fourmis", new Dimension(800, 800));
	private final Terrain terrain = new Terrain(new Point(10,10), new Dimension(700,700));
	private final int niveauFourmiliere = 1;
	private final int niveauPheromone = 2;
	private final int niveauIndividu = 3;
	
	
	public Simulation() {
		this.space.setDoubleBuffered(true);
		this.space.openInWindow();
	    this.nouveauTerrain(this.terrain);
	}

	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public void nouveauTerrain(Terrain terrain) {
		VueTerrain vue = new VueTerrain(terrain);
		this.space.add(vue);
		this.space.repaint();
	} 
	
	public void nouvelleFourmiliere(Fourmiliere fourmiliere) {
		VueFourmiliere vue = new VueFourmiliere(fourmiliere);
		// Ajoute l'individu au dessus du terrain
		this.space.add(vue,this.niveauFourmiliere,0);
		this.space.repaint();
	}

	public void nouveauPheromone(Pheromone pheromone) {
		VuePheromone vue = new VuePheromone(pheromone, this.terrain.getPos());
		this.space.add(vue, this.niveauPheromone, 0);
		this.space.repaint();
	}
	
	public void nouvelIndividu(Individu individu) {
		VueIndividu vue = new VueIndividu(individu);
		// Ajoute l'individu au dessus de la fourmiliere
		this.space.add(vue,this.niveauIndividu,0);
		this.space.repaint();
	}
	
	public void startGraphicAnimation() {
		GraphicAnimation animation = new GraphicAnimation();
		animation.start();
	}

	class GraphicAnimation implements ActionListener {
		final int graphicAnimationDelay = 10;

		public void actionPerformed(ActionEvent e) {
			Component[] views =  Simulation.this.space.getComponents();
			for (Component c : views) {
				if (c instanceof VueElement next) {
					next.mettreAJourVue();
				}
			}
			terrain.etapeDeSimulation(new ContexteDeSimulation(Simulation.this));
		}

		public void start() {
			Timer animation = new Timer(0, this);
			animation.setDelay(this.graphicAnimationDelay);
			animation.start();
		}
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.startGraphicAnimation();
	}
}
