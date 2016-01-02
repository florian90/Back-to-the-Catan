package model.jeu;

import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.PackRess;
import model.joueur.TypeCarte;
import vue.jeu.Fenetre;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Jeu {
	/*
	 * Gère tous les joueurs ainsi que tous les plateaux.
	 */
	private HashMap<Epoque, Plateau> plateaux;
	private ArrayList<Joueur> joueurs;
	private int nbJoueurs, joueurActuel;
	private Epoque epoqueActuelle;

	private boolean m_constructionActive;
	private boolean m_desLences;

	private Fenetre m_vue;
	
	/*
	 * Construit le modele de jeu à partir de la liste des joueurs 
	 * fournie par le menu principal, intialise les plateux de jeu
	 */
	public Jeu(ArrayList<Joueur> p_joueurs)
	{
		epoqueActuelle = Epoque._1985;
		joueurs = p_joueurs;
		for (Joueur j : joueurs)
		{
			j.setM_jeu(this);
		}
		PackRess packInitial = new PackRess();
		packInitial.add(Invention.ConvecteurTemporel.cout(null));
		packInitial.add(Invention.HoverBoard.cout(null));
		packInitial.add(Invention.Radio.cout(null));
		packInitial.add(Invention.Train.cout(null));
		
		joueurs.get(0).recevoirRessources(packInitial);
		joueurActuel = 0; //index du joueur dont le tour est en cours
		nbJoueurs = joueurs.size();
		plateaux = new HashMap<Epoque, Plateau>();
		for (Epoque epoque : Epoque.values())
		{
			plateaux.put(epoque, new Plateau(epoque, 7));
		}
	}

	//Todo: Initialise toutes les instances du jeu
	public void initJeu()
	{
		// Initialise ce que les joueurs possèdent  au début
		joueurActuel = 0;
		m_desLences = false;
		epoqueModifiee();
		m_vue.initTourJoueur();
	}

	/**
	 * Setters classiques
	 **/
	public void setVue(Fenetre fen)
	{
		m_vue = fen;
	}

	/**
	 * getters classiques
	 **/
	public int getNbJoueurs()
	{
		return nbJoueurs;
	}

	public Plateau getPlateau(Epoque epoque)
	{
		return plateaux.get(epoque);
	}

	/*
	 * Retourne un tableau d'entier contenant le 
	 * résultat du dé n°1, le résultat du dé n°2
	 * et la somme des deux.
	 */
	public void lancerDes()
	{
		Random rnd = new Random();
		int de1 = rnd.nextInt(6) + 1;
		int de2 = rnd.nextInt(6) + 1;
		
		int tab[] = {de1, de2};
		m_desLences = true;
		m_vue.lanceDes(tab);
		recolterRessources(de1 + de2);
		m_vue.updateJoueur();
	}
	
	public TypeCarte tirerCarte()
	{
		Random rnd = new Random();
		int res = rnd.nextInt(2); //TODO: à redéfinir pour modifier la fréquence d'apparition des cartes
		
		if (res == 1)
		{
			return TypeCarte.Developpement;
		} else
		{
			return TypeCarte.DeplacerVoleur;
		}

	}

	public void joueurSuivant()
	{
		m_desLences = false;
		if (++joueurActuel >= nbJoueurs)
			joueurActuel = 0;
		m_vue.initTourJoueur();
	}

	public Joueur getJoueur()
	{
		return joueurs.get(joueurActuel);
	}

	public Joueur getJoueur(int i)
	{
		return joueurs.get(i);
	}

	public Epoque getEpoqueActuelle()
	{
		return epoqueActuelle;
	}

	public void epoqueSuivante()
	{
		switch (epoqueActuelle)
		{
			case _1855:
				epoqueActuelle = Epoque._1955;
				break;
			case _1955:
				epoqueActuelle = Epoque._1985;
				break;
			case _1985:
				epoqueActuelle = Epoque._2015;
				break;
			case _2015:
				epoqueActuelle = Epoque._1855;
				break;
		}
		epoqueModifiee();
	}

	public void epoquePrecedente()
	{
		switch (epoqueActuelle)
		{
			case _1855:
				epoqueActuelle = Epoque._2015;
				break;
			case _1955:
				epoqueActuelle = Epoque._1855;
				break;
			case _1985:
				epoqueActuelle = Epoque._1955;
				break;
			case _2015:
				epoqueActuelle = Epoque._1985;
				break;
		}
		epoqueModifiee();
	}

	public void epoqueModifiee()
	{
		m_vue.chargerPlateau(epoqueActuelle);
	}

	public void clicPoint(Point point)
	{
		if (m_constructionActive)
		{
			TypePoint type = point.getType();
			if (type == TypePoint.Vide)
				type = TypePoint.Village;
			else if (type == TypePoint.Village)
				type = TypePoint.Ville;
			construirePoint(type, point);
		}
	}

	public void clicArete(Arete arete)
	{
		if (m_constructionActive)
		{
			TypeArete type = arete.getType();
			if (type == TypeArete.Vide)
			{
				if (arete.peutEtreAutoroute())
					type = TypeArete.Autoroute;
				else
					type = TypeArete.Route;
			}
			construireArete(type, arete);
		}
	}

	public void changeConstructionActive()
	{
		m_constructionActive = !m_constructionActive;
		if (m_constructionActive)
			m_vue.setMessageClassique("Sélectionner la case sur laquelle vous souhaitez construire");
		else
			m_vue.setMessageClassique(getJoueur().getNom() + " échangez, achetez, construisez puis terminez votre tour pour passer au joueur suivant");
	}

	public boolean isConstructionActive()
	{
		return m_constructionActive;
	}

	public Fenetre getFenetre()
	{
		return m_vue;
	}

	public boolean isDeLance()
	{
		return m_desLences;
	}

	public boolean peutConstruirePoint(TypePoint type, Point point)
	{
		if (!getJoueur().possede(type))
		{
			m_vue.setStatus("Vous n'avez pas de " + type + " disponible, achetez-en au marché !");
			return false;
		}
		String res = point.peutConstruire(getJoueur(), type);
		if (res != null)
		{
			m_vue.setStatus(res);
			return false;
		}
		return true;
	}

	public void construirePoint(TypePoint type, Point point)
	{
		if (peutConstruirePoint(type, point))
		{
			getJoueur().construirePoint(type, point);
			point.construire(getJoueur(), type);
			m_vue.updateJoueur();
			point.getVue().update();
		}
	}

	public boolean peutConstruireArete(TypeArete type, Arete arete)
	{
		if (!getJoueur().possede(type))
		{
			m_vue.setStatus("Vous n'avez pas de " + type + " disponible, achetez-en au marché !");
			return false;
		}
		String res = arete.peutConstruire(getJoueur(), type);
		if (res != null)
		{
			m_vue.setStatus(res);
			return false;
		}
		return true;
	}

	public void construireArete(TypeArete type, Arete arete)
	{
		if (peutConstruireArete(type, arete))
		{
			getJoueur().construireArete(type, arete);
			arete.construire(getJoueur(), type);
			m_vue.updateJoueur();
			arete.getVue().update();
		}
	}
	
	//Remark: méthode déplacée depuis la classe ContentTabConstruction, il faudrait la renommer non ?
	public boolean test(int nbRoute, int nbAutoroute, int nbVillage, int nbVille, Epoque epoque, Joueur joueur)
	{
		PackRess cout = new PackRess();
		cout.add(TypeArete.Route.cout(epoque), nbRoute);
		cout.add(TypeArete.Autoroute.cout(epoque), nbAutoroute);
		cout.add(TypePoint.Village.cout(epoque), nbVillage);
		cout.add(TypePoint.Ville.cout(epoque), nbVille);

		return joueur.possede(cout);
	}

	public void recolterRessources(int val)
	{
		for (Plateau plateau : plateaux.values())
			plateau.recolterRessources(val);
	}
}
