package org.simulation.etresVivants;

import org.simulation.etats.*;
import org.simulation.parameter.*;
import org.simulation.terrain.*;
import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Proie  extends Individu {
    private static final double POIDS_MIN = 2.0;
    private static final double POIDS_MAX = 10.0;
    private Etat etat;
    private List<Fourmi> fourmisSurProie;
    private int tempsAttente;

    private Fourmi estPortePar;

    public Proie(Point point) {
        this.pos = point;
        this.setPoids(genererPoids());
        this.etat = new ProieVivant();
        this.fourmisSurProie = new ArrayList<>();
        this.tempsAttente = 0;
        this.estPortePar = null;
    }
    private double genererPoids() {
        Random random = new Random();
        return POIDS_MIN + (POIDS_MAX - POIDS_MIN) * random.nextDouble();
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
        this.getVuObserver().notifyVu();
    }

    public Boolean getNombreFourmisNecessaires() {
        double poidsProie = this.getPoids();
        double poidsFourmi =0;
        for(Fourmi fourmi : fourmisSurProie){
            poidsFourmi+=fourmi.getPoids()*2;
        }
        return poidsFourmi>=poidsProie;
    }

    @Override
    public void initialise(VueIndividu vue) {
        this.etat.initialise(vue);
    }

    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
        super.etapeDeSimulation(contexte);
        this.etat.etapeDeSimulation(contexte);
        for (Fourmi fourmi : contexte.getFourmiliere().getPopulation()) {
            this.etat.actionSiAttaquer(contexte,fourmi);
        }

    }


    public Fourmi getEstPortePar() {
        return estPortePar;
    }

    public void setEstPortePar(Fourmi estPortePar) {
        this.estPortePar = estPortePar;
    }

    public int getTempsAttente() {
        return tempsAttente;
    }

    public void setTempsAttente(int tempsAttente) {
        this.tempsAttente = tempsAttente;
    }

    public List<Fourmi> getFourmisSurProie() {
        return fourmisSurProie;
    }

    public void setFourmisSurProie(List<Fourmi> fourmisSurProie) {
        this.fourmisSurProie = fourmisSurProie;
    }
    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire(this.getClass().getSimpleName());
    }

}
