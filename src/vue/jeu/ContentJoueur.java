package vue.jeu;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.joueur.Invention;
import model.joueur.Joueur;
import model.joueur.Ressource;

public class ContentJoueur extends GridPane {


	public ContentJoueur(Joueur j)
	{
		update(j);
	}
	
	public void update(Joueur j)
	{
		getChildren().removeAll(getChildren());
		ImageView ImgAvatar = new ImageView("textures/Avatar" + j.getNumJoueur() + ".jpg");
		Label LabelPseudo = new Label(j.getNom());

		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		ImgAvatar.setFitWidth(100);
		ImgAvatar.setFitHeight(100);
		add(ImgAvatar, 0, 0);
		add(LabelPseudo, 1, 0);
		add(new Label("Ressources :"), 0, 2);
		
		add(new Label("Métal :" + j.nbRessource(Ressource.Metal)), 0, 4);
		add(new Label("Bois :" + j.nbRessource(Ressource.Bois)), 0, 5);
		add(new Label("Roue :" + j.nbRessource(Ressource.Roue)), 0, 6);
		add(new Label("Haut-Parleurs :" + j.nbRessource(Ressource.HautParleur)), 0, 7);
		add(new Label("Antennes :" + j.nbRessource(Ressource.Antenne)), 0, 8);
		add(new Label("Morceaux de Schéma :" + j.nbRessource(Ressource.MorceauSchema)), 0, 9);
		add(new Label("Plutonium :" + j.nbRessource(Ressource.Plutonium)), 0, 10);
		add(new Label("Aimant :" + j.nbRessource(Ressource.Aimant)), 0, 11);
		add(new Label("Ventilateur :" + j.nbRessource(Ressource.Ventilateur)), 0, 12);
		
		add(new Label("Inventions :"), 0, 14);
		
		add(new Label("Train :" + j.possedeInvention(Invention.Train)), 0, 16);
		add(new Label("Radio :" + j.possedeInvention(Invention.Radio)), 0, 17);
		add(new Label("Convecteur temporel :" + j.possedeInvention(Invention.ConvecteurTemporel)), 0, 18);
		add(new Label("Hoverboard :" + j.possedeInvention(Invention.HoverBoard)), 0, 19);

	}
}
