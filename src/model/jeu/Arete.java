package model.jeu;

import model.jeu.coordonnee.CoordArete;
import model.joueur.Joueur;

public class Arete {
	/*
	 * Correspond à un coté d'une case.
	 */
	private CoordArete m_coord;
	private TypeArete m_type;
	private Joueur m_proprietaire;

	public Arete(CoordArete coord, TypeArete type, Joueur propietaire)
	{
		m_coord = coord;
		m_type = type;
		m_proprietaire = propietaire;
	}

	public Arete(CoordArete coord)
	{
		this(coord, TypeArete.Vide, null);
	}

	public CoordArete getCoord()
	{
		return m_coord;
	}

	public Joueur getProprietaire()
	{
		return m_proprietaire;
	}

	public TypeArete getType()
	{
		return m_type;
	}

	//Todo: construire un nouveau TypeRoute pour un certain joueur
	public void construire(Joueur joueur, TypeArete type)
	{
		//Fait par Val à vérifier
		/*if (peutConstruire(joueur, type))
		{
				m_type = type;
				m_proprietaire = joueur;
		}*/
	}

	//Todo: Check si on peut construire le type demandé pour le joueur
	public boolean peutConstruire(Joueur joueur, TypeArete type)
	{
		//Fait par Val non fini
		//Base pour savoir quelles Aretes sont compatibles autoroute.
		/*int o,i;
	    for(o=-4;o<-2;o++)
	    {
	        for(i=-4;i<0;i++)
	        {
	            System.out.println("1. (%d,%d ; %d,%d ; %d,%d)\n",i,o,i+1,o,i+1,o+1);
	            System.out.println("2. (%d,%d ; %d,%d ; %d,%d)\n",i,o+1,i+1,o+1,i,o);
	            System.out.println("3. (%d,%d ; %d,%d ; %d,%d)\n",-(i+1),-o,-i,-o,-(i+1),-(o+1));
	            System.out.println("4. (%d,%d ; %d,%d ; %d,%d)\n",-(i+1),-(o+1),-i,-(o+1),-i,-o);
	            System.out.println("5. (%d,%d ; %d,%d ; %d,%d)\n",i+4,o+i+5,i+5,o+i+5,i+4,o+i+4);
	            System.out.println("6. (%d,%d ; %d,%d ; %d,%d)\n",i+4,o+i+5,i+5,o+i+5,i+5,o+i+6);
	            System.out.println("7. (%d,%d ; %d,%d ; %d,%d)\n",-(i+5),-(o+i+5),-(i+4),-(o+i+5),-(i+4),-(o+i+4));
	            System.out.println("8. (%d,%d ; %d,%d ; %d,%d)\n",-(i+5),-(o+i+5),-(i+4),-(o+i+5),-(i+5),-(o+i+6));
	        }
	        for(i=-2;i<0;i++)
	        {
	            if((i-1)!= -3 || (o+3) != 0 || -(i-1)!= 3)
	            {
	                System.out.println("9. (%d,%d ; %d,%d ; %d,%d)\n",i-2,o+2,i-1,o+2,i-1,o+3);
	                System.out.println("10. (%d,%d ; %d,%d ; %d,%d)\n",-(i-1),-(o+2),-(i-2),-(o+2),-(i-1),-(o+3));
	            }
	        }
	    }
	    System.out.println("11. (%d,%d ; %d,%d ; %d,%d)\n",-3,-1,-2,-1,-3,-2);
	    System.out.println("12. (%d,%d ; %d,%d ; %d,%d)\n",2,1,3,1,3,2);*/
		
		/*if ((type==Route ET m_type==Vide ET  m_coord != Autorouteconstructible) OU (type=Autoroute ET m_type=Vide ET m_coord = Autorouteconstructible))
		{
			return true;
		}
		else
		{
			return false;
		}*/
		return false;
	}
	
	public String toString()
	{
		return m_coord.toString();
	}

}
