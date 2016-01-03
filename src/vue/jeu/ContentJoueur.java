package vue.jeu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.jeu.Jeu;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class ContentJoueur extends GridPane implements Desactivable {

	private Jeu m_jeu;
	private Fenetre m_fenetre;

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
		m_fenetre = m_jeu.getFenetre();

		init();
		update();
	}

	public void init(){
		
		imgAvatar = new ImageView();
		labelPseudo = new Label();
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		imgAvatar.setFitWidth(100);
		imgAvatar.setFitHeight(100);
		ressources = new Label("Ressources");
		ressources.setId("divisions");

		construire =new Button("Construire");
		construire.setOnMouseClicked((e)->{
			m_jeu.changeConstructionActive();
			if(m_jeu.isConstructionActive())
				construire.setEffect(new InnerShadow());
			else
				construire.setEffect(null);
		});

		aConstruire = new Label("A construire");
		aConstruire.setId("divisions");
		
		utiliserCarteVoleur = new Button("Utiliser");
		utiliserCarteVoleur.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
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
		Joueur j= m_jeu.getJoueur();
		getChildren().removeAll(getChildren());

		imgAvatar.setImage(new Image(j.getAvatar()));
		labelPseudo.setText(j.getNom());
		labelPseudo.setId("labelPseudo");

		add(imgAvatar, 0, 0);
		add(labelPseudo, 1, 0);
		add(ressources, 0, 2);

		add(new Label("Métal : " + j.nbRessource(Ressource.Metal)), 0, 3);
		add(new Label("Bois : " + j.nbRessource(Ressource.Bois)), 0, 4);
		add(new Label("Roue : " + j.nbRessource(Ressource.Roue)), 0, 5);
		add(new Label("Haut-Parleurs : " + j.nbRessource(Ressource.HautParleur)), 0, 6);
		add(new Label("Morceaux de Schéma : " + j.nbRessource(Ressource.MorceauSchema)), 0, 7);
		add(new Label("Antennes : " + j.nbRessource(Ressource.Antenne)), 1, 3);
		add(new Label("Plutonium : " + j.nbRessource(Ressource.Plutonium)), 1, 4);
		add(new Label("Aimant : " + j.nbRessource(Ressource.Aimant)), 1, 5);
		add(new Label("Ventilateur : " + j.nbRessource(Ressource.Ventilateur)), 1, 6);
		
		add(new Label("Routes : " + j.getNbRoutesAConstruire()), 0, 10);
		add(new Label("Autoroutes : " + j.getNbAutoroutesAConstruire()), 0, 11);
		add(new Label("Villages : " + j.getNbVillagesAConstruire()), 1, 10);
		add(new Label("Villes : " + j.getNbVillesAConstruire()), 1, 11);

		if(m_jeu.isConstructionActive())
		{
			m_jeu.changeConstructionActive();
			construire.setEffect(null);
		}

		add(aConstruire, 0, 9);
		add(construire,1,9);
		add(inventions, 0, 13);
		
		add(new Label("Train : " + (j.possedeInvention(Invention.Train)?" Acquis": " Non Acquis")), 0, 14,2,1);
		add(new Label("Radio : " + (j.possedeInvention(Invention.Radio)?" Acquis": " Non Acquis")), 0, 15,2,1);
		add(new Label("Conv. temp. : " +(j.possedeInvention(Invention.ConvecteurTemporel)?" Acquis": " Non Acquis")), 0, 16,2,1);
		add(new Label("Hoverboard : " + (j.possedeInvention(Invention.HoverBoard)?" Acquis": " Non Acquis")), 0, 17,2,1);

		add(cartes, 0, 19);
		add(new Label("Dépl. Voleur : " + j.getNbCartesDeplacerVoleur()), 0, 20);
		add(utiliserCarteVoleur,1,20);
		add(new Label("Développement : " + j.getNbCartesDev()), 0, 21);
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
