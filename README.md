# Simulation d'une Fourmiliere

Dans ce projet, une personne peut parametrer la simulation la lancer,la stopper.
Fait en Java,Swing.

Parmis les fonctionalité il y as le systeme de reproduction, de gestion de nourriture avec un systeme de chasses de proie qui ce déplacement, ainsi q'un systeme de pheromone pour gerer les déplacement des fourmis.

Parmis les améliorations il reste le traitement des déchets, intégration d'une nouvelle Fourmiliere, Amélioration du systeme de déplacement.

Pheromones : 
Matrice ou chaque case contient un gradient de pheromone.
la valeur de gradient maximal est 255, la valeur max de transparence.

Deplacement :
Une fourmis a 3 actions possible : Decouverte, Suivre, Chasse
Lorsque une ouvriere a l'action Decouverte ou Suivre un calcul avec des probabilités est fait en tenant compte des gradients avec un biais minimal afin s'orienter vers une direction. Une fourmi qui est sur Suivre se deplacera avec plus de chance sur les pheromones ayant des gradients élevés. le contraire pour Decouverte.
Chasse -> une ouvriere ne bouge pas.


