package model.jeu;

import model.joueur.Achetable;
import model.joueur.PackRess;
import model.joueur.Ressource;

public enum TypeArete implements Achetable{
	Route
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.Metal, Epoque.getR2(epoque));
				}
			},
	Autoroute
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.Metal, Ressource.Metal, Epoque.getR1(epoque));
				}
			},
	Vide
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return null;
				}
			}
}
