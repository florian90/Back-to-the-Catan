package model.jeu;

import model.jeu.coordonnee.CoordArrete;
import model.joueur.Joueur;

public class Arete {
    private CoordArrete m_coord;
    private TypeArete m_type;
    private Joueur m_proprietaire;

    public Arete(CoordArrete coord, TypeArete type, Joueur propietaire)
    {
        m_coord = coord;
        m_type = type;
        m_proprietaire = propietaire;
    }

    public Arete(CoordArrete coord)
    {
        this(coord, TypeArete.Vide, null);
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

    }

    //Todo: Check si on peut construire le type demandé pour le joueur
    public boolean peutConstruire(Joueur joueur, TypeArete type)
    {
        return false;
    }
}
