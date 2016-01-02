package model.jeu;

import model.jeu.coordonnee.CoordCase;
import model.joueur.Ressource;

public class Case {
	private CoordCase m_coord;
	private Ressource m_ressource;
	private int m_valeur;


	public Case(CoordCase coo, Ressource res, int val)
	{
		m_coord = coo;
		m_ressource = res;
		m_valeur = val;
	}

	public Case(CoordCase coo)
	{//Permet de créer des 'fausses' cases autour du plateau, elles n'apparaitront pas dans la vue
		this(coo, Ressource.Autoroute, 0);
	}

	public CoordCase getCoo()
	{
		return m_coord;
	}

	public Ressource getRessource()
	{
		return m_ressource;
	}
	public int getValeur() {
		return m_valeur;
	}

	@Override
	public String toString()
	{
		return "Case : " + m_coord + ", contient : " + m_ressource;
	}
}
