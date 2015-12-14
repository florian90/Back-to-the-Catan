package test.plateau.application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class HexagonPolygon extends Polygon
{
	private final int spacing = 1;
	
	private int type, number;
	
	public HexagonPolygon(int px, int py, int number)
	{
		double rayon = Constants.hexWidth/2 - spacing;
		
		double x = (double) px + rayon, y = (double) py + rayon;
		this.number = number;
		this.getPoints().addAll(new Double[]{x, y - rayon, x + rayon, y - rayon/2, x + rayon, y + rayon/2, x, y + rayon, x - rayon, y + rayon/2, x - rayon, y - rayon/2});
		setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				setFill(Color.YELLOW);
				/*
				if(event.getX()>20 && event.getX()<Constants.hexWidth-20 )
				{
					highlight(getGraphicsContext());
					setFocused(true);
				}*/
			}
		});
	}
}
