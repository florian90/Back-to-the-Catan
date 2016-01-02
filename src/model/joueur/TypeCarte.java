package model.joueur;

import model.jeu.Epoque;

public enum TypeCarte implements Achetable{
	
	Developpement
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.HautParleur, Ressource.Aimant, Ressource.Aimant, Ressource.Bois);
				}
			},
	DeplacerVoleur
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(Ressource.HautParleur, Ressource.Aimant, Ressource.Aimant, Ressource.Bois);
				}
			};

}
