package org.simulation.terrain;

import java.awt.*;

public class Pheromone {
    private final int[][] pheromones;

    public Pheromone(Dimension dimension) {
        this.pheromones = new int[dimension.height][dimension.width];
    }

    public int[][] getPheromones() {
        return this.pheromones;
    }

    public void deposerPheromone(int x, int y) {
        this.pheromones[x][y] += 3;
    }

    public void reduirePheromone(int x, int y) {
        if(this.pheromones[x][y] > 0) {
            this.pheromones[x][y]--;
        }
    }

    public int getPheromone(int x, int y) {
        return this.pheromones[x][y];
    }
}
