package test.plateau.application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Random;

public class ViewCase extends ImageView{
    private int type, number;
    private Circle cercle;
    private Text textNumber;
	public ViewCase(int p_x, int p_y/*, Case case*/, int p_number)
	{
		super();
        setType();
		setX(p_x);
		setY(p_y);
        number = p_number;
		setFitHeight(Constants.hexHeight);
		setFitWidth(Constants.hexWidth);
        setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
                setType();
			}
		});
	}

	private void setType()
	{
		type=new Random().nextInt(10);
		switch(type)
		{
			case 0:
				setImage(new Image("textures/hexBois.png"));
				break;
			case 1:
				setImage(new Image("textures/hexMetal.png"));
				break;
			case 2:
				setImage(new Image("textures/hexRoue.png"));
				break;
			case 3:
				setImage(new Image("textures/hexHautParleur.png"));
				break;
			case 4:
				setImage(new Image("textures/hexAntenne.png"));
				break;
			case 5:
				setImage(new Image("textures/hexVentilateur.png"));
				break;
			case 6:
				setImage(new Image("textures/hexSchema.png"));
				break;
			case 7:
				setImage(new Image("textures/hexAimant.png"));
				break;
			case 8:
				setImage(new Image("textures/hexPlutonium.png"));
				break;
			case 9:
				setImage(new Image("textures/hexAutoroute.png"));
				break;
		}
	}
}
