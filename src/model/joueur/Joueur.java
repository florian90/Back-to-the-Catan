package model.joueur;

import java.util.HashMap;

import model.jeu.Arete;
import model.jeu.Point;

public class Joueur
{

	private String nom;
	private int numJoueur;
	private HashMap<Ressource, Integer> m_ressources;
	private HashMap<Invention, Integer> m_inventions;
	private HashMap<Point, Integer> m_pointsConstruits;
	private HashMap<Arete, Integer> m_aretesConstruites;
	private HashMap<Carte, Integer> cartes;
	
	public Joueur(String nom, int num)
	{
		this.nom = nom;
		numJoueur = num;
		m_ressources = new HashMap<Ressource, Integer>();
			m_ressources.put(Ressource.Aimant, 0);
			m_ressources.put(Ressource.Antenne, 0);
			m_ressources.put(Ressource.Bois, 0);
			m_ressources.put(Ressource.HautParleur, 0);
			m_ressources.put(Ressource.Metal, 0);
			m_ressources.put(Ressource.MorceauSchema, 0);
			m_ressources.put(Ressource.Plutonium, 0);
			m_ressources.put(Ressource.Roue, 0);
			m_ressources.put(Ressource.Ventilateur, 0);
			
		m_inventions = new HashMap<Invention, Integer>();
			m_inventions.put(Invention.ConvecteurTemporel, 0);
			m_inventions.put(Invention.HoverBoard, 0);
			m_inventions.put(Invention.Radio, 0);
			m_inventions.put(Invention.Train, 0);
			m_inventions.put(Invention.TrainKiVol, 0);
			
		m_pointsConstruits = new HashMap<Point, Integer>();
		m_aretesConstruites = new HashMap<Arete, Integer>();
		cartes = new HashMap<Carte, Integer>();
		
	}

	

	/*
	 * Fonction pour dépenser un certain nombre d'une ressources : dépense les ressources
	 */
	public void depenserRessources(Ressource res, int nombre)
	{
		m_ressources.put(res,m_ressources.get(res)-nombre);
	}

	/*
	 *  Ajoute la ressource au joueur
	 */
	public void recevoirRessources(Ressource res, int nombre)
	{
		m_ressources.put(res,m_ressources.get(res)+nombre);
	}

	/*
	 * Test si le joueur a les ressources passées en paramétres
	 */
	public boolean possede(Ressource res, int nombres)
	{
		return m_ressources.get(res)!=0;
	}
	
	/*
	 * Retourne le nombre de ressources que le joueur possède
	 * correspondant à la ressource passée en paramètres 
	 */
	public int nbRessource(Ressource res/*, int nombres*/)
	{
		return m_ressources.get(res);
	}
	
	/*
	 * Retourne vrai si le joueur possède l'invention 
	 * passée en paramètre
	 */
	public boolean possedeInvention(Invention inv)
	{
		return (m_inventions.get(inv)==1);
	}

	//Todo: Construit un nouvel élément au joueur( carte, route, ville, ...), dépense les ressources et ajoute l'objet au joueur
	public void construire()
	{

	}

	/*
	 * Echange des ressources avec un autre joueur
	 * le joueur courant (this) échange nb2 ressources de type res2
	 * contre nb1 ressources res1 de la part du joueur 'autre'
	 */
	public void echangerRessources(Joueur autre, Ressource res1, int nb1, Ressource res2, int nb2)
	{
		autre.depenserRessources(res1, nb1);
		autre.recevoirRessources(res2, nb2);
		this.depenserRessources(res2, nb2);
		this.recevoirRessources(res1, nb1);

	}
	public String toString()
	{
		return nom;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public int getNumJoueur() {
		return numJoueur;
	}
}
