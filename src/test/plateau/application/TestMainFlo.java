package test.plateau.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.jeu.Jeu;
import model.joueur.Joueur;
import vue.jeu.Fenetre;

import java.util.ArrayList;

public class TestMainFlo extends Application {
	MenuContextuelStyleeee menu;
	boolean clicledOnPoint = false;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			ArrayList<Joueur> joueurs = new ArrayList<>();
			for (int i = 1; i < 5; i++)
				joueurs.add(new Joueur("Joueur" + i, i));
			primaryStage.setScene(new Scene(new Fenetre(new Jeu(joueurs)), 1400, 850));
			primaryStage.show();
/*
			Group root = new Group();
			primaryStage.setScene(new Scene(root, 800, 850));
			primaryStage.show();
			VuePoint vp = new VuePoint(null, 200, 200);
			vp.setOnMouseClicked((e) -> {
				clicledOnPoint = true;
				root.getChildren().add(menu);
			});
			root.getChildren().add(vp);
			primaryStage.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event)
				{
					if (!clicledOnPoint)
					{// Si on a cliqué dans le rond
						root.getChildren().remove(menu);
					}
					clicledOnPoint=false;
				}
			});*/
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
