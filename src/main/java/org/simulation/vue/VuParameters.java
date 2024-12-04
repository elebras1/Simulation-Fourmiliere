package org.simulation.vue;


import lib.Nicellipse.src.nicellipse.component.NiSpace;

import javax.swing.*;

public class VuParameters extends VueElement{
    private static final long serialVersionUID = -1082764164251478273L;

    public VuParameters(Simulation simulation, NiSpace space) {
        JSlider sliderWidth = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        sliderWidth.setBounds(700, 100, 200, 50);
        sliderWidth.setMajorTickSpacing(20);
        sliderWidth.setMinorTickSpacing(10);
        sliderWidth.setPaintTicks(true);
        sliderWidth.setPaintLabels(true);
        sliderWidth.addChangeListener(e -> {
            int value = sliderWidth.getValue();
            simulation.setGraphicAnimationDelay(value);
            simulation.timer.setDelay(simulation.graphicAnimationDelay);
        });
        space.add(sliderWidth);
    }

    @Override
    public void redessine() {

    }
}
