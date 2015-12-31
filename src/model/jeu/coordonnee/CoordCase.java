package model.jeu.coordonnee;

public class CoordCase {
	/*
	 * La case de coordon�e nulle est la case centrale.
	 * La coo ligne augmente quand on prend la case � l'est de la case initiale.
	 * La coo column augmente quand on prend la case au sudWest de la case initiale
	 */
	private int line;
	private int column;

	public CoordCase(int l, int c)
	{
		line = l;
		column = c;
	}

	public int getLine()
	{
		return line;
	}

	public int getColumn()
	{
		return column;
	}

	public CoordCase northEast()
	{
		return new CoordCase(line - 1, column + 1);
	}

	public CoordCase east()
	{
		return new CoordCase(line, column + 1);
	}

	public CoordCase southEast()
	{
		return new CoordCase(line + 1, column);
	}

	public CoordCase southWest()
	{
		return new CoordCase(line + 1, column - 1);
	}

	public CoordCase west()
	{
		return new CoordCase(line, column-1);
	}

	public CoordCase northWest()
	{
		return new CoordCase(line-1, column);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CoordCase coordCase = (CoordCase) o;

		if (line != coordCase.line)
			return false;
		return column == coordCase.column;
	}

	@Override
	public int hashCode()
	{
		int result = line;
		result = 31*result + column;
		return result;
	}

	@Override
	public String toString()
	{
		return "(" + line + ", " + column + ")";
	}
	
	public boolean equals(CoordCase cc)
	{
		return (line == cc.getLine() && column == cc.getColumn());
	}
}
