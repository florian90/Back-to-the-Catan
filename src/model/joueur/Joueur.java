package model.joueur;

import java.util.HashMap;

import model.jeu.Arete;
import model.jeu.Point;

public class Joueur
{

	private String m_nom;
	private String m_avatar;
	private HashMap<Ressource, Integer> m_ressources;
	private HashMap<Invention, Integer> m_inventions;
	private HashMap<Point, Integer> m_pointsConstruits;
	private HashMap<Arete, Integer> m_aretesConstruites;
	private HashMap<Carte, Integer> cartes;
	
	public Joueur(String nom)
	{
		m_nom = nom;
		
		
	}

	/*
	 * Fonction pour dÃ©penser un certain nombre d'une ressources : dépense les ressources
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
	 * Retourne le nombre de ressources que le joueur
	 * correspondant à la ressource passée en paramètres 
	 */
	public int nbRessource(Ressource res, int nombres)
	{
		return m_ressources.get(res);
	}

	//Todo: Construit un nouvel Ã©lÃ©ment au joueur( carte, route, ville, ...), dÃ©penses les ressources et ajoute l'objet au joueur
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
		return m_nom;
	}
	
	public String getNom()
	{
		return m_nom;
	}
}
