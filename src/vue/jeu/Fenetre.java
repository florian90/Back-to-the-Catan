package vue.jeu;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.jeu.Epoque;
import model.jeu.Jeu;
import model.joueur.Joueur;
import vue.jeu.panneauMarche.ContentTabCartes;
import vue.jeu.panneauMarche.ContentTabConstructions;
import vue.jeu.panneauMarche.ContentTabInventions;
import vue.jeu.panneauMarche.VueDes;
import vue.jeu.plateau.VueEchange;
import vue.jeu.plateau.VuePlateau;

import java.util.ArrayList;

public class Fenetre extends AnchorPane {

	private StackPane stack;
	private TitledPane PanneauMarche;
	private TitledPane PanneauCarte;
	private TitledPane PanneauJoueur;
	private Button suiv, prec, finTour;
	private Button[] bt_echangerList;
	private Label numPlateau;
	private Label statusBar;

	private VueDes des = new VueDes();
	private ArrayList<VuePlateau> plateaux;
	private ContentTabConstructions cTC;
	private ContentTabInventions cTI;
	private ContentTabCartes CTCards;
	private ContentJoueur panneauJoueur;

	private Jeu modelJeu;

	private String messageClassique;
	private VueEchange echange;

	public Fenetre(Jeu p_modelJeu)
	{
		modelJeu = p_modelJeu;
		modelJeu.setVue(this);

		

		finTour = new Button("Fin du Tour");
		suiv = new Button("> Suivant");
		prec = new Button("Précédent <");

		Tab TabConstructions = new Tab("Constructions");
		Tab TabCartes = new Tab("Cartes");
		Tab TabInventions = new Tab("Inventions");
		TabPane TabsMarche = new TabPane(TabConstructions, TabInventions, TabCartes);
		TabsMarche.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		VBox VGauche = new VBox();
		VBox VMilieu = new VBox();
		VBox VDroite = new VBox();

		GridPane gridJoueurs = new GridPane();
		gridJoueurs.setHgap(40);
		gridJoueurs.setVgap(10);
		gridJoueurs.setAlignment(Pos.CENTER);
		gridJoueurs.setPadding(new Insets(20, 10, 10, 10));
		bt_echangerList = new Button[modelJeu.getNbJoueurs()];

		for (int i = 0; i < modelJeu.getNbJoueurs(); ++i)
		{ // Initialise les images des joueurs en bas et les boutons échanger
			final int index = i; 
			ImageView avatar = new ImageView(modelJeu.getJoueur(i).getAvatar());
			bt_echangerList[i] = new Button("Echanger");
			bt_echangerList[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					echange.show(modelJeu.getJoueur(), modelJeu.getJoueur(index));
					
				}
			});
			avatar.setFitWidth(50);
			avatar.setPreserveRatio(true);
			gridJoueurs.add(avatar, i, 0);
			gridJoueurs.add(new Label(modelJeu.getJoueur(i).getNom()), i, 1);
			gridJoueurs.add(bt_echangerList[i], i, 2);
		}
		gridJoueurs.add(finTour, modelJeu.getNbJoueurs(), 1);

		plateaux = new ArrayList<>();

		for(Epoque epoque : Epoque.values())
		{
			plateaux.add(new VuePlateau(0, 0, modelJeu.getPlateau(epoque), modelJeu));
		}

		numPlateau = new Label();
		statusBar = new Label();
		panneauJoueur = new ContentJoueur(modelJeu.getJoueur(), modelJeu);
		echange = new VueEchange(panneauJoueur);
		stack = new StackPane();

		cTC = new ContentTabConstructions(panneauJoueur);
		CTCards = new ContentTabCartes(modelJeu.getJoueur(),modelJeu,panneauJoueur);
		cTI = new ContentTabInventions();

		TabConstructions.setContent(cTC);
		TabInventions.setContent(cTI);
		TabCartes.setContent(CTCards);

		VGauche.getChildren().add(TabsMarche);
		VGauche.getChildren().add(new Separator());
		VGauche.getChildren().add(des);
		VGauche.setMinWidth(300);
		VGauche.setPrefWidth(300);
		VGauche.setMaxWidth(300);
		VGauche.setId("VGauche");

		VMilieu.getChildren().add(numPlateau);
		VMilieu.getChildren().add(new HBox(10, prec, suiv));
		VMilieu.getChildren().add(stack);
		VMilieu.getChildren().add(gridJoueurs);

		VMilieu.setMinWidth(800);
		VMilieu.setPrefWidth(800);
		VMilieu.setMaxWidth(800);

		VDroite.getChildren().add(panneauJoueur);
		VDroite.setMinWidth(300);
		VDroite.setPrefWidth(300);
		VDroite.setMaxWidth(300);
		VDroite.setId("VDroite");

		PanneauMarche = new TitledPane("Marché", VGauche);
		PanneauMarche.setCollapsible(false);
		PanneauMarche.setPrefHeight(800);

		PanneauCarte = new TitledPane("Carte", VMilieu);
		PanneauCarte.setCollapsible(false);
		PanneauCarte.setPrefHeight(800);

		PanneauJoueur = new TitledPane("Joueur", VDroite);
		PanneauJoueur.setCollapsible(false);
		PanneauJoueur.setPrefHeight(800);

		statusBar.setPadding(new Insets(10));

		getChildren().add(new StackPane(new VBox(new HBox(PanneauMarche, PanneauCarte, PanneauJoueur), statusBar),echange));

		/****************************************************\
		 *              Gestion des evenements              *
 		\****************************************************/
		suiv.setOnAction(new EventHandler<ActionEvent>() {
			// Passer au plateau suivant
			@Override
			public void handle(ActionEvent event)
			{
				modelJeu.epoqueSuivante();
			}
		});

		prec.setOnAction(new EventHandler<ActionEvent>() {
			// Passer au platea précédent
			@Override
			public void handle(ActionEvent event)
			{

				modelJeu.epoquePrecedente();
			}
		});

		des.getLancer().setOnAction(new EventHandler<ActionEvent>() {
			// Lance les dés
			@Override
			public void handle(ActionEvent event)
			{
				modelJeu.lancerDes();
			}

		});

		finTour.setOnAction(new EventHandler<ActionEvent>() {
			// finit le tour d'un joueur et passer au joueur suivant
			@Override
			public void handle(ActionEvent event)
			{
				modelJeu.joueurSuivant();
			}
		});

		// Initialise la vue
		modelJeu.initJeu();
	}

	public void initTourJoueur()
	{
		System.out.println("test");
		//cTC.desactiver();
		cTI.desactiver();
		des.activer();
		CTCards.desactiver();
		panneauJoueur.desactiver();
		finTour.setDisable(true);
		messageClassique = modelJeu.getJoueur().getNom() + " - Lancez les dés pour commencer";
		resetStatus();
		panneauJoueur.update(modelJeu.getJoueur());
		desactiveEchangeBouttonJoueurActuel();
		cTC.setJoueur(modelJeu.getJoueur());
	}

	public void desactiveEchangeBouttonJoueurActuel()
	{
		// Active tous les boutons pour les échanges
		for(int i=0;i<modelJeu.getNbJoueurs();++i)
			bt_echangerList[i].setDisable(false);
		// Désactive le bouton pour le joueur actuel
		bt_echangerList[modelJeu.getJoueur().getNumJoueur()-1].setDisable(true);
	}

	public void lanceDes(int[] tab)
	{
		des.actualiserResultats(tab);
		des.desactiver();
		panneauJoueur.activer();
		finTour.setDisable(false);
		messageClassique = modelJeu.getJoueur().getNom() + " échangez, achetez, construisez puis terminez votre tour pour passer au joueur suivant";
		resetStatus();
	}

	public void chargerPlateau(Epoque epoque)
	{
		VuePlateau newPlateau = getVuePlateau(epoque);
		stack.getChildren().removeAll(stack.getChildren());
		stack.getChildren().add(newPlateau);
		numPlateau.setText("Epoque : " + epoque);
		cTC.setEpoque(epoque);
	}

	public void setStatus(String str)
	{
		statusBar.setText(str);
	}

	public void resetStatus()
	{
		statusBar.setText(messageClassique);
	}

	public VuePlateau getVuePlateau(Epoque epoque)
	{
		for(VuePlateau pl : plateaux)
			if(pl.getPlateau().getEpoque() == epoque)
				return pl;
		return null;
	}

	public void setMessageClassique(String str)
	{
		messageClassique = str;
		resetStatus();
	}

	public void updateJoueur()
	{
		System.out.println("updateJoueur");
	}
}
