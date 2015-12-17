package test.plateau.application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.jeu.Jeu;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{

			
			MenuPrincipalView menuView = new MenuPrincipalView();
			
			Scene menu = new Scene(menuView,1400,850);
			//jeu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(menu);
			primaryStage.show();
			
			menuView.getNouvellePartie().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					menuView.setParametersVisible();
					
				}
			});
			menuView.getValider().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					
					Jeu modelJeu = new Jeu(menuView.getListeJoueurs());
					Fenetre fen = new Fenetre(modelJeu);
					Scene EcranJeu = new Scene(fen, 1400, 850);
					primaryStage.setScene(EcranJeu);
					primaryStage.show();
					System.out.println(menuView.getListeJoueurs());
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
