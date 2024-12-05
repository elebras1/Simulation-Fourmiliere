package org.simulation.vue;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.parameter.Parameters;

import javax.swing.*;

public class VuParameters extends VueElement {
    private static final long serialVersionUID = -1082764164251478273L;

    public VuParameters(Simulation simulation, NiSpace space) {
        JSlider sliderWidth = this.getjSlider(simulation);

        JLabel labelHeure = this.createLabel("Heure:", 100);
        JTextField tfHeure = this.createTextField(String.valueOf(simulation.getSaisons().getHeure()), 100);
        simulation.getTimer().addActionListener(e -> tfHeure.setText(String.valueOf(simulation.getSaisons().getHeure())));
        tfHeure.setEditable(false);

        space.setLayout(null);

        JLabel labelProbabiliteOuvriere = this.createLabel("Probabilité Ouvrière:", 150);
        JTextField tfProbabiliteOuvriere = this.createTextField(String.valueOf(Parameters.PROBABILITE_OUVRIERE), 150);

        JLabel labelProbabiliteSoldat = this.createLabel("Probabilité Soldat:", 200);
        JTextField tfProbabiliteSoldat = this.createTextField(String.valueOf(Parameters.PROBABILITE_SOLDAT), 200);

        JLabel labelProbabiliteIndividuSexue = this.createLabel("Probabilité Individu Sexué:", 250);
        JTextField tfProbabiliteIndividuSexue = this.createTextField(String.valueOf(Parameters.PROBABILITE_INDIVIDU_SEXUE), 250);

        JLabel labelMaximumReines = this.createLabel("Maximum Reines:", 300);
        JTextField tfMaximumReines = this.createTextField(String.valueOf(Parameters.MAXIMUM_REINES), 300);

        JLabel labelAgeLarve = this.createLabel("Âge Larve:", 350);
        JTextField tfAgeLarve = this.createTextField(String.valueOf(Parameters.AGE_LARVE), 350);

        JLabel labelAgeNymphe = this.createLabel("Âge Nymphe:", 400);
        JTextField tfAgeNymphe = this.createTextField(String.valueOf(Parameters.AGE_NYMPHE), 400);

        JLabel labelAgeAdulte = this.createLabel("Âge Adulte:", 450);
        JTextField tfAgeAdulte = this.createTextField(String.valueOf(Parameters.AGE_ADULTE), 450);

        JLabel labelBiaisMinimal = this.createLabel("Biais Minimal:", 500);
        JTextField tfBiaisMinimal = this.createTextField(String.valueOf(Parameters.BIAIS_MINIMAL), 500);

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

        space.add(sliderWidth);
        space.add(labelHeure);
        space.add(tfHeure);
        space.add(labelProbabiliteOuvriere);
        space.add(tfProbabiliteOuvriere);
        space.add(labelProbabiliteSoldat);
        space.add(tfProbabiliteSoldat);
        space.add(labelProbabiliteIndividuSexue);
        space.add(tfProbabiliteIndividuSexue);
        space.add(labelMaximumReines);
        space.add(tfMaximumReines);
        space.add(labelAgeLarve);
        space.add(tfAgeLarve);
        space.add(labelAgeNymphe);
        space.add(tfAgeNymphe);
        space.add(labelAgeAdulte);
        space.add(tfAgeAdulte);
        space.add(labelBiaisMinimal);
        space.add(tfBiaisMinimal);
        space.add(applyButton);
    }

    private JSlider getjSlider(Simulation simulation) {
        JSlider sliderWidth = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        sliderWidth.setBounds(730, 30, 200, 50);
        sliderWidth.setMajorTickSpacing(20);
        sliderWidth.setMinorTickSpacing(10);
        sliderWidth.setPaintTicks(true);
        sliderWidth.setPaintLabels(true);
        sliderWidth.addChangeListener(_ -> {
            int value = sliderWidth.getValue();
            simulation.setGraphicAnimationDelay(value);
            simulation.getTimer().setDelay(simulation.graphicAnimationDelay);
        });
        return sliderWidth;
    }

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(730, y, 150, 30);
        return label;
    }

    private JTextField createTextField(String text, int y) {
        JTextField textField = new JTextField(text);
        textField.setBounds(900, y, 80, 30);
        return textField;
    }

    @Override
    public void redessine() {
    }
}
