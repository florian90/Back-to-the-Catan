package model.jeu;

import model.jeu.coordonnee.CoordCase;
import model.joueur.Ressource;
import test.plateau.application.ViewCase;

public class Case {
    private CoordCase m_coord;
    private Ressource m_ressource;
    private int m_valeur;

    public Case(CoordCase coo, Ressource res, int val)
    {
        m_coord = coo;
        m_ressource = res;
        m_valeur = val;
    }

    public CoordCase getCoo()
    {
        return m_coord;
    }

    public Ressource getRessource()
    {
        return m_ressource;
    }
}
