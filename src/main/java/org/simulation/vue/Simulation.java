package org.simulation.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.etresVivants.Individu;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.terrain.Terrain;

public class Simulation implements ActionListener {
	private NiSpace space = new NiSpace("Simulation Fourmis", new Dimension(1000, 800));
	private Terrain terrain = new Terrain(new Point(10,10), new Dimension(700,700));
	private Saisons saisons = Saisons.AUTOMNE;
	Timer timer;

	public Simulation() {
		space.setDoubleBuffered(true);
		space.openInWindow();
	    this.nouveauTerrain(terrain);
		this.nouveauParametres(this,space);
	}

	
	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public void nouveauTerrain(Terrain terrain) {
		VueTerrain v = new VueTerrain(terrain);
		this.space.add(v);
		this.space.repaint();
	}
	public void nouveauParametres(Simulation simulation, NiSpace space) {
		VuParameters v = new VuParameters(simulation, space);
		this.space.add(v,0,0);

		this.space.repaint();
	}


	public void nouvelleFourmiliere(Fourmiliere fourmiliere) {
		VueFourmiliere v = new VueFourmiliere(fourmiliere);
		// Ajoute l'individu au dessus du terrain
		this.space.add(v,1,0);
		this.space.repaint();
	}
	
	public void nouvelIndividu(Individu individu) {
		VueIndividu v = new VueIndividu(individu);
		// Ajoute l'individu au dessus de la fourmiliere
		this.space.add(v,2,0);
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
		terrain.etapeDeSimulation(new ContexteDeSimulation(Simulation.this));
	}
	public void start() {
		timer = new Timer(0, this);
		timer.setDelay(this.graphicAnimationDelay);
		timer.start();
	}
	
	public static void main(String args[]) {
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
