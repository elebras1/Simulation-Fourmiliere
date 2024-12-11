[![Langage](https://img.shields.io/badge/Langage-Java-orange.svg)](https://www.java.com)
[![Framework](https://img.shields.io/badge/Library-JavaFx-red.svg)](https://openjfx.io/)

# Simulation de Fourmilière en Java avec Swing

Ce projet est une simulation de fourmilière, réalisée en Java avec l'interface graphique Swing. Il permet de paramétrer, de lancer et de stopper la simulation.

## Fonctionnalités

### Système de reproduction

Les fourmis peuvent se reproduire dans la simulation, permettant une dynamique d'évolution de la colonie.

### Gestion de la nourriture

Un système complexe permet de gérer la collecte et la consommation de nourriture, incluant :

- **Chasse de proies** : les proies se déplacent sur le terrain, et les fourmis doivent les traquer pour assurer leur survie.
- **Déplacement des proies** : les proies réagissent à la présence des fourmis.

### Système de phéromones

Un système de phéromones est implémenté pour guider les déplacements des fourmis. Chaque case de la matrice contient un gradient de phéromone, avec une valeur maximale de 255 et un degré de transparence correspondant.

### Déplacement des fourmis

Les fourmis ont trois actions possibles :

- **Découverte** : Elles explorent le terrain aléatoirement avec un biais minimal vers les zones à faible gradient de phéromone.
- **Suivre** : Elles se dirigent vers les zones à fort gradient de phéromone.
- **Chasse** : Elles cessent de bouger lorsqu'elles traquent une proie.

### Proies

Les proies possèdent différents états :

- **Proie Vivante** :
  - Se déplace aléatoirement sur le terrain.
  - Si elle croise une fourmi, elle s'arrête et la fourmi passe en mode "Chasse".
  - Si la fourmi ne peut pas tuer la proie et qu'aucune autre fourmi ne vient l'aider, la proie s'enfuit (passe en état **En Fuite**).
  - Si la/les fourmi(s) tuent la proie, celle-ci passe en état **Proie Morte**.
- **Proie Morte** : Attend qu'une fourmi la transporte (état **Est Portée**).
- **Est Portée** : La proie suit le déplacement de son porteur.
  - Si le porteur meurt, la proie retombe au sol (revient à l'état **Proie Morte**).
  - Si la proie transportée arrive à la fourmilière, elle disparaît et augmente les réserves de nourriture en fonction de son poids.

## Améliorations Prévues

1. **Traitement des déchets** : Gestion des déchets produits par la colonie.
2. **Intégration d'une nouvelle fourmilière** : Ajout d'une autre colonie pour enrichir la simulation.
3. **Amélioration du système de déplacement** : Optimisation des trajectoires et comportements des fourmis.
