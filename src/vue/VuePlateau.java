package vue;

import javafx.scene.Group;
import javafx.scene.shape.Circle;
import model.jeu.Case;
import model.jeu.Plateau;
import model.jeu.Point;
import model.jeu.coordonnee.CoordCase;
import test.plateau.application.Constants;
import test.plateau.application.ViewCase;

public class VuePlateau extends Group{
    private int m_nbrCasesLarge;
    private Group m_cases;
    private Group m_points;
    private Plateau m_plateau;

    public VuePlateau(int x, int y, Plateau plateau)
    {
        super();
        setTranslateX(x);
        setTranslateY(y);
        m_plateau = plateau;

        initCases();
        // Mettre les routes entre les cases et les points
        initPoints();
    }

    private void initPoints()
    {
        //Todo: Mettre des ViewPoints et pas des cercles
        SimpleFloatCoo c1, c2, c3;
        float rad=10;
        m_points = new Group();
        for(Point pt: m_plateau.getPoints())
        {
            c1 = getCoord(pt.getCoo().getGauche());
            c2 = getCoord(pt.getCoo().getDroite());
            c3 = getCoord(pt.getCoo().getVertical());
            // Calcule les coordonées du centre du point à dessiner
            m_points.getChildren().add(new Circle((c1.x+c2.x+c3.x)/3, (c1.y+c2.y+c3.y)/3, rad));
        }
        getChildren().add(m_points);
    }

    private void initCases()
    {
        m_cases = new Group();
        int min, max;
        min = max = m_plateau.getCases().get(0).getCoo().getLine();
        for(Case tuile : m_plateau.getCases())
        {
            int i;
            i = tuile.getCoo().getLine();
            if(i<min)
                min=i;
            if(i>max)
                max=i;
        }
        m_nbrCasesLarge = max-min+1;// Retrouve la taille du plateau

        ViewCase viewCase;
        CoordCase coo;
        SimpleFloatCoo simplefCoo;
        for(Case tuile : m_plateau.getCases())
        {// Initialise toutes les cases
            coo = tuile.getCoo();
            simplefCoo = getCoord(coo);
            viewCase = new ViewCase(simplefCoo.x-Constants.hexWidth/2, simplefCoo.y-Constants.hexHeight/2, tuile);
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
        int rayon = (m_nbrCasesLarge+1)/2;//en nombre d'hexagones
        float x,y, x_offset, y_offset;
        //Positions de l'hexa (0,0) :
        x_offset = (1+(1.5f*(rayon-1)))*Constants.hexWidth*2/(float)Math.sqrt(3);
        y_offset = Constants.hexHeight/2*m_nbrCasesLarge;
        x = x_offset + Constants.hexWidth/2*coordCase.getLine() + Constants.hexWidth*coordCase.getColumn();
        y = y_offset + Constants.hexHeight/2*1.5f*coordCase.getLine();
        return new SimpleFloatCoo(x, y);
    }

    /*
     * Classe simple pour retourner 2 valeurs dans une fonction
     */
    private class SimpleFloatCoo {
        float x, y;
        SimpleFloatCoo(float p_x, float p_y)
        {
            x=p_x;
            y=p_y;
        }
    }
}
