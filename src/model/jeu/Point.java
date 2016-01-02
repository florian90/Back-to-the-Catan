package model.jeu;

import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Joueur;
import model.joueur.Ressource;
import vue.jeu.plateau.VuePoint;

public class Point {
	private CoordPoint m_coord;
	private TypePoint m_type;
	private Joueur m_proprietaire; //Si null, le point n'est pas encore utilisé
	private VuePoint m_vue;

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

	public void construire(Joueur joueur, TypePoint type)
	{
		m_type = type;
		m_proprietaire = joueur;
	}

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
		else if(joueur.isPremierPointSurPlateau())
		{
			CoordCase init = new CoordCase(0, 0, getEpoque());
			if(m_coord.getVertical().equals(init) || m_coord.getGauche().equals(init) || m_coord.getDroite().equals(init))
				return null;
			else
				return "Votre première colonie du plateau doit se trouver autour du point central";
		}
		else if(false)
		{// Todo: doit être rataché à une route et être à plus de 2 case d'une autre construction
			return "Erreur 76 : Chemin d'accès introuvavle";
		}
		// Sinon on peut construire
		return null;
	}
	
	public String toString()
	{
		return m_coord.toString();
	}

	public void setVue(VuePoint vue)
	{
		m_vue = vue;
	}

	public VuePoint getVue()
	{
		return m_vue;
	}

	public Epoque getEpoque()
	{
		return m_coord.getDroite().getEpoque();
	}
}
