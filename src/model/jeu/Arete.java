package model.jeu;

import model.jeu.coordonnee.CoordArete;
import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class Arete {
	/*
	 * Correspond à un coté d'une case.
	 */
	private CoordArete m_coord;
	private TypeArete m_type;
	private Joueur m_proprietaire;
	private Plateau m_plateau;

	public Arete(CoordArete coord, TypeArete type, Joueur propietaire, Plateau plateau)
	{
		m_coord = coord;
		m_type = type;
		m_proprietaire = propietaire;
		m_plateau = plateau;
	}

	public Arete(CoordArete coord, Plateau plateau)
	{
		this(coord, TypeArete.Vide, null, plateau); // A vérifier le dernier NULL
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
		//Fait par Val
		if (peutConstruire(joueur, type))
		{
			m_type = type;
			m_proprietaire = joueur;
		}
	}
	
	//Todo: Check si on peut construire le type demandé pour le joueur
	public boolean peutConstruire(Joueur joueur, TypeArete type)
	{
		//Fait par Val		
		if ((type==TypeArete.Route && m_type==TypeArete.Vide && !peutEtreAutoroute()) || (type==TypeArete.Autoroute && m_type==TypeArete.Vide && peutEtreAutoroute()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean peutEtreAutoroute()
	{
		CoordPoint x = m_coord.getDebut();
		CoordPoint y = m_coord.getFin();
		CoordCase dx = x.getDroite();
		CoordCase gx = x.getGauche();
		CoordCase vx = x.getVertical();
		CoordCase dy = y.getDroite();
		CoordCase gy = y.getGauche();
		CoordCase vy = y.getVertical();
		
		
		
		if(dx.equals(dy))
		{
			if(m_plateau.getCases().get(dx).getRessource()==Ressource.Autoroute || m_plateau.getCases().get(gx).getRessource()==Ressource.Autoroute)
			{
				System.out.println("Vertical vrai");
				return true;
			}
			else
			{
				System.out.println("Vertical faux");
				return false;
				
			}
		}
		else if(gx.equals(vy))
		{
			if(m_plateau.getCases().get(gx).getRessource()==Ressource.Autoroute || m_plateau.getCases().get(dy).getRessource()==Ressource.Autoroute)
			{
				System.out.println("/ vrai");
				return true;
				
			}
			else
			{
				System.out.println("/ faux");
				return false;
			}
		}
		else if(vx.equals(gy))
		{
			if(m_plateau.getCases().get(vx).getRessource()==Ressource.Autoroute || m_plateau.getCases().get(dx).getRessource()==Ressource.Autoroute)
			{
				System.out.println("\\ vrai");
				return true;
			}
			else
			{
				System.out.println("\\ gy faux");
				return false;
			}
		}
		else
		{
			System.out.println("default faux");
			return false;
		}
	}
	
	public String toString()
	{
		return m_coord.toString();
	}

}
