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

        this.createLabel("Heure:", 100, space);
        JTextField tfHeure = this.createTextField(String.valueOf(simulation.getSaisons().getHeure()), 100, space);
        simulation.getTimer().addActionListener(e -> tfHeure.setText(String.valueOf(simulation.getSaisons().getHeure())));
        tfHeure.setEditable(false);

        this.createLabel("Probabilité Ouvrière:", 150, space);
        JTextField tfProbabiliteOuvriere = this.createTextField(String.valueOf(Parameters.PROBABILITE_OUVRIERE), 150, space);

        this.createLabel("Probabilité Soldat:", 200, space);
        JTextField tfProbabiliteSoldat = this.createTextField(String.valueOf(Parameters.PROBABILITE_SOLDAT), 200, space);

        this.createLabel("Probabilité Individu Sexué:", 250, space);
        JTextField tfProbabiliteIndividuSexue = this.createTextField(String.valueOf(Parameters.PROBABILITE_INDIVIDU_SEXUE), 250, space);

        this.createLabel("Maximum Reines:", 300, space);
        JTextField tfMaximumReines = this.createTextField(String.valueOf(Parameters.MAXIMUM_REINES), 300, space);

        this.createLabel("Âge Larve:", 350, space);
        JTextField tfAgeLarve = this.createTextField(String.valueOf(Parameters.AGE_LARVE), 350, space);

        this.createLabel("Âge Nymphe:", 400, space);
        JTextField tfAgeNymphe = this.createTextField(String.valueOf(Parameters.AGE_NYMPHE), 400, space);

        this.createLabel("Âge Adulte:", 450, space);
        JTextField tfAgeAdulte = this.createTextField(String.valueOf(Parameters.AGE_ADULTE), 450, space);

        this.createLabel("Biais Minimal:", 500, space);
        JTextField tfBiaisMinimal = this.createTextField(String.valueOf(Parameters.BIAIS_MINIMAL), 500, space);

        this.createLabel("Duree de vie min:", 550, space);
        JTextField tfDureeVieMin = this.createTextField(String.valueOf(Parameters.DUREE_VIE_MIN), 550, space);

        this.createLabel("Duree de vie max:", 600, space);
        JTextField tfDureeVieMax = this.createTextField(String.valueOf(Parameters.DUREE_VIE_MAX), 600, space);

        this.createLabel("Nombre max de proie:", 650, space);
        JTextField tfNombreDeProie = this.createTextField(String.valueOf(Parameters.NOMBRE_PROIE_MAX), 650, space);

        JButton applyButton = new JButton("Appliquer");
        applyButton.setBounds(730, 700, 120, 30);
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
                Parameters.DUREE_VIE_MIN = Integer.parseInt(tfDureeVieMin.getText());
                Parameters.DUREE_VIE_MAX = Integer.parseInt(tfDureeVieMax.getText());
                Parameters.NOMBRE_PROIE_MAX = Integer.parseInt(tfNombreDeProie.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(space, "Erreur : Veuillez entrer des valeurs valides.");
            }
        });
        space.add(applyButton);

        JButton afficheTraceButton = new JButton("Afficher Trace");
        afficheTraceButton.setBounds(900, 700, 120, 30);
        afficheTraceButton.addActionListener(_ -> {
            if(!Parameters.AFFICHER_TRACE) {
                Parameters.AFFICHER_TRACE = true;
                afficheTraceButton.setText("Cacher Trace");
            } else {
                Parameters.AFFICHER_TRACE = false;
                afficheTraceButton.setText("Afficher Trace");
            }
        });
        space.add(afficheTraceButton);
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
            simulation.getTimer().setDelay(simulation.getGraphicAnimationDelay());
        });
        space.add(slider);
        return slider;
    }

    private void createLabel(String text, int y, NiSpace space) {
        JLabel label = new JLabel(text);
        label.setBounds(730, y, 150, 30);
        space.add(label);
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
