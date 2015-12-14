package test.plateau.application;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.jeu.Case;

public class ViewCase extends ImageView
{

	private Case m_case;

	public ViewCase(float p_x, float p_y, Case p_case)
	{
		super();
		setX(p_x + Constants.roadWidth/2);
		setY(p_y + Constants.roadWidth/2);
		m_case = p_case;
		setImage(new Image("textures/hex" + m_case.getRessource() + ".png"));
		setFitHeight(Constants.hexHeight - Constants.roadWidth);
		setFitWidth(Constants.hexWidth - Constants.roadWidth);
		setOnMouseClicked(new EventHandler<MouseEvent>()
		{

			@Override
			public void handle(MouseEvent event)
			{
				System.out.println("Clicked on point : " + m_case);
			}
		});
	}
}
