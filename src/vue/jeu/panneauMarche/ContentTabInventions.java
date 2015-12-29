package vue.jeu.panneauMarche;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import vue.jeu.Desactivable;

public class ContentTabInventions extends GridPane implements Desactivable{

	private Button acheterTrain; 
	private Button acheterRadio; 
	private Button acheterConvecteur; 
	private Button acheterHoverboard;
	
	public ContentTabInventions()
	{
		 acheterTrain = new Button("Acheter");
		 acheterRadio = new Button("Acheter");
		 acheterConvecteur = new Button("Acheter");
		 acheterHoverboard = new Button("Acheter");
		
		
		Label hoverboard = new Label("Convecteur Temporel");
		Label detailsTrain = new Label("Détails");
		Label detailsRadio = new Label("Détails");
		Label detailsConvecteur = new Label("Détails");
		Label detailsHoverboard = new Label("Détails");
		StackPane stack = new StackPane();
		
		hoverboard.setWrapText(true);
		hoverboard.setPrefWidth(65);
		
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);
		
		//	add(new Label("Constructions"),0,0);
		add(new Label("Train"), 0, 0);
		add(new Label("Radio"), 0, 1);
		add(hoverboard, 0, 2);
		add(new Label("Hoverboard"), 0, 3);
		
		
		acheterTrain.setPrefWidth(75);
		acheterRadio.setPrefWidth(75);
		acheterConvecteur.setPrefWidth(75);
		acheterHoverboard.setPrefWidth(75);
		add(acheterTrain, 2, 0);
		add(acheterRadio, 2, 1);
		add(acheterConvecteur, 2, 2);
		add(acheterHoverboard, 2, 3);
		add(detailsTrain, 3, 0);
		add(detailsRadio, 3, 1);
		add(detailsConvecteur, 3, 2);
		add(detailsHoverboard, 3, 3);
		add(stack, 0, 5, 3, 10);
		
		detailsTrain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Train", "4xRoue + 5xBois", "textures/train.jpg", "Ceci est un train du far west"));
				

			}
		});
		detailsRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Radio", "2xAntenne + 4xHaut-Parleur + 3xMétal", "textures/radio.jpg", "Ceci est une radio"));
				//add(new DetailInvention("Radio", "2xBois + 2xMetal", "textures/hexBois.png", "Ceci est un train du far west"),0,6,2,10);

				
			}
		});
		detailsConvecteur.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Convecteur Temporel", "3xMorceau de Schéma + 6xPlutonium", "textures/convecteur.gif", "Ceci est un convecteur temporel"));
				//add(new DetailInvention("Convecteur temporel", "2xBois + 2xMetal", "textures/hexBois.png", "Ceci est un train du far west"),0,6,2,10);

				
			}
		});
		detailsHoverboard.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Hoverboard", "4xVentilateur + 2xAimant + 3xMétal", "textures/hoverboard.jpg", "Ceci est un hoverboard"));
				//	add(new DetailInvention("Hoverboard", "2xBois + 2xMetal", "textures/hexBois.png", "Ceci est un train du far west"),0,6,2,10);

				
			}
		});

		
	}

	@Override
	public void desactiver() {
		
		acheterConvecteur.setDisable(true);
		acheterHoverboard.setDisable(true);
		acheterRadio.setDisable(true);
		acheterTrain.setDisable(true);
		
	}
	
	@Override
	public void activer() {
		acheterConvecteur.setDisable(false);
		acheterHoverboard.setDisable(false);
		acheterRadio.setDisable(false);
		acheterTrain.setDisable(false);
	}

}
