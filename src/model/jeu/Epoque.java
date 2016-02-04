package model.jeu;

import model.joueur.Ressource;

public enum Epoque {
	_1855,
	_1955,
	_1985,
	_2015;

	public static Ressource getR1(Epoque epoque)
	{
		switch (epoque)
		{
		case _1855:
			return Ressource.Bois;
		case _1955:
			return Ressource.HautParleur;
		case _1985:
			return Ressource.Plutonium;
		case _2015:
			return Ressource.Aimant;
		}
		return null;
	}

	public static Ressource getR2(Epoque epoque)
	{
		switch (epoque)
		{
		case _1855:
			return Ressource.Roue;
		case _1955:
			return Ressource.Antenne;
		case _1985:
			return Ressource.MorceauSchema;
		case _2015:
			return Ressource.Ventilateur;
		}
		return null;
	}

	public static String toString(Epoque epoque)
	{
		switch(epoque)
		{
			case _1855:
				return "1855";
			case _1955:
				return "1955";
			case _1985:
				return "1985";
			case _2015:
				return "2015";
		}
		return null;
	}

	public static int index(Epoque epoque)
	{
		switch(epoque)
		{
			case _1855:
				return 3;
			case _1955:
				return 4;
			case _1985:
				return 1;
			case _2015:
				return 2;
		}
		return 0;
	}
}
