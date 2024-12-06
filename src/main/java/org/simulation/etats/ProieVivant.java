package org.simulation.etats;

import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class ProieVivant extends Etat {



    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
        Proie proie = (Proie) contexte.getIndividu();
        this.estAttaque(proie);
        if(proie.getFourmisSurProie().isEmpty()){

            int x = proie.getPos().x;
            int y = proie.getPos().y;
            int pos;
            Random rand = new Random();
            pos = rand.nextInt(4);
            switch (pos) {
                case 0: {
                    y = y + 1;
                    break;
                }
                case 1: {
                    x = x + 1;
                    break;
                }
                case 2: {
                    y = y - 1;
                    break;
                }
                case 3: {
                    x = x - 1;
                    break;
                }
            }
            proie.setPos(new Point(x,y));
        }

    }
    private void estAttaque(Proie proie){
        if(proie.getFourmisSurProie().isEmpty()){
            proie.setTempsAttente(0);
            return;}
        boolean fourmisNecessaires = proie.getNombreFourmisNecessaires();
        if (!fourmisNecessaires) {
            proie.setTempsAttente( proie.getTempsAttente()+1);
            if (proie.getTempsAttente() >= Parameters.TEMPS_ATTENTE_MAX) {
                for (Fourmi fourmi : proie.getFourmisSurProie()) {
                    fourmi.setAction(Action.DECOUVERTE);
                }
                proie.setEtat(new EnFuite());
            }
        } else{
            for (Fourmi fourmi : proie.getFourmisSurProie()) {
                fourmi.setAction(Action.DECOUVERTE);
            }
            proie.getFourmisSurProie().clear();
            proie.setEtat(new ProieMort());
        }
        for(int i=0;i<proie.getFourmisSurProie().size();i++){
            if(proie.getFourmisSurProie().get(i).getEtat() instanceof  Mort){
                proie.getFourmisSurProie().remove(proie.getFourmisSurProie().get(i));
            }
        }
    }


    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.GREEN);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

    }

    @Override
    public void actionSiAttaquer(ContexteDeSimulation contexte,Individu individu) {
        Proie proie = (Proie) contexte.getIndividu();
        Fourmi fourmi= (Fourmi) individu;
        double distance = proie.getPos().distance(individu.getPos());
        if (distance <= 5 && !proie.getFourmisSurProie().contains(fourmi) && fourmi.getPortProie() == null && !fourmi.getaFaim()) {
            proie.getFourmisSurProie().add(fourmi);
            fourmi.setAction(Action.CHASSE);
        }
    }

    @Override
    public void gestionDeFaim(ContexteDeSimulation contexte) {

    }

    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire("Proie vivante");
    }
}
