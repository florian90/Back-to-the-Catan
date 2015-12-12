package model.jeu;

import model.jeu.coordonnee.CoordArrete;
import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Ressource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Plateau {
    private Epoque epoque;
    private HashMap<CoordCase, Case> cases;
    private HashMap<CoordArrete, Arete> aretes;
    private HashMap<CoordPoint, Point> points;
    private int m_size;

    public Plateau(Epoque ep, int size)
    {
        epoque = ep;
        m_size = size;
        initCases();
    }

    /*
     * size (int) : largeur de la grille au centre
     */
    private void initCases()
    {
        cases = new HashMap<>();
        CoordCase coord;
        Case tuile;
        Ressource res;
        for(int j=1;j<=m_size;j++)
        {
            for(int i=1;i<=m_size;i++)
            {
                if((res = getRessource(i-1, j-1)) != null)
                {
                    coord = new CoordCase(j-4, i-4);
                    tuile = new Case(coord, res, 6);
                    cases.put(coord, tuile);
                }
            }
        }
    }

    public List<Case> getCases()
    {
        return new ArrayList<>(cases.values());
    }
    
    private Ressource getRessource(int i, int j)
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);list.add(0);list.add(0);list.add(1);list.add(1);list.add(1);list.add(1);
        list.add(0);list.add(0);list.add(1);list.add(2);list.add(3);list.add(2);list.add(1);
        list.add(0);list.add(1);list.add(3);list.add(4);list.add(4);list.add(4);list.add(1);
        list.add(1);list.add(2);list.add(3);list.add(2);list.add(3);list.add(2);list.add(1);
        list.add(1);list.add(4);list.add(3);list.add(4);list.add(3);list.add(1);list.add(0);
        list.add(1);list.add(2);list.add(4);list.add(2);list.add(1);list.add(0);list.add(0);
        list.add(1);list.add(1);list.add(1);list.add(1);list.add(0);list.add(0);list.add(0);

        Ressource r1, r2;
        if(epoque == Epoque._1855) {
            r1 = Ressource.Bois;
            r2 = Ressource.Roue;
        }else if(epoque == Epoque._1955) {
            r1 = Ressource.HautParleur;
            r2 = Ressource.Antenne;
        }else if(epoque == Epoque._1985) {
            r1 = Ressource.Plutonium;
            r2 = Ressource.MorceauSchema;
        }else {
            r1 = Ressource.Aimant;
            r2 = Ressource.Ventilateur;
        }

        switch(list.get(i+7*j))
        {
            case 0:
                return null;
            case 1:
                return Ressource.Autoroute;
            case 2:
                return Ressource.Metal;
            case 3:
                return r1;
        }
        return r2;
    }
}
