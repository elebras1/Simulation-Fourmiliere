package org.simulation.terrain;

import java.awt.*;

public class Pheromone {
    private int[][] pheromones;

    public Pheromone(Dimension dimension) {
        this.pheromones = new int[dimension.height][dimension.width];
    }

    public void incrementerPheromone(int x, int y) {
        this.pheromones[x][y] += 3;
    }

    public void decrementerPheromone(int x, int y) {
        this.pheromones[x][y]--;
    }

    public int getPheromone(int x, int y) {
        return this.pheromones[x][y];
    }
}
