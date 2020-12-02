package model.personnages;

import dataFactories.ImageFactory;
import engine.controller.Cmd;
import model.Drawable;
import exception.PacmanException;
import model.Monde;
import model.Piece;
import model.plateau.Case;
import model.plateau.Position;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import java.util.Collection;

import static engine.GameEngineGraphical.TIMESTEP;

/**
 * @author Tabary
 */
public class Pacman extends Personnage {
    private int vie = 3 ;
    private double timeToKill = 0.0;

    public Pacman(Monde monde, Position position){
        super(monde, new Position(position));
        this.currentDirection = Cmd.IDLE; // L'orientation initiale de Pacman est en sur place
    }

    /**
     * Procédure de vie de Pacman pour une itération de jeu
     */
    @Override
    public void live(){
        // Test collision avec pièce
        grabCoin();
        // Mise a jour des effets
        updateEffects();
    }

    private void updateEffects() {
        reduceTimeToKill();
    }

    private void reduceTimeToKill() {
        if(isAggressif()) {
            timeToKill -= TIMESTEP;
        } else {
            timeToKill = 0;
        }
    }

    /**
     * Procédure qui permet à Pacman de ramasser une pièce.
     * Si une pièce est ramassée, la retire du monde.
     * Augmente le score du joueur de la valeur de la pièce.
     * Augmente le temps d'attaque du joueur du temps accordé par la pièce.
     */
    private void grabCoin() {
        Piece piece = monde.grabPieceAt(position);
        if(piece != null){
            monde.increaseScore(piece.getScore());
            increaseTimeToKill(piece.getAttackTime());
        }
    }

    /**
     * Augmente le temps d'attaque du joueur par la valeur passée en paramètre
     * @throws PacmanException Si la valeur passée en paramètre est négative.
     *                         Si la somme de val et du champ timeToKill est négative.
     * @param val temps d'attaque supplémentaire ajouté
     */
    private void increaseTimeToKill(double val) throws PacmanException {
        if(val < 0){
            throw new PacmanException("Temps d'attaque négatif");
        }
        if (timeToKill + val < 0){
            throw new PacmanException("Temps d'attaque négatif");
        }
        timeToKill += val * 1000;
    }

    /**
     * Procédure qui vérifie si il y a une situation d'attaque entre Pacman et un fantôme (dans les deux sens)
     */
    public void attack() {
        Collection<Personnage> personnages = monde.getPersonnagesAt(position);
        personnages.remove(this);
        if(isAggressif()){
            for (Personnage p : personnages){
                monde.kill(p);
            }
        }
    }

    /**
     * Procédure de déplacement du joueur.
     * Si la case devant Pacman est un couloir Pacman se déplacera sur cette case,
     * Sinon (si la case est un mur) Pacman restera sur place
     */
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

    @Override
    public void resetPosition() {
        Position posInit = monde.getPosInitPacman();
        position.setX(posInit.getX());
        position.setY(posInit.getY());
    }

    @Override
    public void die() {
        monde.decreasedVie();
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

    @Override
    public BufferedImage getImage() {
        BufferedImage img;
        BufferedImage imgRotate;

        if (this.currentDirection.equals(Cmd.IDLE)) {
            img = ImageFactory.getInstance().getPacman();

        } else {
            img = ImageFactory.getInstance().getPacmanAnim();
        }

        switch (this.currentDirection) {

            case UP: {
                imgRotate = rotateImageByDegrees(img, 270);
            }
            break;

            case DOWN: {
                imgRotate = rotateImageByDegrees(img, 90);
            }
            break;

            case LEFT: {
                imgRotate = rotateImageByDegrees(img, 180);
            }
            break;

            default: imgRotate = ImageFactory.getInstance().getPacmanAnim();
            break;


        }

        return imgRotate;
    }
    /**
     * Méthode permettant de dire si Pacman est en train d'attaquer
     * @return vrai si Pacman peut manger les fantômes,
     *         faux sinon.
     */
    public boolean isAggressif(){
        return timeToKill > 0.000001;
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w*cos + h*sin);
        int newHeight = (int) Math.floor(h*cos + w*sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2D = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w/2;
        int y = h/2;

        at.rotate(rads, x, y);
        g2D.setTransform(at);
        g2D.drawImage(img, 0, 0, null);
        g2D.setColor(Color.RED);
        g2D.drawRect(0, 0, newWidth - 1, newHeight - 1);
        g2D.dispose();

        return rotated;

    }
}
