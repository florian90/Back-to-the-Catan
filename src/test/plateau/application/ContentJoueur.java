package test.plateau.application;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ContentJoueur extends GridPane
{


	public ContentJoueur(String avatar, String pseudo, Color couleur)
	{
		ImageView ImgAvatar = new ImageView(new Image(avatar));
		Label LabelPseudo = new Label(pseudo);

		setPadding(new Insets(20, 20, 20, 20));
		setHgap(15);
		setVgap(15);

		ImgAvatar.setFitWidth(100);
		ImgAvatar.setFitHeight(100);
		add(ImgAvatar, 0, 0);
		add(LabelPseudo, 1, 0);
		add(new Label("Ressources :"), 0, 2);
		
		add(new Label("M�tal :"), 0, 4);
		add(new Label("Bois :"), 0, 5);
		add(new Label("Roue :"), 0, 6);
		add(new Label("Haut-Parleurs :"), 0, 7);
		add(new Label("Antennes :"), 0, 8);
		add(new Label("Morceaux de Sch�ma :"), 0, 9);
		add(new Label("Plutonium :"), 0, 10);
		add(new Label("Aimant :"), 0, 11);
		add(new Label("Ventilateur :"), 0, 12);
		
		add(new Label("Inventions :"), 0, 14);
		
		add(new Label("Train :"), 0, 16);
		add(new Label("Radio :"), 0, 17);
		add(new Label("Convecteur temporel :"), 0, 18);
		add(new Label("Hoverboard :"), 0, 19);
	}
}
