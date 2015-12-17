package test.plateau.application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{

			Fenetre fen = new Fenetre();
			MenuPrincipalView menuView = new MenuPrincipalView();
			Scene jeu = new Scene(fen, 1400, 800);
			Scene menu = new Scene(menuView,1400,800);
			jeu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
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
					primaryStage.setScene(jeu);
					primaryStage.show();
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
