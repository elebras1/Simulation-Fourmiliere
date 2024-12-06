package org.simulation.proies;

import org.simulation.etresVivants.Proie;
import org.simulation.fourmiliere.Fourmiliere;
import org.simulation.vue.ContexteDeSimulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Proies {

    private List<Proie> proies = new ArrayList<>();
    private static final int NOMBRE_PROIE_MAX = 500;

    private static final int MIN_DISTANCE_FROM_FOURMILIERE = 45;

    public List<Proie> getProies() {
        return proies;
    }

    public void etapeDeSimulation(ContexteDeSimulation contexte) {

        if (proies.size() <= NOMBRE_PROIE_MAX) {
            for(int i = 0; i < NOMBRE_PROIE_MAX; i++){
                spawnProieAleatoire(contexte);
            }
        }
        for (Proie proy : this.proies) {
            proy.etapeDeSimulation(contexte);
        }
    }
    private void spawnProieAleatoire(ContexteDeSimulation contexte) {

        Point position = generatePositionInZone(contexte);
        while (!isPositionValide(position,contexte)) {
            position = generatePositionInZone(contexte);
        }
        Proie nouvelleProie = new Proie(position);
        proies.add(nouvelleProie);
        contexte.getSimulation().nouvelIndividu(nouvelleProie);

    }

    private Point generatePositionInZone(ContexteDeSimulation contexte) {
        Dimension dim = contexte.getTerrain().getDimension();
        int zone = new Random().nextInt(4);
        return switch (zone) {
            case 0 -> generatePosition(0, 0, contexte);
            case 1 -> generatePosition(dim.width / 2, 0, contexte);
            case 2 -> generatePosition(0, dim.height / 2, contexte);
            case 3 -> generatePosition(dim.width / 2, dim.height / 2, contexte);
            default -> null;
        };
    }

    private Point generatePosition(int xOffset, int yOffset,ContexteDeSimulation contexte) {
        Dimension dim = contexte.getTerrain().getDimension();
        Point pos = contexte.getTerrain().getPos();
        Random random = new Random();
        int x = random.nextInt(dim.width / 2) + xOffset;
        int y = random.nextInt(dim.height / 2) + yOffset;
        return new Point(x + pos.x, y + pos.y);
    }

    private boolean isPositionValide(Point position,ContexteDeSimulation contexte) {
        Fourmiliere fourmiliere = contexte.getFourmiliere();
        Point posFourmiliere = fourmiliere.getPos();
        return posFourmiliere.distance(position) >= MIN_DISTANCE_FROM_FOURMILIERE;
    }
}
