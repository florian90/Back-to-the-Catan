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
	private HashMap<Invention,Boolean> m_inventions;

	private ArrayList<Point> m_villagesConstruits;
	private ArrayList<Point> m_villesConstruites;
	private ArrayList<Arete> m_routesConstruites;
	private ArrayList<Arete> m_autoroutesConstruites;
	
	private int nbRoutesAConstruire;
	private int nbAutoroutesAConstruire;
	private int nbVillagesAConstruire;
	private int nbVillesAConstruire;
	
	private int nbCartesDev;
	private int nbCartesDeplacerVoleur;
	private Jeu m_jeu;
	
	public Joueur(String nom, int num)
	{
		m_nom = nom;
		m_numJoueur = num;
		m_avatar = "textures/Avatar" + num + ".jpg";
		
		switch(m_numJoueur)
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
		m_inventions = new HashMap<>();
		m_villagesConstruits    = new ArrayList<>();
		m_villesConstruites     = new ArrayList<>();
		m_autoroutesConstruites = new ArrayList<>();
		m_routesConstruites     = new ArrayList<>();

		for (Invention inv : Invention.values())
			m_inventions.put(inv, false);
		init();
	}

	public void init()
	{
		nbRoutesAConstruire = 2;
		nbAutoroutesAConstruire = 0;
		nbVillagesAConstruire = 2;
		nbVillesAConstruire = 0;
		nbCartesDeplacerVoleur =6;
		m_accesEpoque = 1;
	}

	/*
	 * Fonction pour dépenser un certain nombre d'une ressources : dépense les ressources
	 */
	public void depenserRessources(PackRess pack)
	{
		m_ressources.remove(pack);
	}

	/*
	 *  Ajoute la ressource au joueur
	 */
	public void recevoirRessources(PackRess pack)
	{
		m_ressources.add(pack);
	}

	/*
	 * Teste si le joueur a les ressources passées en paramètres
	 */
	public boolean possede(PackRess pack)
	{
		return m_ressources.contains(pack);
	}

	/*
	 * Test si le joueur possède un point de type type en stock
	 */
	public boolean possede(TypePoint type)
	{
		if(type == TypePoint.Village)
			return nbVillagesAConstruire >= 1;
		else if(type == TypePoint.Ville)
			return nbVillesAConstruire >= 1;
		return false;
	}

	/*
	 * Test si le joueur possède un point de type type en stock
	 */
	public boolean possede(TypeArete type)
	{
		if(type == TypeArete.Autoroute)
			return nbAutoroutesAConstruire >= 1;
		else if(type == TypeArete.Route)
			return nbRoutesAConstruire >= 1;
		return false;
	}

	/*
	 * Retourne le nombre de ressources que le joueur
	 * correspondant à la ressource pass"e en paramètres
	 */
	public int nbRessource(Ressource res)
	{
		return m_ressources.count(res);
	}
	
	/*
	 * Retourne vrai si le joueur possède l'invention 
	 * passée en paramètre
	 */
	public boolean possedeInvention(Invention inv)
	{
		return (m_inventions.get(inv));
	}

	/*
	 * Vérifie si le joueur peut construire nbr objet(s) de type passé en paramètre
	 */
	public boolean peutConstruire(Achetable obj, int nbr, Epoque epoque)
	{
		PackRess cout = obj.cout(epoque);
		cout.mult(nbr);
		return possede(cout);
	}

	/*
	 * Vérifie si le joueur peut construire un objet de type passé en paramètre
	 */
	public boolean peutConstruire(Achetable obj, Epoque epoque)
	{
		return peutConstruire(obj, 1, epoque);
	}

	//A vérifier (Val): Construit un nouvel élément au joueur( carte, route, ville, ...), dépenses les ressources et ajoute l'objet au joueur
	public void construireInvention(PackRess pack, Invention inv)
	{
		depenserRessources(pack);
		//int nbInv = m_inventions.get(inv);
		m_inventions.put(inv, true);
	}
	
	public void acheterCarte(TypeCarte tc)
	{
		depenserRessources(tc.cout(m_jeu.getEpoqueActuelle()));
		if (tc == TypeCarte.DeplacerVoleur)
		{
			nbCartesDeplacerVoleur++;
			m_jeu.getFenetre().setStatus("Vous venez de piocher une carte voleur. Vous gagnez 1 déplacement de voleur à utiliser quand vous voulez.");
		}
		else
		{
			nbRoutesAConstruire += 2;
			m_jeu.getFenetre().setStatus("Vous venez de piocher une carte developpement. Vous gagnez 2 routes constructibles.");
		}
	}
	
	public int getNbCartesDev() {
		return nbCartesDev;
	}


	public int getNbCartesDeplacerVoleur() {
		return nbCartesDeplacerVoleur;
	}

	public void construirePoint(TypePoint type, Point point)
	{
		if(type == TypePoint.Village)
		{
			m_villagesConstruits.add(point);
			nbVillagesAConstruire--;
		}
		else
		{
			m_villesConstruites.add(point);
			nbVillesAConstruire--;
		}
	}

	public void construireArete(TypeArete type, Arete arete)
	{
		if(type == TypeArete.Autoroute)
		{
			m_autoroutesConstruites.add(arete);
			nbAutoroutesAConstruire--;
		}
		else // TypeArete.Route
		{
			m_routesConstruites.add(arete);
			nbRoutesAConstruire--;
		}
	}

	/*
	 * Echange des ressources avec un autre joueur
	 * le joueur courant (this) échange 'donne' ressources à autre joueur
	 * et recoit 'recois' ressources en retour
	 */
	public void echangerRessources(Joueur autre, PackRess donne, PackRess recois)
	{
		this.depenserRessources(donne);
		this.recevoirRessources(recois);
		autre.depenserRessources(recois);
		autre.recevoirRessources(donne);
	}

	public void acheter(Achetable obj, int nbr)
	{
		PackRess cout = obj.cout(m_jeu.getEpoqueActuelle()); 
		cout.mult(nbr);
		depenserRessources(cout);
		System.out.println(m_nom + " a recu " + nbr + "x" + obj);
		
		if(obj == TypeArete.Route)
		{
			nbRoutesAConstruire+=nbr;
		}
		else if(obj == TypeArete.Autoroute)
		{
			nbAutoroutesAConstruire+=nbr;
		}
		else if(obj == TypePoint.Village)
		{
			nbVillagesAConstruire+=nbr;
		}
		else if(obj == TypePoint.Village)
		{
			nbVillesAConstruire+=nbr;
		}
	}
	
	

	/*
	 * Retourne le nombre de points correspondant au type passé en paramètre
	 */
	public int getNbPoints(TypePoint tp)
	{
		switch(tp)
		{
			case Village:
				return m_villagesConstruits.size();
			case Ville:
				return m_villesConstruites.size();
			default:
				return 0;
		}
	}
	
	/*
	 * Retourne le nombre d'arètes correspondant au type passé en paramètre
	 */
	public int getNbAretes(TypeArete ta)
	{
		switch(ta)
		{
			case Route:
				return m_routesConstruites.size();
			case Autoroute:
				return m_autoroutesConstruites.size();
			default:
				return 0;
		}
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
	
	public void setM_jeu(Jeu m_jeu) {
		this.m_jeu = m_jeu;
	}


	public int getNbRoutesAConstruire() {
		return nbRoutesAConstruire;
	}


	public int getNbAutoroutesAConstruire() {
		return nbAutoroutesAConstruire;
	}


	public int getNbVillagesAConstruire() {
		return nbVillagesAConstruire;
	}


	public int getNbVillesAConstruire() {
		return nbVillesAConstruire;
	}

	public String getAvatar(){
		return m_avatar;
	}
	
	public HashMap<Invention, Boolean> getM_inventions() {
		return m_inventions;
	}

	public void recevoirRessource(Ressource res)
	{
		m_ressources.add(res);
	}

	public boolean isPremierPointSurPlateau()
	{
		for(Point point: m_villagesConstruits)
			if(point.getEpoque() == m_jeu.getEpoqueActuelle())
				return false;
		return true;
	}

	public boolean peutDeplacerVoleur() {
		
		return(nbCartesDeplacerVoleur > 0);
	}

	public void utiliserCarteVoleur() {
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
				return m_accesEpoque>=1;
			case _2015:
				return m_accesEpoque>=2;
			case _1855:
				return m_accesEpoque>=3;
			case  _1955:
				return m_accesEpoque>=4;
		}
		return false;
	}
}
