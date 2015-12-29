package model.joueur;

import model.jeu.Epoque;

public class Carte implements Achetable {
	
	public PackRess cout(Epoque epoque)
	{
		//FixMe: Mettre le cour réel
		return new PackRess(Ressource.HautParleur, Ressource.Aimant, Ressource.Aimant, Ressource.Bois);
	}
}
