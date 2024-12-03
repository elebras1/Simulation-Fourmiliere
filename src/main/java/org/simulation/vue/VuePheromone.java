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
        this.setBackground(new Color(0, 0, 0, 0));
    }

    private void updateBuffer() {
        int[][] pheromones = this.pheromone.getPheromones();
        Graphics2D g2d = this.buffer.createGraphics();

        for (int x = 0; x < pheromones.length; x++) {
            for (int y = 0; y < pheromones[0].length; y++) {
                int gradient = Math.min(255, pheromones[x][y]);
                if (gradient != 0) {
                    int green = (int)(gradient * 0.5);
                    int blue = (int)(gradient * 0.75);
                    int color = (0xFF << 24) | (gradient << 16) | (green << 8) | blue;
                    g2d.setColor(new Color(color, true));
                    g2d.fillRect(x, y, 1, 1);
                }
            }
        }
        g2d.dispose();
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
