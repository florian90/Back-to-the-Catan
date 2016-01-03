package model.jeu;

import model.jeu.coordonnee.CoordArete;
import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Joueur;
import model.joueur.Ressource;
import vue.jeu.plateau.VueArete;

import java.util.ArrayList;

public class Arete {
	/*
	 * Correspond à un coté d'une case.
	 */
	private CoordArete m_coord;
	private TypeArete m_type;
	private Joueur m_proprietaire;
	private Plateau m_plateau;

	private VueArete m_vue;

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

	public void setVue(VueArete vue)
	{
		m_vue = vue;
	}

	public VueArete getVue()
	{

		return m_vue;
	}

	public void construire(Joueur joueur, TypeArete type)
	{
		m_type = type;
		m_proprietaire = joueur;
	}

	public String peutConstruire(Joueur joueur, TypeArete type)
	{
		if (m_proprietaire != null && m_proprietaire != joueur)
		{
			return "Cette arète appartient déjà à un autre joueur";
		} else if (m_type != TypeArete.Vide)
		{//
			return "Cette arète est déjà construite";
		} else if (!isAttache(joueur))
		{// Todo: doit être rataché à une autre route ++ OU au centre du plateau (Au début du jeu)
			return "Cette arète doit être ratachéz à une autre vous appartenant";
		}
		return null;
	}
	/*
	 * Renvoie true si la constructions sur l'arete doit 
	 * être une autoroute
	 */
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
		
		
		if (dx.equals(dy) || gx.equals(gy))
		{
			if (m_plateau.getCasesVirtuelles(dx).getRessource() == Ressource.Autoroute || m_plateau.getCasesVirtuelles(gx).getRessource() == Ressource.Autoroute)
			{
				return true;
			} else
			{
				return false;
			}
		} else if (gx.equals(vy) || dy.equals(vx))
		{
			if (m_plateau.getCasesVirtuelles(dy).getRessource() == Ressource.Autoroute || m_plateau.getCasesVirtuelles(gx).getRessource() == Ressource.Autoroute)
			{
				return true;
				
			} else
			{
				return false;
			}
		} else if (vx.equals(gy) || vy.equals(dx))
		{
			if (m_plateau.getCasesVirtuelles(vx).getRessource() == Ressource.Autoroute || m_plateau.getCasesVirtuelles(dx).getRessource() == Ressource.Autoroute)
			{
				return true;
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return m_coord.toString();
	}

	public boolean isAttache(Joueur joueur)
	{
		// Test si un point adjacent est occupé
		ArrayList<Point> ptsAdjacents = m_plateau.getAdjacentPoints(this);
		for(Point pt : ptsAdjacents)
			if(pt.getProprietaire() == joueur)
			{
				return true;
			}
		// Test si une arete adjacent est occupé
		ArrayList<Arete> areteAdjacents = m_plateau.getAdjacentArete(this);
		for(Arete a : areteAdjacents)
			if(a.getProprietaire() == joueur)
			{
				System.out.println("res : true");
				return true;
			}
		return false;
	}

}
