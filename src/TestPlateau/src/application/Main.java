package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Plateau p1 =new Plateau(10,10);		
		/*	Plateau p2 =new Plateau(600,10);
			Plateau p3 =new Plateau(10,500);		
			Plateau p4 =new Plateau(600,510);*/

			Group root = new Group();
			Scene scene = new Scene(root,1200,1000);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			p1.setTranslateZ(100);
			root.getChildren().add(p1);
			/*root.getChildren().add(p2);
			root.getChildren().add(p3);
			root.getChildren().add(p4);*/

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
