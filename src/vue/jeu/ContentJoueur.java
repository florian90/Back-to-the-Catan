package vue.jeu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
	private Button construire, utiliserCarteVoleur;
	private ImageView imgAvatar;
	private Label labelPseudo;
	private Label ressources;
	private Label aConstruire;
	private Label invention;
	private Label cartes;

	private Group groups[] = new Group[4];
	private ImageView inventions[] = new ImageView[Invention.values().length];
	private Rectangle rectangles[] = new Rectangle[4];

	private Image avatars[];
	private Label lb_ressources[];

	private Label lbRoute, lbAutoroute, lbVille, lbVillage;
	private Label lbCarteVoleur;

	public ContentJoueur(Jeu jeu)
	{
		m_jeu = jeu;
		init();
		update();
	}

	public void init()
	{
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		// Initialisation des avatars
		imgAvatar = new ImageView();
		avatars = new Image[m_jeu.getNbJoueurs()];
		for (int i = 0; i < m_jeu.getNbJoueurs(); i++)
			avatars[i] = new Image(m_jeu.getJoueur(i).getAvatar());
		imgAvatar.setFitWidth(100);
		imgAvatar.setFitHeight(100);
		add(imgAvatar, 0, 0);

		// Initialisation du nom du joueur
		labelPseudo = new Label();
		add(labelPseudo, 1, 0);

		// Ressources du joueur :
		ressources = new Label("Ressources");
		add(ressources, 0, 2);

		lb_ressources = new Label[Ressource.values().length - 1];

		int i = 0;
		for (Ressource ressource : Ressource.values())
		{
			if (ressource == Ressource.Autoroute)
				continue;
			int nbr = m_jeu.getJoueur().nbRessource(ressource);
			ImageView imageView = new ImageView("textures/hex" + ressource + ".png");
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);

			lb_ressources[i] = new Label();
			lb_ressources[i].setTranslateY(10);
			lb_ressources[i].setCenterShape(true);
			lb_ressources[i].setFont(Font.font("Cambria", 20));

			add(new HBox(imageView, lb_ressources[i]), ((i + 6)%2), (i + 6)/2);
			i++;
		}

		construire = new Button("Construire");
		construire.setOnMouseClicked((e) -> {
			m_jeu.changeConstructionActive();
			if (m_jeu.isConstructionActive())
				construire.setEffect(new InnerShadow());
			else
				construire.setEffect(null);
		});

		// Initialisation des labels de nombre de trucs à construire :
		aConstruire = new Label("A construire");
		lbRoute = new Label();
		lbAutoroute = new Label();
		lbVillage = new Label();
		lbVille = new Label();
		add(lbRoute, 0, 10);
		add(lbAutoroute, 0, 11);
		add(lbVillage, 1, 10);
		add(lbVille, 1, 11);
		invention = new Label("Inventions : ");
		invention.setId("divisions");

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

			add(groups[l], l%2, l/2 + 14);
			l++;
		}

		add(aConstruire, 0, 9);
		add(construire, 1, 9);
		add(invention, 0, 13);

		cartes = new Label("Cartes : ");
		add(cartes, 0, 19);

		lbCarteVoleur = new Label();
		add(lbCarteVoleur, 0, 20);

		utiliserCarteVoleur = new Button("Utiliser");
		utiliserCarteVoleur.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event)
			{
				m_jeu.setDeplacementVoleurActif(true);
			}
		});
		add(utiliserCarteVoleur, 1, 20);
	}
	/***************************************************************************************/
	/***************************************************************************************/
	/***************************************************************************************/
	public void update()
	{
		Joueur joueur = m_jeu.getJoueur();

		// Update du nom et de l'avatar du joueur
		imgAvatar.setImage(avatars[joueur.getNumJoueur()]);
		labelPseudo.setText(joueur.getNom());

		// update des nombre de ressources
		int i = 0;
		for (Ressource ressource : Ressource.values())
			if (ressource != Ressource.Autoroute)
				lb_ressources[i++].setText("x" + m_jeu.getJoueur().nbRessource(ressource));

		// Update des constructions dispo
		lbAutoroute.setText("Routes : " + joueur.getNombre(TypeArete.Route));
		lbRoute.setText("Autoroutes : " + joueur.getNombre(TypeArete.Autoroute));
		lbVillage.setText("Villages : " + joueur.getNombre(TypePoint.Village));
		lbVille.setText("Villes : " + joueur.getNombre(TypePoint.Ville));

		if (m_jeu.isConstructionActive())
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
}
