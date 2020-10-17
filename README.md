# ACL_2020_InfoDrame

## Membres du groupe : 
Corentin Roberge-Mentec

Jordan Scherring

Mathieu Tabary

Paul-Emile Watelot

# Fonctionalités prévues et découpage par sprint

## Sprint 1
### Objectifs
 - 1 Déplacement du personnage-joueur sur une grille-plateau
 - 2 Affichage d’un HUD contenant les infos suivantes :
 
    - 2.1 Score

    - 2.2 Nombres de vies du joueur
    
- 3 Création de menus
 
    - 3.1. Menu principal

    - 3.2. Menu de pause

- 4 Génération d’un labyrinthe par défaut
 
    - 4.1. Lecture d’un fichier

    - 4.2. Les personnages dans le jeu ne peuvent pas traverser les murs du labyrinthe

    - 4.3. Créations de cases spéciales :

        - 4.3.1. Pièce : Quand traversée par le personnage-joueur, augmente le score

        - 4.3.2. Grosse pièce : Comme pièce + permet de manger les fantômes


### Répartition des responsabilité
Mathieu et Paul-Emile : 1 + 4.1 + 4.2

Corentin + Jordan : 2 + 3. + 4.3

### Diagrammes
#### Diagramme de classe
Objectif d'implémentation : 
![ClassDiag](conception/diagrammes/class/Objectif Sprint 1.png)

#### Diagrammes de séquence
Déroulement du jeu : 
![ClassDiag](conception/diagrammes/sequence/MainGame.png)

![ClassDiag](conception/diagrammes/sequence/UserPlaying.png)


### Fonctionnalités implémentées une fois le Sprint 1 fini
Il faut d'abord finir le sprint