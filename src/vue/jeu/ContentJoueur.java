package vue.jeu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;
import model.joueur.TypeCarte;
import vue.URL;

public class ContentJoueur extends GridPane implements Desactivable {

	private Jeu m_jeu;
	private defaultButton construire, utiliserCarteVoleur;
	private ImageView imgAvatar;
	private Label labelPseudo;
	private Label ressources;
	private Label aConstruire;
	private Label invention;
	private Label cartes;
	ImageView voyants [];

	private Group groups[] = new Group[4];
	private ImageView inventions[] = new ImageView[Invention.values().length];
	private Rectangle rectangles[] = new Rectangle[4];

	private Image avatars[];
	private Label lb_ressources[];

	private Label lbRoute, lbAutoroute, lbVille, lbVillage;
	private Label lbCarteVoleur;
	private Line ligneJoueurRoute, ligneJoueurAutoroute;

	public ContentJoueur(Jeu jeu)
	{
		m_jeu = jeu;
		init();
		update(true);
	}

	public void init()
	{
		setPadding(new Insets(10, 10, 10, 10));
		setHgap(15);
		setVgap(15);
		GridPane resConstrContent = new GridPane();
		resConstrContent.setHgap(15);
		resConstrContent.setVgap(15);
		GridPane invCartesContent = new GridPane();
		invCartesContent.setHgap(15);
		invCartesContent.setVgap(15);
		Tab invCartes = new Tab("Inv. / Cartes",invCartesContent);
		Tab resConstr = new Tab("Res. / Constr.",resConstrContent);
		resConstr.setClosable(false);
		invCartes.setClosable(false);
		TabPane tabs = new TabPane(resConstr, invCartes);


		// Initialisation des avatars
		imgAvatar = new ImageView();
		avatars = new Image[m_jeu.getNbJoueurs()];
		for (int i = 0; i < m_jeu.getNbJoueurs(); i++)
			avatars[i] = new Image(m_jeu.getJoueur(i).getAvatar());
		imgAvatar.setFitWidth(100);
		imgAvatar.setFitHeight(100);
		add(imgAvatar, 0, 0);

		// Initialisation du nom du joueur
		labelPseudo = new MyLabelTitre();
		add(labelPseudo, 1, 0);

		// Ressources du joueur :
		ressources = new MyLabelTitre("Ressources");
		resConstrContent.add(ressources, 0, 2);

		lb_ressources = new Label[Ressource.values().length];

		int i = 0;
		for (Ressource ressource : Ressource.values())
		{
			if(ressource!=Ressource.Autoroute)
			{
				ImageView imageView = new ImageView("textures/hex" + ressource + ".png");
				imageView.setFitWidth(40);
				imageView.setFitHeight(40);

				lb_ressources[i] = new MyLabelQt();

				resConstrContent.add(new HBox(10, imageView, lb_ressources[i]), ((i + 6)%2), (i + 6)/2);
				i++;
			}

		}

		construire = new defaultButton("Construire");
		construire.setOnMouseClicked((e) -> {
			m_jeu.changeConstructionActive();
			if (m_jeu.isConstructionActive())
				construire.setEffect(new InnerShadow());
			else
				construire.setEffect(null);
		});

		// Initialisation des labels de nombre de trucs à construire :
		aConstruire = new MyLabelTitre("A construire");

		Line line;
		line = new Line(0, 25, Constants.hexWidth/2, 25);
		line.setStrokeWidth(Constants.roadWidth - 4);
		line.setStroke(Color.GRAY);
		ligneJoueurRoute = new Line(0, 25, Constants.hexWidth/2, 25);
		ligneJoueurRoute.setStrokeWidth(Constants.roadWidth);
		lbRoute = new MyLabelQt();
		Group group = new Group(ligneJoueurRoute, line);
		group.setTranslateY(17);
		resConstrContent.add(new HBox(20, group, lbRoute), 0, 10);
		line = new Line(0, 25, Constants.hexWidth/2, 25);
		line.setStrokeWidth(Constants.roadWidth - 4);
		line.setStroke(Color.BLACK);
		ligneJoueurAutoroute = new Line(0, 25, Constants.hexWidth/2, 25);
		ligneJoueurAutoroute.setStrokeWidth(Constants.roadWidth);
		lbAutoroute = new MyLabelQt();
		group = new Group(ligneJoueurAutoroute, line);
		group.setTranslateY(17);
		resConstrContent.add(new HBox(20, group, lbAutoroute), 0, 11);

		lbVillage = new MyLabelQt();
		ImageView imgVillage = new ImageView(URL.village);
		imgVillage.setPreserveRatio(true);
		imgVillage.setFitHeight(40);
		resConstrContent.add(new HBox(20, imgVillage, lbVillage), 1, 10);

		lbVille = new MyLabelQt();
		ImageView imgVille = new ImageView(URL.ville);
		imgVille.setPreserveRatio(true);
		imgVille.setFitHeight(40);
		resConstrContent.add(new HBox(20, imgVille, lbVille), 1, 11);

		// Inventions
		invention = new MyLabelTitre("Inventions");
		int l = 0;
		for (Invention invention : Invention.values())
		{
			inventions[l] = new ImageView(URL.url(invention));
			inventions[l].setPreserveRatio(true);
			inventions[l].setFitHeight(40);

			rectangles[l] = new Rectangle();
			rectangles[l].setFill(Color.GRAY.deriveColor(1, 1, 1, 0.95));
			rectangles[l].setX(inventions[l].getX());
			rectangles[l].setY(inventions[l].getY());
			rectangles[l].setWidth(inventions[l].getBoundsInLocal().getWidth());
			rectangles[l].setHeight(inventions[l].getBoundsInLocal().getHeight());

			groups[l] = new Group();
			groups[l].getChildren().addAll(inventions[l], rectangles[l]);

			invCartesContent.add(groups[l], l%2, l/2 + 4);
			l++;
		}

		resConstrContent.add(aConstruire, 0, 9);
		resConstrContent.add(construire, 1, 9);
		invCartesContent.add(invention, 0, 2);

		MyLabelTitre cartes = new MyLabelTitre("Cartes");
		invCartesContent.add(cartes, 0, 8);

		lbCarteVoleur = new Label();
		invCartesContent.add(lbCarteVoleur, 0, 9);

		utiliserCarteVoleur = new defaultButton("Utiliser");
		utiliserCarteVoleur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				m_jeu.setDeplacementVoleurActif(true);
			}
		});
		invCartesContent.add(utiliserCarteVoleur, 1, 9);
		invCartesContent.add(new MyLabelTitre("Epoques débloquées"), 0, 11);
		
		voyants = new ImageView[3];
		for(int k = 0;k<3;k++)
		{
			voyants[k] =  new ImageView(URL.voyantRouge);
		}
		
		Label tabLabelEpoques[] = {
				new Label("1985",new ImageView(URL.voyantVert)),
				new Label("2015",voyants[0]),
				new Label("1855",voyants[1]),
				new Label("1955",voyants[2])};

		for(int j =0;j<4;j++)
		{
			invCartesContent.add(tabLabelEpoques[j], 0, 13+j);
		}
		add(tabs,0,2,2,30);
	}
	/***************************************************************************************/
	/***************************************************************************************/
	/***************************************************************************************/
	/*
	 * Met à jour la vue correspondant au panneau de droite
	 * si le paramètre et à true, il s'agit d'un changement de joueur
	 * sinon il s'agit d'une mise à jour du contenu du joueur courant
	 */
	public void update(boolean nouveauJoueur)
	{
		Joueur joueur = m_jeu.getJoueur();

		// Update du nom et de l'avatar du joueur
		imgAvatar.setImage(avatars[joueur.getNumJoueur()]);
		labelPseudo.setText(joueur.getNom());

		// update des nombre de ressources
		int i = 0;
		for (Ressource ressource : Ressource.values())
			if (ressource != null && ressource!= Ressource.Autoroute)
				lb_ressources[i++].setText("x" + m_jeu.getJoueur().nbRessource(ressource));

		// Update des constructions dispo
		ligneJoueurRoute.setStroke(joueur.getCouleur());
		ligneJoueurAutoroute.setStroke(joueur.getCouleur());
		lbAutoroute.setText(""+joueur.getNombre(TypeArete.Autoroute));
		lbRoute.setText(""+joueur.getNombre(TypeArete.Route));
		lbVillage.setText(""+joueur.getNombre(TypePoint.Village));
		lbVille.setText(""+joueur.getNombre(TypePoint.Ville));

		if (m_jeu.isConstructionActive() && nouveauJoueur)
		{
			m_jeu.changeConstructionActive();
			construire.setEffect(null);
		}

		// Update des constructions
		int k = 0;
		for (Invention invention : Invention.values())
			rectangles[k++].setVisible(!joueur.possede(invention));

		// Update du nombre de cartes voleur
		lbCarteVoleur.setText("Dépl. Voleur : " + joueur.getNombre(TypeCarte.DeplacerVoleur));

		//Update des voyants des époques
		for(int l =0;l<3;l++)
		{	
			if(joueur.getMAccesEpoque()-1 > l)
			{
				voyants[l].setImage(new Image(URL.voyantVert));
			}
			else
			{
				voyants[l].setImage(new Image(URL.voyantRouge));
			}
		}
		
	}

	@Override
	public void desactiver()
	{
		construire.setDisable(true);
		utiliserCarteVoleur.setDisable(true);
	}

	@Override
	public void activer()
	{
		construire.setDisable(false);
		utiliserCarteVoleur.setDisable(false);
	}

	private class MyLabelTitre extends Label {
		public MyLabelTitre(){
			super();
			setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		}
		public MyLabelTitre(String string){
			super(string);
			setStyle("-fx-font-weight: bold; -fx-font-size: 15px;");
		}
	}

	private class MyLabelQt extends Label {
		public MyLabelQt(){
			super();
			setCenterShape(true);
			setFont(Font.font("Cambria", 20));
			setTranslateY(10);
		}
		public MyLabelQt(String string){
			super(string);
			setCenterShape(true);
			setFont(Font.font("Cambria", 20));
			setTranslateY(10);
		}
	}
}
