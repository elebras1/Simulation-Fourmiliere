package org.simulation.vue;

public interface VuObserver {
    void notifyVu();
    void notifyVuSuppression(Simulation simulation);
}
