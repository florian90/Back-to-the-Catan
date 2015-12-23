package model.jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.joueur.Joueur;

public class Jeu
{
	/*
	 * Gère tous les joueurs ainsi que tous les plateaux.
	 */
	private HashMap<Epoque, Plateau> plateaux;
	private ArrayList<Joueur> joueurs;
	private int nbJoueurs, joueurCourant;

	/*
	 * Construit le modele de jeu à partir de la liste des joueurs 
	 * fournie par le menu principal, intialise les plateux de jeu
	 */
	public Jeu(ArrayList<Joueur> p_joueurs)
	{
		joueurs = p_joueurs;
		joueurCourant=0; //index du joueur dont le tour est en cours
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

	//Todo: Effectue le tour de jeu pour un joueur donnÃ©
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
	/*
	 * Retourne un tableau d'entier contenant le 
	 * résultat du dé n°1, le resultat du dé n°2
	 * et la somme des deux.
	 */
	public int[] lancerDes()
	{
		Random rnd = new Random();
		int de1 = rnd.nextInt(6 ) + 1;
		int de2 = rnd.nextInt(6 ) + 1;
		
		int tab[] = {de1,de2,de1+de2};
		
		return tab;
		
	}
	
	public HashMap<Epoque, Plateau> getPlateaux() 
	{
		return plateaux;
	}
	
	public int getJoueurCourant() 
	{
		return joueurCourant;
	}

	public void setJoueurCourant(int joueurCourant)
	{
		this.joueurCourant = joueurCourant;
	}
}
