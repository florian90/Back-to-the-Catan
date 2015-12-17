package model.joueur;

import model.jeu.Point;

import java.util.HashMap;

public class Joueur
{

	private String m_nom;
	private String m_avatar;
	private HashMap<Ressource, Integer> m_ressources;
	private HashMap<Invention, Integer> m_inventions;
	private HashMap<Point, Integer> m_pointsConstruits;
	private HashMap<Carte, Integer> cartes;
	
	public Joueur(String nom, String avatar)
	{
		m_nom = nom;
		
		try
		{
			m_avatar =avatar;
		}
		catch(Exception e)//Si l'url de l'image est incorrecte on en donne une par d�faut
		{
			m_avatar = "textures/hexagon.jpg";
		}
	}

	//Todo: Fonction pour dépenser un certain nombre d'une ressources : dépense les ressources
	public void depenserRessources(Ressource res, int nombres)
	{

	}

	//Todo: Ajoute la ressource au joueur
	public void recevoirRessources(Ressource res)
	{

	}

	//Todo: Test si le jouer a les ressources passées en paramètres
	public boolean possede(Ressource res, int nombres)
	{
		return false;
	}

	//Todo: Construit un nouvel élément au joueur( carte, route, ville, ...), dépenses les ressources et ajoute l'objet au joueur
	public void construireTruc()
	{

	}

	//Todo: Echange des ressources avec un autre joueur
	public void echangerRessources(Joueur autre /*Ressource...*/)
	{

	}
}
