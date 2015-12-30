package test.plateau.application;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import vue.jeu.Constants;
import vue.jeu.plateau.VuePoint;

public class MenuContextuelStyleeee extends Group {
	Arc arc;

	public MenuContextuelStyleeee(int x, int y)
	{
		arc = new Arc();
		arc.setCenterX(x);
		arc.setCenterY(y);
		arc.setRadiusX(Constants.arcWidth);
		arc.setRadiusY(Constants.arcHeight);
		arc.setStartAngle(-15);
		arc.setLength(210);
		arc.setType(ArcType.OPEN);
		arc.setFill(Color.TRANSPARENT);
		arc.setStroke(Color.RED);
		getChildren().add(new VuePoint(null, x, y, Constants.pointRadius));

		ImageView imageVille = new ImageView("textures/Ville.jpg");
		ImageView imageVillage = new ImageView("textures/Village.jpg");

		imageVillage.setX(x);
		imageVillage.setY(y);
		imageVillage.setFitWidth(50);
		imageVillage.setFitHeight(50);

		getChildren().add(arc);
		getChildren().addAll(imageVillage);
	}
}
