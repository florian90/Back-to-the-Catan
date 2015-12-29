package model.joueur;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import model.jeu.Arete;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.jeu.Point;
import model.jeu.TypeArete;
import model.jeu.TypePoint;

public class Joueur {

	private String m_nom;
	private int m_numJoueur;
	private Color m_couleur;
	private String m_avatar;
	private PackRess m_ressources;
	private HashMap<Invention, Integer> m_inventions;
	private HashMap<Point, Integer> m_pointsConstruits;
	private HashMap<Arete, Integer> m_aretesConstruites;
	private HashMap<Carte, Integer> m_cartes;
	private Jeu m_jeu;
	
	public Joueur(String nom, int num)
	{
		m_nom = nom;
		m_numJoueur = num;
		
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
		m_pointsConstruits = new HashMap<>();
		m_aretesConstruites = new HashMap<>();
		m_cartes = new HashMap<>();

		for (Invention inv : Invention.values())
			m_inventions.put(inv, 0);
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
	 * Retourne le nombre de ressources que le joueur
	 * correspondant à la ressource pass"e en paramètres
	 */
	public int nbRessource(Ressource res/*FixMe:, int nombres ??*/)//Je crois que ca sert à rien ca doit être un résidu de copier coller 
	{
		return m_ressources.count(res);
	}
	
	/*
	 * Retourne vrai si le joueur possède l'invention 
	 * passée en paramètre
	 */
	public boolean possedeInvention(Invention inv)
	{
		return (m_inventions.get(inv) >= 1);
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
		int nbInv = m_inventions.get(inv);
		m_inventions.put(inv, nbInv+1);
	}
	
	public void acheterCarte(PackRess pack, Carte card)
	{
		depenserRessources(pack);
		int nbCard = m_cartes.get(card);
		m_cartes.put(card, nbCard+1);
	}
	
	public void construireRoute(PackRess pack, Arete road)
	{
		depenserRessources(pack);
		int nbRoad = m_aretesConstruites.get(road);
		m_aretesConstruites.put(road, nbRoad+1);
	}
	//A vérifier si point doit être une hashmap ou pas. Séparer Ville et Village ?
	public void construirePoint(PackRess pack, Point point)
	{
		depenserRessources(pack);
		int nbPoint = m_pointsConstruits.get(point);
		m_pointsConstruits.put(point, nbPoint+1);
	}

	/*
	 * Echange des ressources avec un autre joueur
	 * le joueur courant (this) échange 'donne' ressources à autre joueur
	 * et recois 'recois' ressources en retour
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
		PackRess cout = obj.cout(m_jeu.getEpoqueActuelle()); //FixMe: recupérer la vraie époque // Normalement c'est bon
		cout.mult(nbr);
		depenserRessources(cout);
		System.out.println(m_nom + " a recu " + nbr + "x" + obj);
		
		//FixMe: recevoir l'objet
	}
	
	/*
	 * Retourne le nombre de points correspondant au type passé en paramètre
	 */
	public int getNbPoints(TypePoint tp)
	{
		int nbPoints=0;
		
				for(Map.Entry<Point, Integer> entry : m_pointsConstruits.entrySet())
				{
					if (entry.getKey().equals(tp))
					{
						nbPoints++;
					}
						
				}
				System.out.println("NbPoints : "+nbPoints);
				return nbPoints;
	}
	
	/*
	 * Retourne le nombre d'arètes correspondant au type passé en paramètre
	 */
	public int getNbAretes(TypeArete ta)
	{
		int nbAretes=0;
		
				for(Map.Entry<Point, Integer> entry : m_pointsConstruits.entrySet())
				{
					if (entry.getKey().equals(ta))
					{
						nbAretes++;
					}
						
				}
				System.out.println("NbAretes : "+nbAretes);
				return nbAretes;
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
	
	public Color getM_Couleur()
	{
		return m_couleur;
	}
	
	public void setM_jeu(Jeu m_jeu) {
		this.m_jeu = m_jeu;
	}
}
