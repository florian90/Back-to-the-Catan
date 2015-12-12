package model.joueur;

import model.jeu.Point;
import test.plateau.application.Route;

import java.util.HashMap;
import java.util.List;

public class Joueur {

    private String m_nom;
    private HashMap<Ressource, Integer> m_ressources;
    private HashMap<Invention, Integer> m_inventions;
    private HashMap<Point, Integer> m_pointsConstruits;
    private HashMap<Carte, Integer> cartes;

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
