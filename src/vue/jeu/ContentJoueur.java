package vue.jeu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import model.jeu.Jeu;
import model.jeu.TypeArete;
import model.jeu.TypePoint;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;
import model.joueur.TypeCarte;

public class ContentJoueur extends GridPane implements Desactivable {

	private Jeu m_jeu;
	private Button construire, utiliserCarteVoleur;
	private ImageView imgAvatar;
	private Label labelPseudo;
	private Label ressources;
	private Label aConstruire;
	private Label inventions;
	private Label cartes;

	public ContentJoueur(Jeu jeu)
	{
		m_jeu = jeu;
		init();
		update();
	}

	public void init()
	{
		
		imgAvatar = new ImageView();
		labelPseudo = new Label();
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		imgAvatar.setFitWidth(100);
		imgAvatar.setFitHeight(100);
		ressources = new Label("Ressources");
		ressources.setId("divisions");

		construire = new Button("Construire");
		construire.setOnMouseClicked((e) -> {
			m_jeu.changeConstructionActive();
			if (m_jeu.isConstructionActive())
				construire.setEffect(new InnerShadow());
			else
				construire.setEffect(null);
		});

		aConstruire = new Label("A construire");
		aConstruire.setId("divisions");
		
		utiliserCarteVoleur = new Button("Utiliser");
		utiliserCarteVoleur.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event)
			{
				
				m_jeu.setM_deplacementVoleurActif(true);

				
			}
		});
		inventions = new Label("Inventions : ");
		inventions.setId("divisions");


		cartes = new Label("Cartes : ");
		cartes.setId("divisions");
	}
	
	public void update()
	{
		Joueur j = m_jeu.getJoueur();
		getChildren().removeAll(getChildren());

		imgAvatar.setImage(new Image(j.getAvatar()));
		labelPseudo.setText(j.getNom());
		labelPseudo.setId("labelPseudo");

		add(imgAvatar, 0, 0);
		add(labelPseudo, 1, 0);
		add(ressources, 0, 2);

		int i = 6;
		for (Ressource ressource : Ressource.values())
		{
			if (ressource == Ressource.Autoroute)
				continue;
			int nbr = m_jeu.getJoueur().nbRessource(ressource);
			ImageView imageView = new ImageView("textures/hex" + ressource + ".png");
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);

			Label lb = new Label("x" + nbr);
			lb.setTranslateY(10);
			lb.setCenterShape(true);
			lb.setFont(Font.font("Cambria", 20));

			HBox box = new HBox(10);
			box.getChildren().addAll(imageView, lb);
			add(box, (i%2), i/2);
			i++;
		}

		add(new Label("Routes : " + j.getNombre(TypeArete.Route)), 0, 10);
		add(new Label("Autoroutes : " + j.getNombre(TypeArete.Autoroute)), 0, 11);
		add(new Label("Villages : " + j.getNombre(TypePoint.Village)), 1, 10);
		add(new Label("Villes : " + j.getNombre(TypePoint.Ville)), 1, 11);

		if (m_jeu.isConstructionActive())
		{
			m_jeu.changeConstructionActive();
			construire.setEffect(null);
		}

		add(aConstruire, 0, 9);
		add(construire, 1, 9);
		add(inventions, 0, 13);

		i = 14;
		for(Invention invention : Invention.values())
			add(new Label(invention + " : " + (j.getNombre(invention)>1 ? " Acquis" : " Non Acquis")), 0, i++, 2, 1);

		add(cartes, 0, 19);
		add(new Label("Dépl. Voleur : " + j.getNombre(TypeCarte.DeplacerVoleur)), 0, 20);
		add(utiliserCarteVoleur, 1, 20);
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
