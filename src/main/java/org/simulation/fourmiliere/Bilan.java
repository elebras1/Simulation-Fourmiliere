package org.simulation.fourmiliere;

import java.util.HashMap;
import java.util.Map;

public class Bilan {
    Map<String, Integer> bilan;

    public Bilan() {
        this.bilan = new HashMap<>();
    }

    public void inscrire(String key) {
        if (this.bilan.containsKey(key)) {
            this.bilan.put(key, this.bilan.get(key) + 1);
        } else {
            this.bilan.put(key, 1);
        }
    }

    public void clear() {
        this.bilan.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int total = 0;
        for(Map.Entry<String, Integer> entry : this.bilan.entrySet()) {
            result.append(entry.getKey()).append(" : ").append(entry.getValue()).append("; ");
            total += entry.getValue();
        }

        return result + "Total : " + total;
    }
}
