package test.plateau.application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import vue.jeu.Constants;
import vue.jeu.plateau.VuePoint;

public class TestMainFlo extends Application {
	MenuContextuelStyleeee menu;
	boolean b = false;

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			/*
			ArrayList<Joueur> joueurs = new ArrayList<>();
			for (int i = 1; i < 5; i++)
				joueurs.add(new Joueur("Joueur" + i, i));
			primaryStage.setScene(new Scene(new Fenetre(new Jeu(joueurs)), 1400, 850));
			primaryStage.show();*/
			Group root = new Group();
			primaryStage.setScene(new Scene(root, 800, 850));
			primaryStage.show();
			VuePoint vp = new VuePoint(null, 200, 200, Constants.pointRadius);
			vp.setOnMouseClicked((e) -> {
				b = true;
				menu = new MenuContextuelStyleeee(200, 200);
				root.getChildren().add(menu);
			});
			root.getChildren().add(vp);
			primaryStage.getScene().setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event)
				{
					//root.getChildren().add(new MenuContextuelStyleeee(200, 200));
					if (!b)
					{// Si on a cliqué dans le rond
						root.getChildren().remove(menu);
					}
					b=false;
				}
			});
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
