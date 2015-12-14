package test.plateau.application;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.jeu.Epoque;
import model.jeu.Plateau;
import vue.VuePlateau;

import java.util.ArrayList;

public class Fenetre extends AnchorPane
{
	StackPane stack;
	ArrayList<VuePlateau> plateaux;
	int plateauActuel;//index du plateau dans la liste des plateaux (0 � 3)
	Button suiv, prec;
	Label numPlateau;
	ContentTabConstructions cTC = new ContentTabConstructions();
	ContentTabInventions cTI = new ContentTabInventions();
	ContentTabCartes CTCards = new ContentTabCartes();

	public Fenetre()
	{

		TitledPane PanneauMarche;
		TitledPane PanneauCarte;
		TitledPane PanneauJoueur;
		
		Tab TabConstructions = new Tab("Constructions");
		Tab TabCartes = new Tab("Cartes");
		Tab TabInventions = new Tab("Inventions");
		TabPane TabsMarche = new TabPane(TabConstructions, TabInventions, TabCartes);
		
		VBox VGauche = new VBox();
		VBox VMilieu = new VBox();
		VBox VDroite = new VBox();


		plateauActuel = 0;
		numPlateau = new Label("VuePlateau n� :" + (plateauActuel + 1));
		plateaux = new ArrayList<>();
		plateaux.add(new VuePlateau(0, 0, new Plateau(Epoque._1985, 7)));
		plateaux.add(new VuePlateau(0, 0, new Plateau(Epoque._1855, 7)));
		plateaux.add(new VuePlateau(0, 0, new Plateau(Epoque._1955, 7)));
		plateaux.add(new VuePlateau(0, 0, new Plateau(Epoque._2015, 7)));
		stack = new StackPane(plateaux.get(plateauActuel));
		suiv = new Button("Suivant");
		prec = new Button("Pr�c�dent");
		
		TabConstructions.setContent(new Label("Vive M. Gechter !"));
		TabCartes.setContent(new Label("ou pas"));
		TabConstructions.setContent(cTC);
		TabInventions.setContent(cTI);
		TabCartes.setContent(CTCards);

		
		VGauche.getChildren().add(TabsMarche);
		VGauche.setMinWidth(300);
		VGauche.setPrefWidth(300);
		VGauche.setMaxWidth(300);
		VGauche.setId("VGauche");
		
		VMilieu.getChildren().add(numPlateau);
		VMilieu.getChildren().add(new HBox(prec, suiv));
		VMilieu.getChildren().add(stack);
		VMilieu.setMinWidth(800);
		VMilieu.setPrefWidth(800);
		VMilieu.setMaxWidth(800);
		
		
		VDroite.getChildren().add(new ContentJoueur("textures/hexPlutonium.png", "DarkMowah", Color.YELLOW));
		VDroite.setMinWidth(300);
		VDroite.setPrefWidth(300);
		VDroite.setMaxWidth(300);
		VDroite.setId("VDroite");

		PanneauMarche = new TitledPane("March�", VGauche);
		PanneauMarche.setCollapsible(false);
		PanneauMarche.setPrefHeight(800);
		
		
		PanneauCarte = new TitledPane("Carte", VMilieu);
		PanneauCarte.setCollapsible(false);
		PanneauCarte.setPrefHeight(800);
		
		PanneauJoueur = new TitledPane("Joueur", VDroite);
		PanneauJoueur.setCollapsible(false);
		PanneauJoueur.setPrefHeight(800);
		
		
		getChildren().add(new HBox(PanneauMarche, PanneauCarte, PanneauJoueur));
		suiv.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				plateauActuel = (plateauActuel + 1)%4;
				System.out.println("pa :" + plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("VuePlateau n� :" + (plateauActuel + 1));

			}
		});

		prec.setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				plateauActuel = (plateauActuel + 3)%4;
				System.out.println("pa :" + plateauActuel);
				stack.getChildren().removeAll(stack.getChildren());
				stack.getChildren().add(plateaux.get(plateauActuel));
				numPlateau.setText("VuePlateau n� :" + (plateauActuel + 1));

			}
		});
	}
}
