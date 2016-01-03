package model.joueur;

import model.jeu.Epoque;

public enum TypeCarte implements Achetable{
	
	Developpement
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(/*Epoque.getR1(epoque),Epoque.getR2(epoque)*/);
				}
			},
	DeplacerVoleur
			{
				@Override
				public PackRess cout(Epoque epoque)
				{
					return new PackRess(/*Epoque.getR1(epoque),Epoque.getR2(epoque)*/);
				}
			};

}
