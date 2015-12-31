package model.joueur;

public enum Ressource {
	
	Aimant,
	Antenne,
	Autoroute,
	Bois,
	HautParleur,
	Metal,
	MorceauSchema,
	Plutonium,
	Roue,
	Ventilateur;
	
	public static String toString(Ressource r)
	{
		switch(r)
		{
		case Aimant:
			return "Aimant";
		case Autoroute:
			return "Autoroute";
		case Antenne:
			return "Antenne";
		case Bois:
			return "Bois";
		case HautParleur:
			return "Haut-Parleur";
		case Metal:
			return "Métal";
		case MorceauSchema:
			return "Morceaux de Schéma";
		case Plutonium:
			return "Plutonium";
		case Roue:
			return "Roue";
		case Ventilateur:
			return "Ventilateur";
		}
		
		return "";
	}
}
