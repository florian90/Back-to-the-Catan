package vue.jeu;


import java.io.File;
import java.util.ArrayList;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.jeu.Epoque;
import model.jeu.Jeu;
import vue.URL;
import vue.jeu.panneauMarche.ContentTabCartes;
import vue.jeu.panneauMarche.ContentTabConstructions;
import vue.jeu.panneauMarche.ContentTabInventions;
import vue.jeu.panneauMarche.VueDes;
import vue.jeu.plateau.VueEchange;
import vue.jeu.plateau.VuePlateau;

public class Fenetre extends AnchorPane {

	private StackPane stack;
	private TitledPane PanneauMarche;
	private TitledPane PanneauCarte;
	private TitledPane PanneauJoueur;
	private defaultButton prec, finTour;
	private AnimatedButton suiv;
	private defaultButton[] bt_echangerList;
	private Label numPlateau;
	private Label statusBar;

	private VueDes des = new VueDes();
	private ArrayList<VuePlateau> plateaux;
	private ContentTabConstructions cTC;
	private ContentTabInventions cTI;
	private ContentTabCartes cTCards;
	
	private VBox VMilieu;
	private ContentJoueur panneauJoueur;

	private Jeu m_jeu;

	private String messageClassique;
	private VueEchange echange;
	Stage primaryStage;

