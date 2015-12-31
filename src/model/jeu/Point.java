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
		m_type = type;
		m_proprietaire = joueur;
	}

	//Todo: Vérifie si on peut construire le type demandé pour le joueur
	public String peutConstruire(Joueur joueur, TypePoint type)
	{
		if(m_proprietaire != null && m_proprietaire != joueur)
		{// Chez un autre joueur
			return "Ce point appartient déjà à un autre joueur";
		}
		else if(m_type == TypePoint.Vide && type==TypePoint.Ville)
		{// ville sur rien
			return "Une Ville ne peut être construite que sur un Village déjà existant";
		}
		else if(m_type == TypePoint.Ville && type == TypePoint.Village)
		{// village sur ville
			return "Vous ne pouvez pas construire de Village sur une Ville";
		}
		else if(type == m_type)
		{// t sur t
			return "Ce point est déjà de type " + type;
		}
		else if(false)
		{// Todo: doit être rataché à une rote et être à plusde 2 case d'une autre construction
			return "Erreur 76 : Chemin d'accès introuvavle";
		}
		// Sinon on peut construire
		return null;
	}
	
	public String toString()
	{
		return m_coord.toString();
	}
}
