package model.joueur;

public class Carte implements Achetable {
	public PackRess cout()
	{
		//FixMe: Mettre le cour réel
		return new PackRess(Ressource.HautParleur, Ressource.Aimant, Ressource.Aimant, Ressource.Bois);
	}
}
