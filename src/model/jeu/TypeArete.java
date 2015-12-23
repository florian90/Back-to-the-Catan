package model.jeu;

import model.joueur.PackRess;
import model.joueur.Ressource;

public enum TypeArete {
	Route,
	Autoroute,
	Vide;

	public static PackRess cout(TypeArete type, Epoque epoque)
	{
		Ressource spe = Epoque.getR1(epoque);
		switch (type)
		{
			case Route:
				return new PackRess(Ressource.Metal, spe);
			case Autoroute:
				return new PackRess(Ressource.Metal, Ressource.Metal, spe);
			default:
				return null;
		}
	}
}
