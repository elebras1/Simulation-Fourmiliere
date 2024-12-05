package org.simulation.vue;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.parameter.Parameters;

import javax.swing.*;

public class VuParameters extends VueElement {
    private static final long serialVersionUID = -1082764164251478273L;

    public VuParameters(Simulation simulation, NiSpace space) {
        JSlider sliderWidth = this.getjSlider(simulation, space);
        space.add(sliderWidth);
        space.setLayout(null);

        JLabel labelHeure = this.createLabel("Heure:", 100, space);
        JTextField tfHeure = this.createTextField(String.valueOf(simulation.getSaisons().getHeure()), 100, space);
        simulation.getTimer().addActionListener(e -> tfHeure.setText(String.valueOf(simulation.getSaisons().getHeure())));
        tfHeure.setEditable(false);

        JLabel labelProbabiliteOuvriere = this.createLabel("Probabilité Ouvrière:", 150, space);
        JTextField tfProbabiliteOuvriere = this.createTextField(String.valueOf(Parameters.PROBABILITE_OUVRIERE), 150, space);

        JLabel labelProbabiliteSoldat = this.createLabel("Probabilité Soldat:", 200, space);
        JTextField tfProbabiliteSoldat = this.createTextField(String.valueOf(Parameters.PROBABILITE_SOLDAT), 200, space);

        JLabel labelProbabiliteIndividuSexue = this.createLabel("Probabilité Individu Sexué:", 250, space);
        JTextField tfProbabiliteIndividuSexue = this.createTextField(String.valueOf(Parameters.PROBABILITE_INDIVIDU_SEXUE), 250, space);

        JLabel labelMaximumReines = this.createLabel("Maximum Reines:", 300, space);
        JTextField tfMaximumReines = this.createTextField(String.valueOf(Parameters.MAXIMUM_REINES), 300, space);

        JLabel labelAgeLarve = this.createLabel("Âge Larve:", 350, space);
        JTextField tfAgeLarve = this.createTextField(String.valueOf(Parameters.AGE_LARVE), 350, space);

        JLabel labelAgeNymphe = this.createLabel("Âge Nymphe:", 400, space);
        JTextField tfAgeNymphe = this.createTextField(String.valueOf(Parameters.AGE_NYMPHE), 400, space);

        JLabel labelAgeAdulte = this.createLabel("Âge Adulte:", 450, space);
        JTextField tfAgeAdulte = this.createTextField(String.valueOf(Parameters.AGE_ADULTE), 450, space);

        JLabel labelBiaisMinimal = this.createLabel("Biais Minimal:", 500, space);
        JTextField tfBiaisMinimal = this.createTextField(String.valueOf(Parameters.BIAIS_MINIMAL), 500, space);

        JButton applyButton = new JButton("Appliquer");
        applyButton.setBounds(850, 550, 120, 30);
        applyButton.addActionListener(_ -> {
            try {
                Parameters.PROBABILITE_OUVRIERE = Integer.parseInt(tfProbabiliteOuvriere.getText());
                Parameters.PROBABILITE_SOLDAT = Integer.parseInt(tfProbabiliteSoldat.getText());
                Parameters.PROBABILITE_INDIVIDU_SEXUE = Integer.parseInt(tfProbabiliteIndividuSexue.getText());
                Parameters.MAXIMUM_REINES = Integer.parseInt(tfMaximumReines.getText());
                Parameters.AGE_LARVE = Integer.parseInt(tfAgeLarve.getText());
                Parameters.AGE_NYMPHE = Integer.parseInt(tfAgeNymphe.getText());
                Parameters.AGE_ADULTE = Integer.parseInt(tfAgeAdulte.getText());
                Parameters.BIAIS_MINIMAL = Double.parseDouble(tfBiaisMinimal.getText());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(space, "Erreur : Veuillez entrer des valeurs valides.");
            }
        });
        space.add(applyButton);
    }

    private JSlider getjSlider(Simulation simulation, NiSpace space) {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        slider.setBounds(730, 30, 200, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(_ -> {
            int value = slider.getValue();
            simulation.setGraphicAnimationDelay(value);
            simulation.getTimer().setDelay(simulation.graphicAnimationDelay);
        });
        space.add(slider);
        return slider;
    }

    private JLabel createLabel(String text, int y, NiSpace space) {
        JLabel label = new JLabel(text);
        label.setBounds(730, y, 150, 30);
        space.add(label);
        return label;
    }

    private JTextField createTextField(String text, int y, NiSpace space) {
        JTextField textField = new JTextField(text);
        textField.setBounds(900, y, 80, 30);
        space.add(textField);
        return textField;
    }

    @Override
    public void redessine() {
    }
}
