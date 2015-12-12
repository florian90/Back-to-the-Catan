package test.plateau.application;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DetailInvention extends GridPane 
{
	public DetailInvention(String type,String cout, String imageUrl,String description)
	{
		add(new Label(type),0,0);
		ImageView vignette = new ImageView(new Image(imageUrl));
		vignette.setPreserveRatio(true);
		vignette.setFitWidth(75);
		add(vignette,0,1,2,1);
		add(new Label("Description :"),0,3);
		add(new Label(description),0,5);
		add(new Label("Coût :"+cout),0,7);
		
		
	}
}
