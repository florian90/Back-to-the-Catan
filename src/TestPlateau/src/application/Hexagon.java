package application;

import java.nio.channels.SelectionKey;
import java.util.Random;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class Hexagon extends Canvas
{
	private int type,number;



	public Hexagon(int x, int y,int number)
	{
		super(Constants.hexWidth,Constants.hexHeight);
		this.number = number;
		setTranslateX(x);
		setTranslateY(y);
		paint(getGraphicsContext2D(),x,y);
		System.out.println(type);
		/*setOnMouseMoved(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				paint(getGraphicsContext2D(),x,y);
			}
		});*/
		setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				highlight(getGraphicsContext2D());

				
			}
		});
		setOnKeyPressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				setTranslateX(getTranslateX()+10);
				
			}
		});
	}
	

	private void paint(GraphicsContext gc, int x, int y)
	{
		type=new Random().nextInt(10);
		switch(type)
		{
		case 0:
			gc.drawImage(new Image("Textures/hexBois.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 1:
			gc.drawImage(new Image("Textures/hexMetal.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 2:
			gc.drawImage(new Image("Textures/hexRoue.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 3:
			gc.drawImage(new Image("Textures/hexHautParleur.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 4:
			gc.drawImage(new Image("Textures/hexAntenne.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 5:
			gc.drawImage(new Image("Textures/hexVentilateur.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 6:
			gc.drawImage(new Image("Textures/hexSchema.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 7:
			gc.drawImage(new Image("Textures/hexAimant.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 8:
			gc.drawImage(new Image("Textures/hexPlutonium.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		case 9:
			gc.drawImage(new Image("Textures/hexAutoroute.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
			break;
		}
		gc.setFill(Color.BLACK);
		gc.fillOval(Math.round(getWidth()/2)-15,Math.round(getHeight())/2-15, 30,30);
		gc.setFill(Color.WHITE);
		gc.fillOval(Math.round(getWidth()/2)-14,Math.round(getHeight())/2-14, 28,28);
		gc.setFill(Color.BLACK);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(""+number, Math.round(getWidth()/2),Math.round(getHeight())/2+5 );
	}
	
	private void highlight(GraphicsContext gc)
	{
		gc.setGlobalBlendMode(BlendMode.OVERLAY);
		gc.drawImage(new Image("Textures/hexagon1.png"), 0, 0,Constants.hexWidth,Constants.hexHeight);
	}

}