	public Fenetre(Jeu p_modelJeu, Stage p_primaryStage)
	{
		m_jeu = p_modelJeu;
		m_jeu.setVue(this);

		primaryStage = p_primaryStage;

		finTour = new defaultButton("Fin du Tour");
		suiv = new AnimatedButton("> Suivant");
		prec = new defaultButton("Précédent <");

		Tab TabConstructions = new Tab("Constructions");
		Tab TabCartes = new Tab("Cartes");
		Tab TabInventions = new Tab("Inventions");
		TabPane TabsMarche = new TabPane(TabConstructions, TabInventions, TabCartes);
		TabsMarche.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		TabsMarche.setPrefHeight(450);
		
		VMilieu = new VBox();
		VBox VGauche = new VBox();
		VBox VDroite = new VBox();

		GridPane gridJoueurs = new GridPane();
		gridJoueurs.setHgap(40);
		gridJoueurs.setVgap(10);
		gridJoueurs.setAlignment(Pos.CENTER);
		gridJoueurs.setPadding(new Insets(20, 10, 10, 10));
		bt_echangerList = new defaultButton[m_jeu.getNbJoueurs()];

		for (int i = 0; i < m_jeu.getNbJoueurs(); ++i)
		{ // Initialise les images des joueurs en bas et les boutons échanger
			final int index = i; 
			ImageView avatar = new ImageView(m_jeu.getJoueur(i).getAvatar());
			bt_echangerList[i] = new defaultButton("Echanger");
			bt_echangerList[i].setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					
					echange.show(m_jeu.getJoueur(), m_jeu.getJoueur(index));
					
				}
			});
			avatar.setFitWidth(50);
			avatar.setPreserveRatio(true);
			gridJoueurs.add(avatar, i, 0);
			gridJoueurs.add(new Label(m_jeu.getJoueur(i).getNom()), i, 1);
			gridJoueurs.add(bt_echangerList[i], i, 2);
		}
		gridJoueurs.add(finTour, m_jeu.getNbJoueurs(), 1);

		plateaux = new ArrayList<>();

		for(Epoque epoque : Epoque.values())
		{
			plateaux.add(new VuePlateau(0, 0, m_jeu.getPlateau(epoque), m_jeu));
		}

		numPlateau = new Label();
		statusBar = new Label();
		panneauJoueur = new ContentJoueur(m_jeu);
		echange = new VueEchange(panneauJoueur);
		stack = new StackPane();

		cTC = new ContentTabConstructions(m_jeu);
		cTCards = new ContentTabCartes(m_jeu);
		cTI = new ContentTabInventions(m_jeu);

		TabConstructions.setContent(cTC);
		TabInventions.setContent(cTI);
		TabCartes.setContent(cTCards);

		VGauche.getChildren().add(TabsMarche);
		VGauche.getChildren().add(new Separator());
		VGauche.getChildren().add(des);
		VGauche.setMinWidth(300);
		VGauche.setPrefWidth(300);
		VGauche.setMaxWidth(300);
		VGauche.setId("VGauche");

		VMilieu.setId("fond1985");
		VMilieu.getChildren().add(numPlateau);
		numPlateau.setText("Epoques : ");
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
				m_jeu.epoqueSuivante();
				VMilieu.setId("fond"+Epoque.toString(p_modelJeu.getEpoqueActuelle()));
				if (suiv.isBlinking())
				suiv.stopBlinking();
				p_modelJeu.getJoueur().setSuivHasToBlink(false);
			}
		});

		prec.setOnAction(new EventHandler<ActionEvent>() {
			// Passer au plateau précédent
			@Override
			public void handle(ActionEvent event)
			{

				m_jeu.epoquePrecedente();
				VMilieu.setId("fond"+Epoque.toString(p_modelJeu.getEpoqueActuelle()));

			}
		});

		des.getLancer().setOnAction(new EventHandler<ActionEvent>() {
			// Lance les dés
			@Override
			public void handle(ActionEvent event)
			{
				m_jeu.lancerDes();
			}

		});

		finTour.setOnAction(new EventHandler<ActionEvent>() {
			// Finit le tour d'un joueur et passer au joueur suivant
			@Override
			public void handle(ActionEvent event)
			{
				m_jeu.joueurSuivant();
			}
		});

		// Initialise la vue
		m_jeu.initJeu();
	}

	public void initTourJoueur()
	{
		cTI.update();
		panneauJoueur.update();
		cTC.desactiver();
		cTI.desactiver();
		cTC.desactiver();
		cTCards.desactiver();
		panneauJoueur.desactiver();
		des.activer();
		finTour.setDisable(true);
		messageClassique = m_jeu.getJoueur().getNom() + " - Lancez les dés pour commencer";
		resetStatus();
		desactiveEchangeBouttonJoueurActuel();
	}

	public void desactiveEchangeBouttonJoueurActuel()
	{
		// Active tous les boutons pour les échanges
		for(int i=0;i<m_jeu.getNbJoueurs();++i)
			bt_echangerList[i].setDisable(false);
		// Désactive le bouton pour le joueur actuel
		bt_echangerList[m_jeu.getJoueur().getNumJoueur()].setDisable(true);
	}

	public void lanceDes(int[] tab)
	{
		des.actualiserResultats(tab);
		des.desactiver();
		panneauJoueur.activer();
		finTour.setDisable(false);
		cTC.activer();
		cTI.activer();
		cTCards.activer();
		messageClassique = m_jeu.getJoueur().getNom() + " échangez, achetez, construisez puis terminez votre tour pour passer au joueur suivant";
		resetStatus();
	}

	public void chargerPlateau(Epoque epoque)
	{
		VuePlateau newPlateau = getVuePlateau(epoque);
		stack.getChildren().removeAll(stack.getChildren());
		stack.getChildren().add(newPlateau);
		
		//numPlateau.setText("Epoque : " + Epoque.toString(epoque));
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
		panneauJoueur.update();
		cTC.update();
		cTI.update();
		cTCards.update();
	}
	
	public ContentTabCartes getcTCards() {
		return cTCards;
	}


	public void initTourInitiaux()
	{
		initTourJoueur();
		des.desactiver();
		setMessageClassique(m_jeu.getJoueur() + " - Construisez votre première colonie !");
		m_jeu.changeConstructionActive();
	}
	
	public void afficheVainqueur()
	{
		GridPane grid = new GridPane();
		Button ok = new defaultButton("OK");
		grid.add(new Label(m_jeu.getJoueur().getNom()+", vous avez construit la dernière invention et donc vous \npossédez maintenant le TrainKiVol !!!\n\n Félicitations !"), 0, 0);
		grid.add(ok, 0, 1);
		TitledPane panneauVainqueur = new TitledPane("Félicitations !",grid);
		panneauVainqueur.setTranslateX(450);
		panneauVainqueur.setTranslateY(325);
		panneauVainqueur.setPrefSize(500, 200);
		panneauVainqueur.setId("divisions");
		panneauVainqueur.setCollapsible(false);
		getChildren().add(panneauVainqueur);
		ok.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				videoFin();
			}
		});
		
		
	}
	
	public void videoFin()
	{
	    final File f = new File(URL.video_fin);
	    final Media m = new Media(f.toURI().toString());
	    final MediaPlayer mp = new MediaPlayer(m);
	    final MediaView mv = new MediaView(mp);

	    final DoubleProperty width = mv.fitWidthProperty();
	    final DoubleProperty height = mv.fitHeightProperty();
	    
	    width.bind(Bindings.selectDouble(mv.sceneProperty(), "width"));
	    height.bind(Bindings.selectDouble(mv.sceneProperty(), "height"));
	    
	    mv.setPreserveRatio(true);
	    StackPane root = new StackPane();
	    root.getChildren().add(mv);
	    final Scene scene = new Scene(root, 960, 540);
	    scene.setFill(Color.BLACK);
	    primaryStage.setScene(scene);
	    primaryStage.setFullScreen(true);
	    primaryStage.show();
		mp.play();

	}

	public void setSuivBlinking(boolean b)
	{
		if(b)
			suiv.startBlinking();
		else
			suiv.stopBlinking();
	}
}
