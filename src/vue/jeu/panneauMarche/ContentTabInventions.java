package vue.jeu.panneauMarche;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.joueur.Invention;
import model.joueur.PackRess;
import model.joueur.Ressource;
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;
import vue.jeu.Fenetre;

public class ContentTabInventions extends GridPane implements Desactivable{

	private Button acheterTrain; 
	private Button acheterRadio; 
	private Button acheterConvecteur; 
	private Button acheterHoverboard;
	private Jeu m_jeu;

	public ContentTabInventions(Jeu jeu, ContentJoueur ctj )
	{
		m_jeu = jeu;

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
		setHeight(800);

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
		add(stack, 0, 5, 4, 10);


		/**********************************
		 ******Gestion des Evenements********
		 ***********************************/
		detailsTrain.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Train", " - 4xRoue\n           - 5xBois", "textures/train.jpg", "Ceci est un train du far west"));


			}
		});
		detailsRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Radio", " - 2xAntenne \n           - 4xHaut-Parleur \n           - 3xMétal", "textures/radio.jpg", "Ceci est une radio"));



			}
		});
		detailsConvecteur.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Convecteur Temporel", " - 3xMorceau de Schéma \n           - 6xPlutonium", "textures/convecteur.gif", "Ceci est un convecteur temporel"));
				//add(new DetailInvention("Convecteur temporel", "2xBois + 2xMetal", "textures/hexBois.png", "Ceci est un train du far west"),0,6,2,10);


			}
		});
		detailsHoverboard.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Hoverboard", " - 4xVentilateur \n           - 2xAimant \n           - 3xMétal", "textures/hoverboard.jpg", "Ceci est un hoverboard"));
				//	add(new DetailInvention("Hoverboard", "2xBois + 2xMetal", "textures/hexBois.png", "Ceci est un train du far west"),0,6,2,10);


			}
		});

		acheterTrain.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.Train, Epoque._2015))
				{
					/*	PackRess pack = new PackRess();
					pack.add(Ressource.Roue,4);
					pack.add(Ressource.Bois,5);*/
					m_jeu.getJoueur().construireInvention(Invention.Train.cout(Epoque._1855), Invention.Train);		
					acheterTrain.setDisable(true);
					ctj.update();

				}
				else
				{
					System.out.println("Pas assez de ressources");
				}

			}
		});

		acheterRadio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.Radio, Epoque._1955))
				{
					/*PackRess pack = new PackRess();
					pack.add(Ressource.Antenne,2);
					pack.add(Ressource.HautParleur,4);
					pack.add(Ressource.Metal,3);*/
					m_jeu.getJoueur().construireInvention(Invention.Radio.cout(Epoque._1955), Invention.Radio);	
					acheterRadio.setDisable(true);
					ctj.update();
				}
				else
				{
					System.out.println("Pas assez de ressources");
				}
			}
		});

		acheterConvecteur.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.ConvecteurTemporel, Epoque._1985))
				{
					/*PackRess pack = new PackRess();
					pack.add(Ressource.MorceauSchema,3);
					pack.add(Ressource.Plutonium,6);*/
					m_jeu.getJoueur().construireInvention(Invention.ConvecteurTemporel.cout(Epoque._1985), Invention.ConvecteurTemporel);		
					acheterConvecteur.setDisable(true);
					ctj.update();
				}
				else
				{
					System.out.println("Pas assez de ressources");
				}
			}
		});

		acheterHoverboard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.HoverBoard, Epoque._2015))
				{
					/*PackRess pack = new PackRess();
					pack.add(Ressource.Ventilateur,4);
					pack.add(Ressource.Aimant,2);
					pack.add(Ressource.Metal,3);*/
					m_jeu.getJoueur().construireInvention(Invention.HoverBoard.cout(Epoque._2015), Invention.HoverBoard);
					acheterHoverboard.setDisable(true);
					ctj.update();
				}
				else
				{
					System.out.println("Pas assez de ressources");
				}
			}
		});


	}

	public void update()
	{
		activer();
		/*if(m_jeu.getJoueur().getM_inventions().get(Invention.ConvecteurTemporel) );
		{
			acheterConvecteur.setDisable(true);
		}
		if(m_jeu.getJoueur().getM_inventions().get(Invention.HoverBoard));
		{
			acheterHoverboard.setDisable(true);
		}
		if(m_jeu.getJoueur().getM_inventions().get(Invention.Radio));
		{
			acheterRadio.setDisable(true);
		}
		if(m_jeu.getJoueur().getM_inventions().get(Invention.Train));
		{
			acheterTrain.setDisable(true);
		}*/
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
