package vue.jeu.plateau;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.jeu.Point;

public class VuePoint extends Circle{
	
	private Point m_point;
	
	public VuePoint(Point p, float centreX, float centreY, float rayon)
	{
		super(centreX,centreY,rayon);
		m_point = p;
		
		setStroke(Color.BLACK);
		setFill(Color.BLACK);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setFill(Color.WHITE);				
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setFill(Color.BLACK);				
			}
		});
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println(m_point.toString());
				setFill(Color.BLACK);
				setStroke(Color.WHITE);
				
			}
		});
	}

}
