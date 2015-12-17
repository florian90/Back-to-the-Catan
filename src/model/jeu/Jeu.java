package model.jeu;

import java.util.ArrayList;
import java.util.HashMap;

import model.joueur.Joueur;

public class Jeu
{
	/*
	 * GËre tous les joueurs ainsi que tous les plateaux.
	 */
	private HashMap<Epoque, Plateau> plateaux;
	private ArrayList<Joueur> joueurs;
	private int nbJoueurs;

	/*
	 * Construit le modele de jeu ‡ partir de la liste des joueurs 
	 * fournie par le menu principal, intialise les plateux de jeu
	 */
	public Jeu(ArrayList<Joueur> p_joueurs)
	{
		joueurs = p_joueurs;
		nbJoueurs = joueurs.size();
		plateaux = new HashMap<Epoque,Plateau>();
		plateaux.put(Epoque._1855, new Plateau(Epoque._1985, 7));
		plateaux.put(Epoque._1955, new Plateau(Epoque._1955, 7));
		plateaux.put(Epoque._1985, new Plateau(Epoque._1985, 7));
		plateaux.put(Epoque._2015, new Plateau(Epoque._2015, 7));
		initJeu();
	}



	//Todo: Initialise toutes les instances du jeu
	public void initJeu()
	{

	}

	//Todo: Effectue le tour de jeu pour un joueur donn√©
	public void tourJoueur()
	{

	}

	public int getNbJoueurs()
	{
		return nbJoueurs;
	}

	public ArrayList<Joueur> getJoueurs()
	{
		return joueurs;
	}
	public HashMap<Epoque, Plateau> getPlateaux() 
	{
		return plateaux;
	}
}
