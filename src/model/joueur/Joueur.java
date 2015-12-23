package model.joueur;

import model.jeu.Arete;
import model.jeu.Point;

import java.util.HashMap;

public class Joueur
{

	private String m_nom;
	private int numJoueur;
	private String m_avatar;
	private PackRess m_ressources;
	private HashMap<Invention, Integer> m_inventions;
	private HashMap<Point, Integer> m_pointsConstruits;
	private HashMap<Arete, Integer> m_aretesConstruites;
	private HashMap<Carte, Integer> cartes;
	
	public Joueur(String nom, int num)
	{
		m_nom = nom;
	}

	

	/*
	 * Fonction pour dépenser un certain nombre d'une ressources : dépense les ressources
	 */
	public void depenserRessources(PackRess pack)
	{
		m_ressources.remove(pack);
	}

	/*
	 *  Ajoute la ressource au joueur
	 */
	public void recevoirRessources(PackRess pack)
	{
		m_ressources.add(pack);
	}

	/*
	 * Test si le joueur a les ressources passées en paramètres
	 */
	public boolean possede(PackRess pack)
	{
		return m_ressources.contains(pack);
	}

	/*
	 * Retourne le nombre de ressources que le joueur
	 * correspondant à la ressource pass"e en paramètres
	 */
	public int nbRessource(Ressource res/*FixMe:, int nombres ??*/)
	{
		return m_ressources.count(res);
	}
	
	/*
	 * Retourne vrai si le joueur poss�de l'invention 
	 * pass�e en param�tre
	 */
	public boolean possedeInvention(Invention inv)
	{
		return (m_inventions.get(inv)==1);
	}


	//Todo: Construit un nouvel élément au joueur( carte, route, ville, ...), dépenses les ressources et ajoute l'objet au joueur
	public void construire()
	{

	}

	/*
	 * Echange des ressources avec un autre joueur
	 * le joueur courant (this) échange 'donne' ressources à autre joueur
	 * et recois 'recois' ressources en retour
	 */
	public void echangerRessources(Joueur autre, PackRess donne, PackRess recois)
	{
		this.depenserRessources(donne);
		this.recevoirRessources(recois);
		autre.depenserRessources(recois);
		autre.recevoirRessources(donne);
	}

	public String toString()
	{
		return m_nom;
	}
	
	public String getNom()
	{
		return nom;
	}
	
	public int getNumJoueur() {
		return numJoueur;
	}
}
