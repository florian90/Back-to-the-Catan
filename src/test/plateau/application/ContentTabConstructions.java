package test.plateau.application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;

public class ContentTabConstructions extends GridPane
{


	public ContentTabConstructions()
	{
	
		Spinner spinRoute = new Spinner(0,100,0);
		Spinner spinAutoroute = new Spinner(0,100,0);
		Spinner spinVillage = new Spinner(0,100,0);
		Spinner spinVille = new Spinner(0,100,0);
		Button acheter = new Button("Acheter");
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		
	//	add(new Label("Constructions"),0,0);
		add(new Label("Route"),0, 0);
		add(new Label("Autoroute"),0, 1);
		add(new Label("Village"),0, 2);
		add(new Label("Ville"),0, 3);
		
		
		spinRoute.setPrefWidth(50);
		spinAutoroute.setPrefWidth(50);
		spinVillage.setPrefWidth(50);
		spinVille.setPrefWidth(50);
		add(spinRoute,2,0);
		add(spinAutoroute,2,1);
		add(spinVillage,2,2);
		add(spinVille,2,3);
		add(new Label("1xMétal + 1xRS"),3,0);
		add(new Label("2xMétal + 1xRS"),3,1);
		add(new Label("2xMétal + 2xRS"),3,2);
		add(new Label("4xMétal + 3xRS"),3,3);
		add(acheter,3,4);

		
		
	}
}
