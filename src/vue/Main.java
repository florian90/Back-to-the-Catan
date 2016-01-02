package vue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.jeu.Jeu;
import vue.jeu.Fenetre;

import java.io.File;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			MenuPrincipalView menuView = new MenuPrincipalView();
			
			Scene menu = new Scene(menuView, 1400, 850);
			menu.getStylesheets().add(getClass().getResource("StyleMenu.css").toExternalForm());
			
			primaryStage.setScene(menu);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Back To The Catane");
			primaryStage.getIcons().add(new Image("textures/favicon.jpg"));
			primaryStage.show();
			//Tests
			/*Jeu modelJeu = new Jeu(menuView.getListeJoueurs());
			Fenetre fen = new Fenetre(modelJeu);
			Scene EcranJeu = new Scene(fen, 1400, 850);
			primaryStage.setScene(EcranJeu);
			primaryStage.show();*/
			//Tests
			
			menuView.getNouvellePartie().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event)
				{
					
					menuView.setParametersVisible();
					
				}
			});
			menuView.getValider().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event)
				{
					
					Jeu modelJeu = new Jeu(menuView.getListeJoueurs());
					Fenetre fen = new Fenetre(modelJeu);
					Scene EcranJeu = new Scene(fen, 1400, 850);
					EcranJeu.getStylesheets().add(getClass().getResource("StyleEcranJeu.css").toExternalForm());
					primaryStage.setScene(EcranJeu);
					primaryStage.show();
					/*final File file = new File("src/sons/nomdezeus.mp3"); // NOM DE ZEUS !!
					final Media media = new Media(file.toURI().toString());
					final MediaPlayer mediaPlayer = new MediaPlayer(media);
					mediaPlayer.play();*/
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
