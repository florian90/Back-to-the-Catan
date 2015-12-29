package model.jeu;

import model.joueur.Achetable;
import model.joueur.PackRess;
import model.joueur.Ressource;

public enum TypePoint implements Achetable{
	Ville
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.Metal, 4, Epoque.getR1(epoque), 3); // ou Epoque.getR2(epoque) non ?
				}
			},
	Village
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.Metal, 2, Epoque.getR2(epoque), 2); // same
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
