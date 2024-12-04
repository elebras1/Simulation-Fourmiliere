package org.simulation.etresVivants;

import org.simulation.etats.*;
import org.simulation.vue.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Proie  extends Individu {
    private static final double POIDS_MIN = 2.0;
    private static final double POIDS_MAX = 10.0;
    private Etat etat;
    private List<Fourmi> fourmisSurProie = new ArrayList<>();
    private int tempsAttente;
    private static final int TEMPS_ATTENTE_MAX = 180;

    public Proie(Point point) {
        this.pos=point;

        this.setPoids(genererPoids());
        this.etat=new ProieVivant();
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
    }

    public Boolean getNombreFourmisNecessaires() {
        double poidsProie = this.getPoids();
        double poidsFourmi =0;
        for(Fourmi fourmi : fourmisSurProie){
            poidsFourmi+=fourmi.getPoids()*2;
        }
        return poidsFourmi>=poidsProie;
    }

    public void estAttaquer() {
        if(this.fourmisSurProie.isEmpty()){return;}
        boolean fourmisNecessaires = getNombreFourmisNecessaires();
        if (!fourmisNecessaires) {
            attendre();
        } else{
            this.setEtat(new ProieMort());
            this.getVuObserver().notifyVu();
        }
    }
    public void attendre() {
        this.tempsAttente++;
        if (this.tempsAttente >= TEMPS_ATTENTE_MAX) {
            this.setEtat(new EnFuite());
            this.getVuObserver().notifyVu();
        }
    }
    public void attaquerPar(Fourmi fourmi) {
        if (!(this.etat instanceof ProieVivant)) {return;}
        double distance =this.getPos().distance(fourmi.getPos());
        if (distance<=5 && !this.fourmisSurProie.contains(fourmi)) {
            this.fourmisSurProie.add(fourmi);
        }
    }
    @Override
    public void initialise(VueIndividu vue) {
        this.etat.initialise(vue);
    }

    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
        super.etapeDeSimulation(contexte);
        this.estAttaquer();
        this.etat.etapeDeSimulation(contexte);
    }
}
