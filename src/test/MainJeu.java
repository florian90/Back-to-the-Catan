package test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.jeu.Jeu;
import vue.jeu.Fenetre;

import java.util.ArrayList;


public class MainJeu extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			primaryStage.setResizable(false);
			primaryStage.setTitle("Back To The Catane");
			primaryStage.getIcons().add(new Image("textures/favicon.jpg"));

			ArrayList<String> noms = new ArrayList<>(4);
			noms.add("Marty");
			noms.add("Doc'");
			noms.add("Jennifer");
			noms.add("Einstein");

			Jeu modelJeu = new Jeu(noms);
			Fenetre fen = new Fenetre(modelJeu, primaryStage);
			Scene EcranJeu = new Scene(fen, 1400, 1000);
			EcranJeu.getStylesheets().add(getClass().getResource("../vue/StyleEcranJeu.css").toExternalForm());

			primaryStage.setScene(EcranJeu);
			primaryStage.show();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
