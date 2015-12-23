package model.joueur;

public enum Invention implements Achetable {
	/*TrainKiVol,*/
	Train
			{
				@Override
				public PackRess cout()
				{
					return new PackRess(Ressource.Roue, 4, Ressource.Bois, 5);
				}
			},
	HoverBoard
			{
				@Override
				public PackRess cout()
				{
					return new PackRess(Ressource.Antenne, 2, Ressource.HautParleur, 4, Ressource.Metal, 3);
				}
			},
	ConvecteurTemporel
			{
				@Override
				public PackRess cout()
				{
					return new PackRess(Ressource.MorceauSchema, 3, Ressource.Plutonium, 6);
				}
			},
	Radio
			{
				@Override
				public PackRess cout()
				{
					return new PackRess(Ressource.Ventilateur, 4, Ressource.Aimant, 2, Ressource.Metal, 3);
				}
			}
}
