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
        if(x >= 0 && x < this.pheromones.length && y >= 0 && y < this.pheromones[0].length && this.pheromones[x][y] < 256) {
            this.pheromones[x][y] = Math.min(255, this.pheromones[x][y] + 3);
        }
    }

    public void evaporation() {
        for(int x = 0; x < this.pheromones.length; x++) {
            for(int y = 0; y < this.pheromones[0].length; y++) {
                this.pheromones[x][y] = Math.max(0, this.pheromones[x][y] - 3);
            }
        }
    }

    public int getPheromone(int x, int y) {
        return this.pheromones[x][y];
    }
}
