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


}
