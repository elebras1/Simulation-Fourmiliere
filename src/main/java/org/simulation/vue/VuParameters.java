package org.simulation.vue;

import lib.Nicellipse.src.nicellipse.component.NiSpace;
import org.simulation.parameter.Parameters;

import javax.swing.*;

public class VuParameters extends VueElement {
    private static final long serialVersionUID = -1082764164251478273L;

    public VuParameters(Simulation simulation, NiSpace space) {
        simulation.getTimer().stop();
        JSlider sliderWidth = this.getjSlider(simulation, space);
        space.add(sliderWidth);
        space.setLayout(null);

        this.createLabel("Heure:",730, 100, space);
        JTextField tfHeure = this.createTextField(String.valueOf(simulation.getSaisons().getHeure()), 900, 100, space);
        simulation.getTimer().addActionListener(e -> tfHeure.setText(String.valueOf(simulation.getSaisons().getHeure())));
        tfHeure.setEditable(false);

        this.createLabel("Probabilité Ouvrière:", 730, 150, space);
        JTextField tfProbabiliteOuvriere = this.createTextField(String.valueOf(Parameters.PROBABILITE_OUVRIERE),900, 150, space);

        this.createLabel("Probabilité Soldat:", 730, 200, space);
        JTextField tfProbabiliteSoldat = this.createTextField(String.valueOf(Parameters.PROBABILITE_SOLDAT),900, 200, space);

        this.createLabel("Probabilité Individu Sexué:", 730, 250, space);
        JTextField tfProbabiliteIndividuSexue = this.createTextField(String.valueOf(Parameters.PROBABILITE_INDIVIDU_SEXUE),900, 250, space);

        this.createLabel("Maximum Reines:", 730, 300, space);
        JTextField tfMaximumReines = this.createTextField(String.valueOf(Parameters.MAXIMUM_REINES),900, 300, space);

        this.createLabel("Âge Larve:", 730, 350, space);
        JTextField tfAgeLarve = this.createTextField(String.valueOf(Parameters.AGE_LARVE),900, 350, space);

        this.createLabel("Âge Nymphe:", 730, 400, space);
        JTextField tfAgeNymphe = this.createTextField(String.valueOf(Parameters.AGE_NYMPHE),900, 400, space);

        this.createLabel("Âge Adulte:", 730, 450, space);
        JTextField tfAgeAdulte = this.createTextField(String.valueOf(Parameters.AGE_ADULTE),900, 450, space);

        this.createLabel("Biais Minimal:", 730, 500, space);
        JTextField tfBiaisMinimal = this.createTextField(String.valueOf(Parameters.BIAIS_MINIMAL),900, 500, space);

        this.createLabel("Duree de vie min:", 730, 550, space);
        JTextField tfDureeVieMin = this.createTextField(String.valueOf(Parameters.DUREE_VIE_MIN), 900,550, space);

        this.createLabel("Duree de vie max:", 730, 600, space);
        JTextField tfDureeVieMax = this.createTextField(String.valueOf(Parameters.DUREE_VIE_MAX),900, 600, space);

        this.createLabel("Nombre max de proie:", 730, 650, space);
        JTextField tfNombreDeProie = this.createTextField(String.valueOf(Parameters.NOMBRE_PROIE_MAX),900, 650, space);

        this.createLabel("Temps attente max : ", 1000, 100, space);
        JTextField tfTempsAttenteMax = this.createTextField(String.valueOf(Parameters.TEMPS_ATTENTE_MAX),1130,  100, space);

        this.createLabel("Changement de saison : ", 1000, 150, space);
        JTextField tfChangementSaison = this.createTextField(String.valueOf(Parameters.CHANGEMENT_SAISON),1130,  150, space);

        this.createLabel("Pheromone deposer : ", 1000, 200, space);
        JTextField tfPheromoneDeposer = this.createTextField(String.valueOf(Parameters.PHEROMONE_DEPOSER),1130,  200, space);

        this.createLabel("Faim : ", 1000, 250, space);
        JTextField tfFaim = this.createTextField(String.valueOf(Parameters.FAIM),1130,  250, space);

        this.createLabel("Poids min proie : ", 1000, 300, space);
        JTextField tfPoidsMinProie = this.createTextField(String.valueOf(Parameters.POIDS_MIN_PROIE),1130,  300, space);

        this.createLabel("Poids max proie : ", 1000, 350, space);
        JTextField tfPoidsMaxProie = this.createTextField(String.valueOf(Parameters.POIDS_MAX_PROIE),1130,  350, space);

        this.createLabel("Nombre naissance : ", 1000, 400, space);
        JTextField tfNombreNaissance = this.createTextField(String.valueOf(Parameters.NOMBRE_NAISSANCE),1130,  400, space);

        this.createLabel("Vitesse ponte : ", 1000, 450, space);
        JTextField tfVitessePonte = this.createTextField(String.valueOf(Parameters.VITESSE_PONTE),1130,  450, space);

        this.createLabel("Stock nourriture : ", 1000, 500, space);
        JTextField tfStockNourriture = this.createTextField(String.valueOf(Parameters.STOCK_NOURITURE),1130,  500, space);

        this.createLabel("Temps avant faim : ", 1000, 550, space);
        JTextField tfTempsAvantFaim = this.createTextField(String.valueOf(Parameters.TEMPS_AVANT_FAIM),1130,  550, space);

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
                Parameters.TEMPS_ATTENTE_MAX = Integer.parseInt(tfTempsAttenteMax.getText());
                Parameters.CHANGEMENT_SAISON = Integer.parseInt(tfChangementSaison.getText());
                Parameters.PHEROMONE_DEPOSER = Integer.parseInt(tfPheromoneDeposer.getText());
                Parameters.FAIM = Integer.parseInt(tfFaim.getText());
                Parameters.POIDS_MIN_PROIE = Integer.parseInt(tfPoidsMinProie.getText());
                Parameters.POIDS_MAX_PROIE = Integer.parseInt(tfPoidsMaxProie.getText());
                Parameters.NOMBRE_NAISSANCE = Integer.parseInt(tfNombreNaissance.getText());
                Parameters.VITESSE_PONTE = Integer.parseInt(tfVitessePonte.getText());
                Parameters.STOCK_NOURITURE = Integer.parseInt(tfStockNourriture.getText());
                Parameters.TEMPS_AVANT_FAIM = Integer.parseInt(tfTempsAvantFaim.getText());
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

        JButton startButton = new JButton("Start");
        startButton.setBounds(1070, 700, 120, 30);
        startButton.addActionListener(_ -> {
            if(simulation.getTimer().isRunning()) {
                simulation.getTimer().stop();
                startButton.setText("Start");
            } else {
                simulation.getTimer().start();
                startButton.setText("Stop");
            }
        });
        space.add(startButton);
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

    private void createLabel(String text, int x, int y, NiSpace space) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 30);
        space.add(label);
    }

    private JTextField createTextField(String text, int x, int y, NiSpace space) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, 80, 30);
        space.add(textField);
        return textField;
    }

    @Override
    public void redessine() {
    }
}
