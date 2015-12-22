package model.joueur;

import java.util.HashMap;

public class PackRess  {
	private HashMap<Ressource, Integer> me;

	public PackRess()
	{
		me = new HashMap<>();
		for(Ressource r: Ressource.values())
		{
			me.put(r, 0);
		}
	}

	public PackRess(Ressource... rs)
	{
		me = new HashMap<>();
		for(Ressource r : rs)
		{
			add(r);
		}
	}

	public PackRess(PackRess pack)
	{
		this();
		me.forEach((r,s)->set(r, pack.me.get(r)));
	}

	public void add(Ressource r)
	{
		me.put(r, me.get(r)+1);
	}

	public void add(Ressource r, int nbr)
	{
		me.put(r, me.get(r)+nbr);
	}

	public void remove(Ressource ress)
	{
		me.put(ress, me.get(ress)-1);
	}

	public void remove(Ressource r, int nbr)
	{
		me.put(r, me.get(r)-nbr);
	}

	public void set(Ressource r, int nbr)
	{
		me.put(r, nbr);
	}

	public void mult(int factor)
	{
		/* Multiplie tous les champs par factor */
		me.forEach((r, i) -> me.put(r,i*factor));
	}

	public void add(PackRess pack)
	{
		for(Ressource ressource : Ressource.values())
		{
			set(ressource, me.get(ressource) + pack.me.get(ressource));
		}
	}

	public void remove(PackRess pack)
	{
		for(Ressource ressource : Ressource.values())
		{
			set(ressource, me.get(ressource) - pack.me.get(ressource));
		}
	}
}
