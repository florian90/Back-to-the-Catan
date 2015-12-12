package test.plateau.application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ContentTabCartes extends GridPane
{
	public ContentTabCartes()
	{
		ImageView cartes = new ImageView(new Image("textures/cards.jpg"));
		
		cartes.setPreserveRatio(true);
		cartes.setFitWidth(200);
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		add(new Label("Coût : 2 ressources d'époques différentes"),0,0);
		add(cartes,0,2);
		
	}
}
