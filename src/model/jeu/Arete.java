package model.jeu;

import model.jeu.coordonnee.CoordArete;
import model.joueur.Joueur;

public class Arete {
	/*
	 * Correspond à un coté d'une case.
	 */
	private CoordArete m_coord;
	private TypeArete m_type;
	private Joueur m_proprietaire;

	public Arete(CoordArete coord, TypeArete type, Joueur propietaire)
	{
		m_coord = coord;
		m_type = type;
		m_proprietaire = propietaire;
	}

	public Arete(CoordArete coord)
	{
		this(coord, TypeArete.Vide, null);
	}

	public CoordArete getCoord()
	{
		return m_coord;
	}

	public Joueur getProprietaire()
	{
		return m_proprietaire;
	}

	public TypeArete getType()
	{
		return m_type;
	}

	//Todo: construire un nouveau TypeRoute pour un certain joueur
	public void construire(Joueur joueur, TypeArete type)
	{
		//Fait par Val à vérifier
		/*if (peutConstruire(joueur, type))
		{
				m_type = type;
				m_proprietaire = joueur;
		}*/
	}

	//Todo: Check si on peut construire le type demandé pour le joueur
	public boolean peutConstruire(Joueur joueur, TypeArete type)
	{
		//Fait par Val non fini
		/*if ((type==Route ET m_type==Vide ET  m_coord != Autorouteconstructible) OU (type=Autoroute ET m_type=Vide ET m_coord = Autorouteconstructible))
		{
			return true;
		}
		else
		{
			return false;
		}*/
		return false;
	}
	
	public String toString()
	{
		return m_coord.toString();
	}

}
