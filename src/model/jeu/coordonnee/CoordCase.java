package model.jeu.coordonnee;

public class CoordCase {
    private int line;
    private int column;

    public CoordCase(int l, int c)
    {
        line = l;
        column = c;
    }

    public int getLine()
    {
        return  line;
    }

    public int getColumn()
    {
        return column;
    }
}
