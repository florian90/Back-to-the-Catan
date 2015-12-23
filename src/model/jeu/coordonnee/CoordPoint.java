package model.jeu.coordonnee;

public class CoordPoint {
	/*
	 * Les coordonées d'un points sont les trois coordonées des cases touchant ce point.
	 */
	private CoordCase gauche, droite, vertical;

	public CoordPoint(CoordCase g, CoordCase d, CoordCase v)
	{
		gauche = g;
		droite = d;
		vertical = v;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		CoordPoint that = (CoordPoint) o;

		if (gauche != null ? !gauche.equals(that.gauche) : that.gauche != null)
			return false;
		if (droite != null ? !droite.equals(that.droite) : that.droite != null)
			return false;
		return vertical != null ? vertical.equals(that.vertical) : that.vertical == null;

	}

	@Override
	public int hashCode()
	{
		int result = gauche != null ? gauche.hashCode() : 0;
		result = 31*result + (droite != null ? droite.hashCode() : 0);
		result = 31*result + (vertical != null ? vertical.hashCode() : 0);
		return result;
	}

	public CoordCase getDroite()
	{
		return droite;
	}

	public CoordCase getGauche()
	{
		return gauche;
	}

	public CoordCase getVertical()
	{
		return vertical;
	}
}
