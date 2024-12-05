package org.simulation.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.terrain.Pheromone;
import org.simulation.terrain.Terrain;


public class Simulation implements ActionListener {
	private NiSpace space = new NiSpace("Simulation Fourmis", new Dimension(1000, 800));
	private Terrain terrain = new Terrain(new Point(10,10), new Dimension(700,700));
	private Saisons saisons = Saisons.AUTOMNE;
	private Timer timer;

	public Simulation() {
		this.space.setDoubleBuffered(true);
		this.space.openInWindow();
		this.nouveauTerrain(terrain);
	}

	public Terrain getTerrain() {
		return this.terrain;
	}

	public Timer getTimer() {
		return this.timer;
	}
	
	public void nouveauTerrain(Terrain terrain) {
		VueTerrain vue = new VueTerrain(terrain);
		this.space.add(vue);
		this.space.repaint();
	}
	public void nouveauParametres(Simulation simulation, NiSpace space) {
		VuParameters v = new VuParameters(simulation, space);
		this.space.add(v,0,0);
		this.space.repaint();
	}


	public void nouvelleFourmiliere(Fourmiliere fourmiliere) {
		VueFourmiliere vue = new VueFourmiliere(fourmiliere);
		// Ajoute l'individu au dessus du terrain
		this.space.add(vue,1,0);
		this.space.repaint();
	}

	public void nouveauPheromone(Pheromone pheromone) {
		VuePheromone vue = new VuePheromone(pheromone, this.terrain.getPos());
		this.space.add(vue, 3, 0);
		this.space.repaint();
	}
	
	public void nouvelIndividu(Individu individu) {
		VueIndividu vue = new VueIndividu(individu);
		// Ajoute l'individu au dessus de la fourmiliere
		this.space.add(vue,2,0);
		this.space.repaint();
	}
	public void retirerIndividu(VueIndividu individu) {
		this.space.remove(individu);
		this.space.repaint();
	}
	public int getGraphicAnimationDelay() {
		return graphicAnimationDelay;
	}
	public void setGraphicAnimationDelay(int graphicAnimationDelay) {
		this.graphicAnimationDelay=graphicAnimationDelay;
	}

	int graphicAnimationDelay = 100;

	public void actionPerformed(ActionEvent e) {
		Component[] views =  Simulation.this.space.getComponents();
		for (int i = 0; i < views.length; i++) {
			Component c = views[i];
			if (c instanceof VueElement) {
				VueElement next = (VueElement) c;
				next.mettreAJourVue();
				
			}
		}
		this.terrain.etapeDeSimulation(new ContexteDeSimulation(Simulation.this));
		if(this.saisons.getHeure() % 24 == 0) {
			this.terrain.getPheromone().evaporation();
		}

	}
	public void start() {
		this.timer = new Timer(0, this);
		this.timer.setDelay(this.graphicAnimationDelay);
		this.timer.start();
		this.nouveauParametres(this,space);
	}
	
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
		simulation.start();
	}

    public Saisons getSaisons() {
        return saisons;
    }

    public void setSaisons(Saisons saisons) {
        this.saisons = saisons;
    }
}
