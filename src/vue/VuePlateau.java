package vue;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.jeu.Arete;
import model.jeu.Case;
import model.jeu.Plateau;
import model.jeu.Point;
import model.jeu.coordonnee.CoordCase;
import model.jeu.coordonnee.CoordPoint;
import test.plateau.application.Constants;
import test.plateau.application.ViewCase;

public class VuePlateau extends Group {
	
	private int m_nbrCasesLarge;
	private Group m_cases;
	private Group m_aretes;
	private Group m_points;
	private Plateau m_plateau;

	public VuePlateau(int x, int y, Plateau plateau)
	{
		super();
		setTranslateX(x);
		setTranslateY(y);
		m_plateau = plateau;

		initCases();
		initAretes();
		initPoints();
	}

	private void initPoints()
	{
		//Todo: Mettre des ViewPoints et pas des cercles
		SimpleFloatCoo center;
		float rad = 10;
		m_points = new Group();
		for (Point pt : m_plateau.getPoints())
		{
			center = getCoord(pt.getCoo());
			//m_points.getChildren().add(new Circle(center.x, center.y, rad));
			m_points.getChildren().add(new VuePoint(pt,center.x, center.y, rad));
		}
		getChildren().add(m_points);
	}

	private void initAretes()
	{
		//Todo: Utiliser des ViewAretes et pas des lignes
		m_aretes = new Group();
		SimpleFloatCoo debut, fin;
		//Line line;
		VueArete vueArete;
		for (Arete arete : m_plateau.getAretes())
		{
			/*debut = getCoord(arete.getCoord().getDebut());
			fin = getCoord(arete.getCoord().getFin());
			line = new Line(debut.x, debut.y, fin.x, fin.y);
			line.setStrokeWidth(Constants.roadWidth);
			line.setStroke(Color.GRAY);
			m_aretes.getChildren().add(line);*/
			
			debut = getCoord(arete.getCoord().getDebut());
			fin = getCoord(arete.getCoord().getFin());
			vueArete = new VueArete(arete, debut.x, debut.y, fin.x, fin.y);
			m_aretes.getChildren().add(vueArete);
		}
		getChildren().add(m_aretes);
	}

	private void initCases()
	{
		// Retrouve la taille du plateau
		// /!\ doit être hexagonal
		int min, max;
		min = max = m_plateau.getCases().get(0).getCoo().getLine();
		for (Case tuile : m_plateau.getCases())
		{
			int i;
			i = tuile.getCoo().getLine();
			if (i < min)
				min = i;
			if (i > max)
				max = i;
		}
		m_nbrCasesLarge = max - min + 1;

		// Initialise toutes les cases
		m_cases = new Group();
		ViewCase viewCase;
		CoordCase coo;
		SimpleFloatCoo simplefCoo;
		for (Case tuile : m_plateau.getCases())
		{
			coo = tuile.getCoo();
			simplefCoo = getCoord(coo);
			viewCase = new ViewCase(simplefCoo.x - Constants.hexWidth/2, simplefCoo.y - Constants.hexHeight/2, tuile);
			m_cases.getChildren().add(viewCase);
		}
		getChildren().add(m_cases);
	}

	/*
	 * Retourne la position du centre de l'hexagone en fonction des coordonnés d'entrée
	 */
	private SimpleFloatCoo getCoord(CoordCase coordCase)
	{
		//#BUG: Ne marche que pour une taille de 7 :'(
		int rayon = (m_nbrCasesLarge + 1)/2;//en nombre d'hexagones
		float x, y, x_offset, y_offset;
		//Positions de l'hexa (0,0) :
		x_offset = (1 + (1.5f*(rayon - 1)))*Constants.hexWidth*2/(float) Math.sqrt(3);
		y_offset = Constants.hexHeight/2*m_nbrCasesLarge;
		x = x_offset + Constants.hexWidth/2*coordCase.getLine() + Constants.hexWidth*coordCase.getColumn();
		y = y_offset + Constants.hexHeight/2*1.5f*coordCase.getLine();
		return new SimpleFloatCoo(x, y);
	}

	/*
	 * Retourne la position du centre du point
	 */
	private SimpleFloatCoo getCoord(CoordPoint coordPoint)
	{
		SimpleFloatCoo c1, c2, c3;
		c1 = getCoord(coordPoint.getGauche());
		c2 = getCoord(coordPoint.getDroite());
		c3 = getCoord(coordPoint.getVertical());
		return new SimpleFloatCoo((c1.x + c2.x + c3.x)/3, (c1.y + c2.y + c3.y)/3);
	}

	/*
	 * Classe simple pour retourner 2 valeurs dans une fonction
	 */
	private class SimpleFloatCoo {
		private float x, y;

		SimpleFloatCoo(float p_x, float p_y)
		{
			x = p_x;
			y = p_y;
		}
	}
}
