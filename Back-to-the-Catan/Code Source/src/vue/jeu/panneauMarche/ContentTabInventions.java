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
import vue.jeu.ContentJoueur;
import vue.jeu.Desactivable;

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
				stack.getChildren().add(new DetailInvention("Train", " - 4 x Roue\n           - 5 x Bois", "textures/train.jpg", "Ceci est un train du far west"));


			}
		});
		detailsRadio.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Radio", " - 2 x Antenne \n           - 4 x Haut-Parleur \n           - 3 x Métal", "textures/radio.jpg", "Ceci est une radio"));



			}
		});
		detailsConvecteur.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Convecteur Temporel", " - 3 x Morceau de Schéma \n           - 6 x Plutonium", "textures/convecteur.gif", "Ceci est un convecteur temporel"));
			}
		});
		detailsHoverboard.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event)
			{
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(new DetailInvention("Hoverboard", " - 4 x Ventilateur \n           - 2 x Aimant \n           - 3 x Métal", "textures/hoverboard.jpg", "Ceci est un hoverboard"));
			}
		});

		acheterTrain.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.Train, Epoque._2015))
				{
					m_jeu.getJoueur().construireInvention(Invention.Train.cout(Epoque._1855), Invention.Train);		
					acheterTrain.setDisable(true);
					ctj.update();

				}
			}
		});

		acheterRadio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.Radio, Epoque._1955))
				{
					m_jeu.getJoueur().construireInvention(Invention.Radio.cout(Epoque._1955), Invention.Radio);	
					acheterRadio.setDisable(true);
					ctj.update();
				}
			}
		});

		acheterConvecteur.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.ConvecteurTemporel, Epoque._1985))
				{
					m_jeu.getJoueur().construireInvention(Invention.ConvecteurTemporel.cout(Epoque._1985), Invention.ConvecteurTemporel);		
					acheterConvecteur.setDisable(true);
					ctj.update();
				}
				
			}
		});

		acheterHoverboard.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (m_jeu.getJoueur().peutConstruire(Invention.HoverBoard, Epoque._2015))
				{
					m_jeu.getJoueur().construireInvention(Invention.HoverBoard.cout(Epoque._2015), Invention.HoverBoard);
					acheterHoverboard.setDisable(true);
					ctj.update();
				}
			}
		});


	}

	public void update()
	{
		activer();
		if(m_jeu.getJoueur().possedeInvention(Invention.ConvecteurTemporel) );
		{
			acheterConvecteur.setDisable(true);
		}
		if(m_jeu.getJoueur().possedeInvention(Invention.HoverBoard));
		{
			acheterHoverboard.setDisable(true);
		}
		if(m_jeu.getJoueur().possedeInvention(Invention.Radio));
		{
			acheterRadio.setDisable(true);
		}
		if(m_jeu.getJoueur().possedeInvention(Invention.Train));
		{
			acheterTrain.setDisable(true);
		}
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
