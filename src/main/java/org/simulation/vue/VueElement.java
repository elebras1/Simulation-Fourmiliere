package org.simulation.vue;


import lib.Nicellipse.src.nicellipse.component.NiEllipse;

public abstract class VueElement extends NiEllipse {
	private static final long serialVersionUID = 1L;
	
	public VueElement() {
	}
	
	public void mettreAJourVue() {
		this.redessine();
	}
	
	public abstract void redessine();

}
