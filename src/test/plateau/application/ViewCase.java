package test.plateau.application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import model.jeu.Case;
import model.joueur.Ressource;

import java.util.Random;

public class ViewCase extends ImageView{

	private Case m_case;

	public ViewCase(float p_x, float p_y, Case p_case)
	{
		super();
		setX(p_x);
		setY(p_y);
		m_case = p_case;
		setImage(new Image("textures/hex"+m_case.getRessource()+".png"));
		setFitHeight(Constants.hexHeight-Constants.roadWidth);
		setFitWidth(Constants.hexWidth-Constants.roadWidth);
		/*(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setImage();
			}
		});*/
	}
}
