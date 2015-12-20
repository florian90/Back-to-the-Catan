package model.joueur;

import java.util.HashMap;

public class PackRess extends HashMap<Ressource, Integer> {

	public PackRess()
	{
		for(Ressource r: Ressource.values())
		{
			put(r, 0);
		}
	}

	public PackRess(Ressource... rs)
	{
		this();
		for(Ressource r : rs)
		{
			add(r);
		}
	}

	public PackRess(PackRess pack)
	{
		this();
		forEach((r,s)->set(r, pack.get(r)));
	}

	public void add(Ressource r)
	{
		put(r, get(r)+1);
	}

	public void add(Ressource r, int nbr)
	{
		put(r, get(r)+nbr);
	}

	public void remove(Ressource ress)
	{
		put(ress, get(ress)-1);
	}

	public void remove(Ressource r, int nbr)
	{
		put(r, get(r)-nbr);
	}

	public void set(Ressource r, int nbr)
	{
		put(r, nbr);
	}

	public void mult(int factor)
	{
		/* Multiplie tous les champs par factor */
		forEach((r, i) -> put(r,i*factor));
	}
}
