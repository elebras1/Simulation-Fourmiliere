package org.simulation.etats;

import org.simulation.etresVivants.Individu;
import org.simulation.etresVivants.Proie;
import org.simulation.fourmiliere.Bilan;
import org.simulation.vue.*;

import javax.swing.*;
import java.awt.*;

public class EstPorte extends Etat{


    @Override
    public void etapeDeSimulation(ContexteDeSimulation contexte) {
        Proie proie = (Proie) contexte.getIndividu();
        if(!(proie.getEstPortePar().getEtat() instanceof Adulte)){
            proie.setEtat(new ProieMort());
            proie.getEstPortePar().setPortProie(null);
            proie.setEstPortePar(null);


        }else{
            proie.setPos(proie.getEstPortePar().getPos());
        }
    }

    public void initialise(VueIndividu vue ) {
        vue.setBackground(Color.cyan);
        vue.setDimension(new Dimension(5, 5));
        vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));

    }

    @Override
    public void actionSiAttaquer(ContexteDeSimulation contexte, Individu individu) {

    }

    @Override
    public void gestionDeFaim(ContexteDeSimulation contexte) {

    }

    @Override
    public void bilan(Bilan bilan) {
        bilan.inscrire("Est porté");
    }

}
