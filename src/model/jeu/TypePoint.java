package model.jeu;

import model.joueur.PackRess;
import model.joueur.Ressource;

public enum TypePoint
{
	Ville,
	Village,
	Vide;

	public static PackRess cout(TypePoint type, Epoque epoque)
	{
		Ressource spe = Epoque.getR2(epoque);
		switch (type)
		{
			case Ville:    return new PackRess(Ressource.Metal, 4, spe, 3);
			case Village:  return new PackRess(Ressource.Metal, 2, spe, 2);
			default:       return null;
		}
	}
}
