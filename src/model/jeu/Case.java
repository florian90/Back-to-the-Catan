package model.jeu;

import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Ressource;
import vue.jeu.plateau.ViewCase;

public class Case {
	private CoordCase m_coord;
	private Ressource m_ressource;
	private int m_valeur;
	private boolean voleurPresent;
	private ViewCase m_vue;

	public Case(CoordCase coo, Ressource res, int val)
	{
		m_coord = coo;
		m_ressource = res;
		m_valeur = val;
		if (m_valeur == 7)
			voleurPresent = true;
		else
			voleurPresent = false;
	}

	public Case(CoordCase coo)
	{//Permet de créer des 'fausses' cases autour du plateau, elles n'apparaitront pas dans la vue
		this(coo, null, 0);
	}

	public CoordCase getCoo()
	{
		return m_coord;
	}

	public Ressource getRessource()
	{
		return m_ressource;
	}

	public int getValeur()
	{
		return m_valeur;
	}

	public boolean isVoleurPresent()
	{
		return voleurPresent;
	}

	public void setVoleurPresent(boolean voleurPresent)
	{
		this.voleurPresent = voleurPresent;
		m_vue.setImageVoleur(voleurPresent);
	}

	public void setVue(ViewCase vue)
	{
		m_vue = vue;
	}

	public ViewCase getVue()
	{
		return m_vue;
	}

	@Override
	public String toString()
	{
		return "Case : " + m_coord + ", contient : " + m_ressource;
	}

	/*
	 * Retourne la coordonnée du point selon une case et un index
	 * L'index représente quel point est sélectionné pour une case donnée
	 * Les points sont ordonnées de 0 à 5 pour une case.
	 * L'index 0 correspond au point situé en haut de la case, les suivants tant sélectionés par ordre horaire.
	 * Si l'index n'est pas compris entre 0 et 5, la fonction renvoie une Exception de type IndexOutOfBoundsException
	 */
	public CoordPoint getCooPoint(int index) throws IndexOutOfBoundsException
	{
		CoordCase coo = getCoo();
		switch (index)
		{
			case 0:
				return new CoordPoint(coo.northWest(), coo.northEast(), coo);
			case 1:
				return new CoordPoint(coo, coo.east(), coo.northEast());
			case 2:
				return new CoordPoint(coo, coo.east(), coo.southEast());
			case 3:
				return new CoordPoint(coo.southWest(), coo.southEast(), coo);
			case 4:
				return new CoordPoint(coo.west(), coo, coo.southWest());
			case 5:
				return new CoordPoint(coo.west(), coo, coo.northWest());
			default:
				throw new IndexOutOfBoundsException();
		}
	}
}
