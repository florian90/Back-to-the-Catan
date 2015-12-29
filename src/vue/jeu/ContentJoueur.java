package vue.jeu;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class ContentJoueur extends GridPane {

	private Button construire;
	
	public ContentJoueur(Joueur j)
	{
		update(j);
	}
	
	public void update(Joueur j)
	{
		
		getChildren().removeAll(getChildren());
		ImageView ImgAvatar = new ImageView("textures/Avatar" + j.getNumJoueur() + ".jpg");
		Label labelPseudo = new Label(j.getNom());
		labelPseudo.setId("labelPseudo");
		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		ImgAvatar.setFitWidth(100);
		ImgAvatar.setFitHeight(100);
		add(ImgAvatar, 0, 0);
		add(labelPseudo, 1, 0);
		Label ressources = new Label("Ressources");
		ressources.setId("divisions");
		add(ressources, 0, 2);
		
		add(new Label("Métal :" + j.nbRessource(Ressource.Metal)), 0, 3);
		add(new Label("Bois :" + j.nbRessource(Ressource.Bois)), 0, 4);
		add(new Label("Roue :" + j.nbRessource(Ressource.Roue)), 0, 5);
		add(new Label("Haut-Parleurs :" + j.nbRessource(Ressource.HautParleur)), 0, 6);
		add(new Label("Morceaux de Schéma :" + j.nbRessource(Ressource.MorceauSchema)), 0, 7);
		add(new Label("Antennes :" + j.nbRessource(Ressource.Antenne)), 1, 3);
		add(new Label("Plutonium :" + j.nbRessource(Ressource.Plutonium)), 1, 4);
		add(new Label("Aimant :" + j.nbRessource(Ressource.Aimant)), 1, 5);
		add(new Label("Ventilateur :" + j.nbRessource(Ressource.Ventilateur)), 1, 6);
		
		construire =new Button("Construire");
		Label aConstruire = new Label("A construire");
		aConstruire.setId("divisions");
		add(aConstruire, 0, 9);
		add(construire,1,9);
		
		add(new Label("Routes :" + j.getNbRoutesAConstruire()), 0, 10);
		add(new Label("Autoroutes :" + j.getNbAutoroutesAConstruire()), 0, 11);
		add(new Label("Villages :" + j.getNbVillagesAConstruire()), 1, 10);
		add(new Label("Villes :" + j.getNbVillesAConstruire()), 1, 11);
		
		
		Label inventions = new Label("Inventions :");
		inventions.setId("divisions");
		add(inventions, 0, 13);
		
		add(new Label("Train :" + j.possedeInvention(Invention.Train)), 0, 14);
		add(new Label("Radio :" + j.possedeInvention(Invention.Radio)), 1, 14);
		add(new Label("Conv. temporel :" + j.possedeInvention(Invention.ConvecteurTemporel)), 0, 15);
		add(new Label("Hoverboard :" + j.possedeInvention(Invention.HoverBoard)), 1, 15);

		Label cartes = new Label("Cartes :");
		cartes.setId("divisions");
		add(cartes, 0, 17);
		add(new Label("Dépl. Voleur :" + j.getNbCartesDeplacerVoleur()), 0, 18);
		add(new Label("Développement :" + j.getNbCartesDev()), 0, 19);
		
	}
}
