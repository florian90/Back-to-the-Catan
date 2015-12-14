package test.plateau.application;

import javafx.application.Application;
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
			Group root = new Group();
			Scene scene = new Scene(root, 1400, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			root.getChildren().add(fen);
			/*root.getChildren().add(p2);
			root.getChildren().add(p3);
			root.getChildren().add(p4);*/

			primaryStage.setScene(scene);
			primaryStage.show();
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
