package model.jeu.coordonnee;

public class CoordArete {
    private CoordPoint debut, fin;

    public CoordArete(CoordPoint d, CoordPoint f)
    {
        debut = d;
        fin = f;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CoordArete that = (CoordArete) o;
        return (debut.equals(that.debut) && fin.equals(that.fin)) || (fin.equals(that.debut) && debut.equals(that.fin));
    }

    @Override
    public int hashCode() {
        return debut.hashCode() * fin.hashCode();
    }

    public CoordPoint getDebut() {
        return debut;
    }

    public CoordPoint getFin() {
        return fin;
    }
}
