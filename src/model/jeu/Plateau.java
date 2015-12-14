package model.jeu;

import model.jeu.coordonnee.CoordArete;
import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import model.joueur.Ressource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Plateau {
    private Epoque epoque;
    private HashMap<CoordCase, Case> cases;
    private HashMap<CoordArete, Arete> aretes;
    private HashMap<CoordPoint, Point> points;
    private int m_size;

    public Plateau(Epoque ep, int size) {
        epoque = ep;
        m_size = size;
        init();
    }

    private void init()
    {
        initCases();
        initPoints();
        initAretes();
    }

    private void initCases()
    {
        int[][] list = getRessourceTab(); // Initialise le tableau des ressources
        cases = new HashMap<>();
        CoordCase coord;
        Case tuile;
        Ressource res;
        for (int j = 1; j <= m_size; j++) {
            for (int i = 1; i <= m_size; i++) {
                coord = new CoordCase(j - 4, i - 4);
                if ((res = getRessource(coord, list)) != null) {
                    tuile = new Case(coord, res, 6);
                    cases.put(coord, tuile);
                }
            }
        }
    }

    private void initPoints()
    {
        points = new HashMap<>();
        CoordCase coo;
        CoordPoint cooPoint;
        for (Case tuile: cases.values())
        {
            coo = tuile.getCoo();
            cooPoint = new CoordPoint(coo.west(), coo, coo.northWest());
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
            cooPoint = new CoordPoint(coo.northWest(), coo.northEast(), coo);
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
            cooPoint = new CoordPoint(coo, coo.east(), coo.northEast());
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
            cooPoint = new CoordPoint(coo, coo.east(), coo.southEast());
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
            cooPoint = new CoordPoint(coo.southWest(), coo.southEast(), coo);
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
            cooPoint = new CoordPoint(coo.west(), coo, coo.southWest());
            if(!points.containsKey(cooPoint))
                points.put(cooPoint, new Point(cooPoint));
        }
    }

    private void initAretes()
    {
        aretes = new HashMap<>();
        CoordArete cooArete;
        for(Case tuile: cases.values())
        {
            for(int i=0;i<6;i++)
            {
                cooArete = new CoordArete(getCooPoint(tuile, i), getCooPoint(tuile, (i+1)%6));
                if(!aretes.containsKey(cooArete))
                {
                    aretes.put(cooArete, new Arete(cooArete));
                }
            }
        }
    }

    private CoordPoint getCooPoint(Case tuile, int index) throws IndexOutOfBoundsException
    {
        CoordCase coo = tuile.getCoo();
        switch (index)
        {
            case 0:
                return new CoordPoint(coo.northWest(), coo.northEast(), coo);
            case 1:
                return new CoordPoint(coo, coo.east(), coo.northEast());
            case 2:
                return new CoordPoint(coo, coo.east(), coo.southEast());
            case 3:
                return new CoordPoint(coo.southWest(), coo.southEast(), coo);
            case 4:
                return new CoordPoint(coo.west(), coo, coo.southWest());
            case 5:
                return new CoordPoint(coo.west(), coo, coo.northWest());
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public List<Case> getCases() {
        return new ArrayList<>(cases.values());
    }

    public List<Arete> getAretes() {
        return new ArrayList<>(aretes.values());
    }

    public List<Point> getPoints()
    {
        return new ArrayList<>(points.values());
    }

    private Ressource getRessource(CoordCase coordCase, int[][] list)
    {
        Ressource r1, r2;
        if (epoque == Epoque._1855) {
            r1 = Ressource.Bois;
            r2 = Ressource.Roue;
        } else if (epoque == Epoque._1955) {
            r1 = Ressource.HautParleur;
            r2 = Ressource.Antenne;
        } else if (epoque == Epoque._1985) {
            r1 = Ressource.Plutonium;
            r2 = Ressource.MorceauSchema;
        } else {
            r1 = Ressource.Aimant;
            r2 = Ressource.Ventilateur;
        }
        try {
            switch (list[coordCase.getLine()+m_size/2][coordCase.getColumn()+m_size/2]) {
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
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private int[][] getRessourceTab()
    {
        //Todo: Le fichier doit contenir ne matrice 7x7 avec un nombre puis un espace, si on modifie la taille rien ne vas plus !
        // Crée un tableau avec les ressources présentes dans chaque case
        // Le tableau tab est initialisé à partir d'un fichier
        int[][] list = new int[m_size][m_size];
        String [] tab = null;
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader("ressources/PlateauxInitiaux/plateau1.txt"));
            for (int i = 0; i < m_size; i++)
            {
                tab = reader.readLine().split(" ");
                for (int j = 0; j < m_size; j++)
                {
                    list[i][j] = Integer.parseInt(tab[j]);
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

}
