package org.simulation.vue;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.parameter.Parameters;

import javax.swing.*;

public class VuParameters extends VueElement {
    private static final long serialVersionUID = -1082764164251478273L;

    public VuParameters(Simulation simulation, NiSpace space) {
        JSlider sliderWidth = this.getjSlider(simulation);
        space.add(sliderWidth);

        space.setLayout(null);

        JLabel labelProbabiliteOuvriere = this.createLabel("Probabilité Ouvrière:", 700, 50);
        JTextField tfProbabiliteOuvriere = this.createTextField(String.valueOf(Parameters.PROBABILITE_OUVRIERE), 850, 50);

        JLabel labelProbabiliteSoldat = this.createLabel("Probabilité Soldat:", 700, 100);
        JTextField tfProbabiliteSoldat = this.createTextField(String.valueOf(Parameters.PROBABILITE_SOLDAT), 850, 100);

        JLabel labelProbabiliteIndividuSexue = this.createLabel("Probabilité Individu Sexué:", 700, 150);
        JTextField tfProbabiliteIndividuSexue = this.createTextField(String.valueOf(Parameters.PROBABILITE_INDIVIDU_SEXUE), 850, 150);

        JLabel labelMaximumReines = this.createLabel("Maximum Reines:", 700, 200);
        JTextField tfMaximumReines = this.createTextField(String.valueOf(Parameters.MAXIMUM_REINES), 850, 200);

        JLabel labelAgeLarve = this.createLabel("Âge Larve:", 700, 250);
        JTextField tfAgeLarve = this.createTextField(String.valueOf(Parameters.AGE_LARVE), 850, 250);

        JLabel labelAgeNymphe = this.createLabel("Âge Nymphe:", 700, 300);
        JTextField tfAgeNymphe = this.createTextField(String.valueOf(Parameters.AGE_NYMPHE), 850, 300);

        JLabel labelAgeAdulte = this.createLabel("Âge Adulte:", 700, 350);
        JTextField tfAgeAdulte = this.createTextField(String.valueOf(Parameters.AGE_ADULTE), 850, 350);

        JLabel labelBiaisMinimal = this.createLabel("Biais Minimal:", 700, 400);
        JTextField tfBiaisMinimal = this.createTextField(String.valueOf(Parameters.BIAIS_MINIMAL), 850, 400);

        JButton applyButton = new JButton("Appliquer");
        applyButton.setBounds(850, 450, 120, 30);
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
        return sliderWidth;
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        return label;
    }

    private JTextField createTextField(String text, int x, int y) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, 80, 30);
        return textField;
    }

    @Override
    public void redessine() {
    }
}
