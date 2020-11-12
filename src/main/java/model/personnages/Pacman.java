package model.personnages;

import engine.controller.Cmd;
import model.Monde;
import model.plateau.Case;
import model.plateau.Position;

/**
 * @author Tabary
 */
public class Pacman extends Personnage  {
    private int vie = 3 ;

    public Pacman(Monde monde, Position position){
        super(monde, position);
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est vers la droite
    }

    @Override
    public void move() {
        Case[] voisins = monde.getVoisins(position);
        switch (currentDirection){
            case LEFT:
                if(voisins[0].getCoutAcces() <= 10) {
                    position.moveLeft();
                }
                break;
            case UP:
                if(voisins[1].getCoutAcces() <= 10) {
                    position.moveUp();
                }
                break;
            case RIGHT:
                if(voisins[2].getCoutAcces() <= 10) {
                    position.moveRight();
                }
                break;
            case DOWN:
                if(voisins[3].getCoutAcces() <= 10) {
                    position.moveDown();
                }
                break;
        }
    }

    /**
     * Methode qui decrémente de 1 la vie de pacman
     */
    public void decreasedVie() {
        this.vie--;
    }

    /**
     * Retourne le nombre de vie de pacman
     * @return int vie
     */
    public int getVie() {
        return vie;
    }

    /**
     * Fixe le nombre de vie à Pacman
     * @param vie int le nombre de vie de Pacman
     */
    public void setVie(int vie) {
        this.vie = vie;
    }

    public void setDir(Cmd commande) {
        currentDirection = commande;
    }

}