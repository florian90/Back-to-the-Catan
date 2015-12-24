package model.jeu;

import model.jeu.coordonnee.CoordPoint;
import model.joueur.Joueur;

public class Point {
	private CoordPoint m_coord;
	private TypePoint m_type;
	private Joueur m_proprietaire; //Si null, le point n'est pas encore utilisé

	public Point(CoordPoint coord, TypePoint type, Joueur propietaire)
	{
		m_coord = coord;
		m_type = type;
		m_proprietaire = propietaire;
	}

	public Point(CoordPoint coord)
	{
		this(coord, TypePoint.Vide, null);
	}

	public Joueur getProprietaire()
	{
		return m_proprietaire;
	}

	public TypePoint getType()
	{
		return m_type;
	}

	public CoordPoint getCoo()
	{
		return m_coord;
	}

	//Todo: construire un nouveau TypePoint pour un certain joueur
	public void construire(Joueur joueur, TypePoint type)
	{

	}

	//Todo: Vérifie si on peut construire le type demandé pour le joueur
	public boolean peutConstruire(Joueur joueur, TypePoint type)
	{
		return false;
	}
	
	public String toString()
	{
		return m_coord.toString();
	}
}
