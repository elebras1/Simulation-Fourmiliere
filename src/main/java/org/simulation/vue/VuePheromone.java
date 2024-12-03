package org.simulation.vue;

import org.simulation.terrain.Pheromone;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VuePheromone extends VueElement {
    private final Pheromone pheromone;
    private BufferedImage buffer;

    public VuePheromone(Pheromone pheromone, Point position) {
        this.pheromone = pheromone;
        this.setBounds(position.x, position.y, this.pheromone.getPheromones()[0].length, this.pheromone.getPheromones().length);
        this.buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.updateBuffer();
    }

    private void updateBuffer() {
        int[][] pheromones = this.pheromone.getPheromones();
        for (int y = 0; y < pheromones.length; y++) {
            for (int x = 0; x < pheromones[0].length; x++) {
                int gradient = Math.min(255, pheromones[y][x]);

                if(gradient == 0) {
                    this.buffer.setRGB(x, y, 0x00000000);
                } else {
                    int color = new Color(gradient, 0, 0).getRGB();
                    this.buffer.setRGB(x, y, color);
                }
            }
        }
    }

    @Override
    public void redessine() {
        this.updateBuffer();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.buffer, 0, 0, null);
    }
}
