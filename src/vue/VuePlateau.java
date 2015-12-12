package vue;

import javafx.scene.Group;
import model.jeu.Case;
import model.jeu.Plateau;
import model.jeu.coordonnee.CoordCase;
import test.plateau.application.Constants;
import test.plateau.application.ViewCase;

public class VuePlateau extends Group{
    private int m_nbrCasesLarge;
    private Group m_cases;
    private Plateau m_plateau;

    public VuePlateau(int x, int y, Plateau plateau)
    {
        super();
        setTranslateX(x);
        setTranslateY(y);
        m_plateau = plateau;

        m_cases = new Group();
        initCases();
        getChildren().add(m_cases);
    }

    private void initCases()
    {
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
            viewCase = new ViewCase(simplefCoo.x, simplefCoo.y, tuile);
            m_cases.getChildren().add(viewCase);
        }
    }

    /*
     * Retourne la position du centre de l'hexagone en fonction des coordonnés d'entrée
     */
    private SimpleFloatCoo getCoord(CoordCase coordCase)
    {
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
