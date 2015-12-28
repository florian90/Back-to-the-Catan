package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.jeu.Arete;
import vue.Constants;

public class VueArete extends Line
{
	private Arete m_arete;
	
	public VueArete(Arete a, float debutX, float debutY, float finX, float finY)
	{
		super(debutX,debutY,finX,finY);
		m_arete = a;
		
		setStrokeWidth(Constants.roadWidth);
		setStroke(Color.GRAY);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setStroke(Color.WHITE);
				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setStroke(Color.GRAY);				
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(m_arete.toString());
				
			}
		});
		
		
	}

}
