package test.plateau.application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vue.jeu.Constants;

public class Route extends Canvas {
	String type = "";

	public Route(int x, int y, String type)
	{
		super(Constants.hexWidth + 10, Constants.hexHeight + 10);
		setTranslateX(x);
		setTranslateY(y);
		this.type = type;
		paint(getGraphicsContext2D(), type);
		/*setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				change(getGraphicsContext2D(), type);
				
			}
		});*/
	}

	private void paint(GraphicsContext gc, String type)
	{
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(7);
		switch (type)
		{
			case "/h":
				gc.strokeLine(0, Constants.hexHeight/3 - 10, Constants.hexWidth/2, 0);

				break;
			case "\\h":
				gc.strokeLine(Constants.hexWidth/2, 0, Constants.hexWidth, Constants.hexHeight/3 - 10);

				break;
			case "|g":
				gc.strokeLine(0, Constants.hexHeight/3 - 10, 0, 2*Constants.hexHeight/3 + 10);

				break;
			case "/b":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
			case "\\b":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
			case "|d":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
		}

	}
	
	private void change(GraphicsContext gc, String type)
	{
		gc.setStroke(Color.YELLOW);
		gc.setLineWidth(7);
		switch (type)
		{
			case "/h":
				gc.strokeLine(0, Constants.hexHeight/3 - 10, Constants.hexWidth/2, 0);

				break;
			case "\\h":
				gc.strokeLine(Constants.hexWidth/2, 0, Constants.hexWidth, Constants.hexHeight/3 - 10);

				break;
			case "|g":
				gc.strokeLine(0, Constants.hexHeight/3 - 10, 0, 2*Constants.hexHeight/3 + 10);

				break;
			case "/b":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
			case "\\b":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
			case "|d":
				gc.strokeLine(0, 0, Constants.hexWidth/3, Constants.hexHeight/3);

				break;
		}

	}
}
