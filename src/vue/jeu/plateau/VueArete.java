package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.jeu.Arete;
import model.jeu.Jeu;
import vue.jeu.Constants;

public class VueArete extends Line
{
	private Arete m_arete;
	private Jeu m_jeu;
	
	public VueArete(Arete a, float debutX, float debutY, float finX, float finY, Jeu jeu)
	{
		super(debutX,debutY,finX,finY);
		m_arete = a;
		m_jeu = jeu;
		
		setStrokeWidth(Constants.roadWidth);
		setStroke(Color.GRAY);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				//setStroke(Color.WHITE);
				/*if(m_arete.peutEtreAutoroute())
				{
					setStroke(Color.YELLOW);	
				}
				else
				{
					setStroke(Color.BLUE);
				}*/
				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
			//	setStroke(Color.GRAY);				
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(m_arete.toString());
				if(m_arete.peutEtreAutoroute())
				{
					setStroke(Color.YELLOW);	
				}
				else
				{
					setStroke(Color.BLUE);
				}
				
			
			}
		});
		
		
	}

}
