package model.jeu;

import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Joueur;
import model.joueur.PackRess;
import model.joueur.Ressource;
import model.joueur.TypeCarte;
import vue.jeu.Fenetre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Jeu {
	/*
	 * Gère tous les joueurs ainsi que tous les plateaux.
	 */
	private HashMap<Epoque, Plateau> plateaux;
	private ArrayList<Joueur> joueurs;
	private int nbJoueurs, joueurActuel;
	private Epoque epoqueActuelle;

	private Fenetre m_vue;
	
	/*
	 * Construit le modele de jeu à partir de la liste des joueurs 
	 * fournie par le menu principal, intialise les plateux de jeu
	 */
	public Jeu(ArrayList<Joueur> p_joueurs)
	{
		epoqueActuelle = Epoque._1985; 
		joueurs = p_joueurs;
		for(Joueur j : joueurs)
		{
			j.setM_jeu(this);
		}
		joueurs.get(0).recevoirRessources(new PackRess(Ressource.Metal, Ressource.HautParleur,Ressource.Metal, Ressource.HautParleur,Ressource.Metal, Ressource.HautParleur,Ressource.Metal, Ressource.HautParleur,Ressource.Metal, Ressource.HautParleur));//FixMe: remove this
		joueurActuel = 0; //index du joueur dont le tour est en cours
		nbJoueurs = joueurs.size();
		plateaux = new HashMap<Epoque, Plateau>();
		for(Epoque epoque : Epoque.values())
		{
			plateaux.put(epoque, new Plateau(epoque, 7));
		}
	}

	//Todo: Initialise toutes les instances du jeu
	public void initJeu()
	{
		// Initialise ce que les joueurs possèdent  au début
		epoqueModifiee();
		m_vue.initTourJoueur();
	}

	/** Setters classiques **/
	public void setVue(Fenetre fen)
	{
		m_vue = fen;
	}

	/** getters classiques **/
	public int getNbJoueurs()
	{
		return nbJoueurs;
	}

	public Plateau getPlateau(Epoque epoque)
	{
		return plateaux.get(epoque);
	}

	/*
	 * Retourne un tableau d'entier contenant le 
	 * résultat du dé n°1, le résultat du dé n°2
	 * et la somme des deux.
	 */
	public void lancerDes()
	{
		Random rnd = new Random();
		int de1 = rnd.nextInt(6) + 1;
		int de2 = rnd.nextInt(6) + 1;
		
		int tab[] = {de1, de2};
		m_vue.lanceDes(tab);
		
	}
	
	public TypeCarte tirerCarte()
	{
		Random rnd = new Random();
		int res = rnd.nextInt(2) ; //Todo: à redéfinir pour modifier la fréquence d'apparition des cartes
		
		if (res == 1)
		{
			return TypeCarte.Developpement;
		}
		else
		{
			return TypeCarte.DeplacerVoleur;
		}

	}

	public void joueurSuivant()
	{
		if(++joueurActuel >= nbJoueurs)
			joueurActuel = 0;
		m_vue.initTourJoueur();
	}

	public Joueur getJoueur()
	{
		return joueurs.get(joueurActuel);
	}

	public Joueur getJoueur(int i)
	{
		return joueurs.get(i);
	}

	public Epoque getEpoqueActuelle() {
		return epoqueActuelle;
	}

	public Epoque epoqueSuivante(){
		switch (epoqueActuelle)
		{
			case _1855:
				epoqueActuelle=Epoque._1955;
				break;
			case _1955:
				epoqueActuelle=Epoque._1985;
				break;
			case _1985:
				epoqueActuelle=Epoque._2015;
				break;
			case _2015:
				epoqueActuelle=Epoque._1855;
				break;
		}
		epoqueModifiee();
		return epoqueActuelle;
	}

	public Epoque epoquePrecedente(){
		switch (epoqueActuelle)
		{
			case _1855:
				epoqueActuelle=Epoque._2015;
				break;
			case _1955:
				epoqueActuelle=Epoque._1855;
				break;
			case _1985:
				epoqueActuelle=Epoque._1955;
				break;
			case _2015:
				epoqueActuelle=Epoque._1985;
				break;
		}
		epoqueModifiee();
		return epoqueActuelle;
	}
	public void epoqueModifiee()
	{
		m_vue.chargerPlateau(epoqueActuelle);
	}
}

