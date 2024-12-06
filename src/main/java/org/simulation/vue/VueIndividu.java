package org.simulation.vue;

import org.simulation.etresVivants.Individu;

public class VueIndividu extends VueElement implements VuObserver {
	private static final long serialVersionUID = 8010266472160477056L;
	private Individu individu;
	
	public Individu getIndividu() {
		return individu;
	}

	public VueIndividu(Individu individu) {
		this.individu = individu;
		this.individu.setVuObserver(this);
		individu.initialise(this);
		this.setLocation(this.individu.getPos());
	}
	
	@Override
	public void redessine() {
		this.setLocation(this.individu.getPos());
	}

	@Override
	public void notifyVu() {
		this.individu.initialise(this);
	}

	@Override
	public void notifyVuSuppression(Simulation simulation) {
		simulation.retirerIndividu(this);
	}

}

