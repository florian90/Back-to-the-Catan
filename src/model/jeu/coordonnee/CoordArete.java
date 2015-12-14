package model.jeu.coordonnee;

public class CoordArete
{
	/*
	 * coordArete : Classe qui permet de caractériser une arète
	 * Attributs :
	 *  - debut, fin : coordonées des points délémitants l'arrete
	 * Les dex attributs sont interchangeables.
	 */
	private CoordPoint debut, fin;

	public CoordArete(CoordPoint d, CoordPoint f)
	{
		debut = d;
		fin = f;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CoordArete that = (CoordArete) o;
		return (debut.equals(that.debut) && fin.equals(that.fin)) || (fin.equals(that.debut) && debut.equals(that.fin));
	}

	@Override
	public int hashCode()
	{
		// Le hashCode est le même si on intervertis debut et fin.
		return debut.hashCode()*fin.hashCode();
	}

	public CoordPoint getDebut()
	{
		return debut;
	}

	public CoordPoint getFin()
	{
		return fin;
	}
}
