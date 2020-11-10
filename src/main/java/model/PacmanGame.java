package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.controller.Cmd;
import engine.controller.Game;
import interfaceModel.Monde;
import model.plateau.Labyrinthe;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private final Monde monde;

	/**
	 * constructeur avec fichier source pour le help
	 */
	public PacmanGame(String source) {
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				System.out.println(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		monde = new MondePacman(new Labyrinthe("src/main/resources/labyClassic.txt"));
	}

	/**
	 * Constructeur sans fichier source pour le help
	 */
	public PacmanGame(){
		monde = new MondePacman(new Labyrinthe("src/main/resources/labyClassic.txt"));
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande Cmd input saisis par l'utilisateur
	 */
	@Override
	public void evolve(Cmd commande) {
		// System.out.println("Execute "+commande);
		monde.setJoueurDir(commande);
		monde.nextStep();
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}

	/**
	 * Retourne le monde afin de le faire suivre par la vue graphique
	 * @return monde
	 */
	public Monde getMonde() {
		return monde;
	}
}
