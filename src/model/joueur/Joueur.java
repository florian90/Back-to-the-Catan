package model.joueur;

import javafx.scene.paint.Color;
import model.jeu.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Joueur {

	private String m_nom;
	private int m_numJoueur;
	private Color m_couleur;
	private String m_avatar;
	private PackRess m_ressources;
	private int m_accesEpoque;

	private HashMap<Achetable, Integer> m_inventaire;

	private ArrayList<Point> m_villagesConstruits;
	private ArrayList<Point> m_villesConstruites;
	private ArrayList<Arete> m_routesConstruites;
	private ArrayList<Arete> m_autoroutesConstruites;

	private int nbCartesDeplacerVoleur;
	private Jeu m_jeu;

	public Joueur(String nom, int num)
	{
		m_nom = nom;
		m_numJoueur = num;
		m_avatar = "textures/Avatar" + num + ".jpg";

		switch (m_numJoueur)
		{
			case 1:
				m_couleur = Color.RED;
				break;
			case 2:
				m_couleur = Color.BLUE;
				break;
			case 3:
				m_couleur = Color.YELLOW;
				break;
			case 4:
				m_couleur = Color.GREEN;
				break;
		}
		m_ressources = new PackRess();
		m_inventaire = new HashMap<>();
		m_villagesConstruits = new ArrayList<>();
		m_villesConstruites = new ArrayList<>();
		m_autoroutesConstruites = new ArrayList<>();
		m_routesConstruites = new ArrayList<>();

		init();
	}

	public void init()
	{
		m_inventaire.put(TypeArete.Route, 2);
		m_inventaire.put(TypePoint.Village, 2);
		m_accesEpoque = 1;
		
		/* Pour avoir toutes les ressources nécessaires pour tester toutes les fonctionnalités*/
		m_ressources.add(new PackRess(Invention.Train.cout(null)));
		m_ressources.add(new PackRess(Invention.ConvecteurTemporel.cout(null)));
		m_ressources.add(new PackRess(Invention.HoverBoard.cout(null)));
		m_ressources.add(new PackRess(Invention.Radio.cout(null)));
		
		m_ressources.add(new PackRess(Ressource.Metal, 50, Ressource.MorceauSchema, 50, Ressource.Plutonium, 50));
	}

	/*
	 * Enleve les objets / ressources de l'inventaire du joueur
	 */
	public void depenser(PackRess pack)
	{
		m_ressources.remove(pack);
	}
	private void depenser(Achetable obj)
	{
		m_inventaire.put(obj, m_inventaire.get(obj) - 1);
	}

	/*
	 *  Ajoute les ressources / objets au joueur
	 */
	public void recevoir(Ressource res)
	{
		m_ressources.add(res);
	}

	public void recevoir(PackRess pack)
	{
		m_ressources.add(pack);
	}

	public void recevoir(Achetable obj)
	{
		m_inventaire.put(obj, (m_inventaire.get(obj) == null ? 0 : m_inventaire.get(obj)) + 1);
	}

	/*
	 * Teste si le joueur possède les objets / ressources passées en paramètres
	 */
	public boolean possede(PackRess pack)
	{
		return m_ressources.contains(pack);
	}
	public boolean possede(Achetable obj)
	{
		return m_inventaire.get(obj) != null && m_inventaire.get(obj) > 0;
	}

	/*
	 * Retourne le nombre de res que le joueur possède
	 */
	public int nbRessource(Ressource res)
	{
		return m_ressources.count(res);
	}

	/*
	 * Test si le joueur possède
	 * assez de ressources pour acheter obj
	 */
	public boolean peutAcheter(Achetable obj)
	{
		return possede(obj.cout(m_jeu.getEpoqueActuelle()));
	}

	/*
	 * Si le joueur possède toutes les inventions :
	 * Lance l'écran de victoire
	 */
	private void testVictoire()
	{
		boolean possedeTout = true;
		for (Invention i : Invention.values())
		{
			if (!possede(i))
			{
				possedeTout = false;
			}
		}

		if (possedeTout)
		{
			m_jeu.getFenetre().afficheVainqueur();
		}
	}

	public void acheter(Achetable obj)
	{
		if (!peutAcheter(obj))
			return;
		depenser(obj.cout(m_jeu.getEpoqueActuelle()));
		m_inventaire.put(obj, (m_inventaire.get(obj) == null) ? 1 : m_inventaire.get(obj) + 1);
		System.out.println("possede : " + m_inventaire.get(obj) + " " + obj);
		if (obj instanceof Invention)
			testVictoire();
		else if(obj instanceof TypeCarte)
		{
			if (obj == TypeCarte.DeplacerVoleur)
			{
				nbCartesDeplacerVoleur++;
				m_jeu.getFenetre().setStatus("Vous venez de piocher une carte voleur. Vous gagnez 1 déplacement de voleur à utiliser quand vous voulez.");
			}
			else
			{
				recevoir(TypeArete.Route);
				recevoir(TypeArete.Route);
				m_jeu.getFenetre().setStatus("Vous venez de piocher une carte developpement. Vous gagnez 2 routes constructibles.");
			}
		}
		m_jeu.getFenetre().updateJoueur();
	}

	public int getNombre(Achetable obj)
	{
		return m_inventaire.get(obj) == null ? 0 : m_inventaire.get(obj);
	}

	public void construirePoint(TypePoint type, Point point)
	{
		depenser(type);
		if (type == TypePoint.Village)
			m_villagesConstruits.add(point);
		else
			m_villesConstruites.add(point);
	}

	public void construireArete(TypeArete type, Arete arete)
	{
		depenser(type);
		if (type == TypeArete.Autoroute)
		{
			boolean accesEpoque = true;
			for (Arete a : m_autoroutesConstruites)
				if (m_jeu.getEpoqueActuelle() == a.getEpoque())
					accesEpoque = false;
			if (accesEpoque)
				accesNouvelleEpoque();
			m_autoroutesConstruites.add(arete);
		}
		else
			m_routesConstruites.add(arete);
	}

	/*
	 * Echange des ressources avec un autre joueur
	 * le joueur courant (this) échange 'donne' ressources à autre joueur
	 * et recoit 'recois' ressources en retour
	 */
	public void echangerRessources(Joueur autre, PackRess donne, PackRess recois)
	{
		this.depenser(donne);
		this.recevoir(recois);
		autre.depenser(recois);
		autre.recevoir(donne);
	}

	public String toString()
	{
		return m_nom;
	}

	public String getNom()
	{
		return m_nom;
	}

	public int getNumJoueur()
	{
		return m_numJoueur;
	}

	public Color getCouleur()
	{
		return m_couleur;
	}

	public void setjeu(Jeu m_jeu)
	{
		this.m_jeu = m_jeu;
	}

	public String getAvatar()
	{
		return m_avatar;
	}

	public boolean isPremierPointSurPlateau()
	{
		for (Point point : m_villagesConstruits)
			if (point.getEpoque() == m_jeu.getEpoqueActuelle())
				return false;
		return true;
	}

	public boolean peutDeplacerVoleur()
	{

		return (nbCartesDeplacerVoleur > 0);
	}

	public void utiliserCarteVoleur()
	{
		nbCartesDeplacerVoleur--;
	}

	public void accesNouvelleEpoque()
	{
		m_accesEpoque++;
	}

	public boolean aAcces(Epoque epoque)
	{
		switch (epoque)
		{
			case _1985:
				return m_accesEpoque >= 1;
			case _2015:
				return m_accesEpoque >= 2;
			case _1855:
				return m_accesEpoque >= 3;
			case _1955:
				return m_accesEpoque >= 4;
		}
		return false;
	}
}
