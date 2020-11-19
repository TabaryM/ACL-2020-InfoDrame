package model.personnages;

import dataFactories.ImageFactory;
import model.Monde;
import model.personnages.Fantome;
import model.plateau.Position;

import java.awt.image.BufferedImage;

public class FantomePeureux extends Fantome {
    public FantomePeureux(Monde monde, Position position, Position pacmanPosition) {
        super(monde, position, pacmanPosition);
    }

    @Override
    public void ia() {

    }

    @Override
    public void moveConcret() {

    }

    @Override
    public BufferedImage getImage() {
        return ImageFactory.getInstance().getFantomePeureux();
    }
}